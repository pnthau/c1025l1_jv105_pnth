package controller;

import dto.CardDto;
import service.CardService;
import service.ICardService;

import utility.Validator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.io.PrintWriter;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(name = "CardController", value = { "/cards", "/cards/*" })
public class CardController extends HttpServlet {
    private final ICardService cardService = new CardService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String pathInfo = req.getPathInfo();
        if ("/return".equals(pathInfo)) {
            handleReturnBook(req, resp);
        } else {
            listCards(req, resp);
        }
    }

    private void handleReturnBook(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String idStr = req.getParameter("id");
        String bookIdStr = req.getParameter("bookId");

        boolean success = false;
        if (Validator.isPositiveNumber(idStr) && Validator.isPositiveNumber(bookIdStr)) {
            int cardId = Integer.parseInt(idStr);
            int bookId = Integer.parseInt(bookIdStr);
            success = cardService.returnBook(cardId, bookId);
        }

        resp.setContentType("application/json; charset=UTF-8");
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("success", success);

        ObjectMapper mapper = new ObjectMapper();
        try (PrintWriter out = resp.getWriter()) {
            mapper.writeValue(out, responseData);
        }
    }

    private void listCards(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CardDto> cardList = cardService.findAll();
        req.setAttribute("cards", cardList);
        req.getRequestDispatcher("/WEB-INF/view/cards/list.jsp").forward(req, resp);
    }
}
