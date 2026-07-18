package com.example.controller;

import com.example.entity.RentalSpace;
import com.example.service.RentalSpaceService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "RentalSpaceServlet", urlPatterns = "/rental-spaces")
public class RentalSpaceServlet extends HttpServlet {

    private RentalSpaceService rentalSpaceService = new RentalSpaceService();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        if (action == null)
            action = "";

        switch (action) {
            case "create":
                showCreateForm(request, response);
                break;
            default:
                listRentalSpaces(request, response);
                break;
        }
    }

    private void listRentalSpaces(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("type");
        String floorStr = request.getParameter("floor");
        Integer floor = null;
        if (floorStr != null && !floorStr.isEmpty()) {
            try {
                floor = Integer.parseInt(floorStr);
            } catch (NumberFormatException e) {
                // ignore
            }
        }

        List<RentalSpace> rentalSpaces = rentalSpaceService.findAll(type, floor);
        request.setAttribute("rentalSpaces", rentalSpaces);
        request.setAttribute("searchType", type);
        request.setAttribute("searchFloor", floor);

        request.getRequestDispatcher("/WEB-INF/views/rental_space/list.jsp").forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/rental_space/create.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if ("create".equals(action)) {
            createRentalSpace(request, response);
        }
    }

    private void createRentalSpace(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");
        String areaStr = request.getParameter("area");
        String status = request.getParameter("status");
        String floorStr = request.getParameter("floor");
        String type = request.getParameter("type");
        String priceStr = request.getParameter("price");

        Double area = null;
        Integer floor = null;
        Double price = null;

        try {
            if (areaStr != null && !areaStr.isEmpty())
                area = Double.parseDouble(areaStr);
        } catch (Exception e) {
        }
        try {
            if (floorStr != null && !floorStr.isEmpty())
                floor = Integer.parseInt(floorStr);
        } catch (Exception e) {
        }
        try {
            if (priceStr != null && !priceStr.isEmpty())
                price = Double.parseDouble(priceStr);
        } catch (Exception e) {
        }

        RentalSpace rentalSpace = new RentalSpace(null, code, area, status, floor, type, price);
        Map<String, String> errors = rentalSpaceService.save(rentalSpace);

        if (errors.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/rental-spaces?successCode=" + java.net.URLEncoder.encode(code, "UTF-8"));
        } else {
            request.setAttribute("errors", errors);
            request.setAttribute("rentalSpace", rentalSpace);
            request.getRequestDispatcher("/WEB-INF/views/rental_space/create.jsp").forward(request, response);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> jsonResponse = new HashMap<>();

        if (code != null) {
            boolean isDeleted = rentalSpaceService.delete(code);
            if (isDeleted) {
                jsonResponse.put("success", true);
            } else {
                jsonResponse.put("success", false);
                jsonResponse.put("message", "Không thể xóa mặt bằng.");
            }
        } else {
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Mã mặt bằng không hợp lệ.");
        }

        out.print(objectMapper.writeValueAsString(jsonResponse));
        out.flush();
    }
}
