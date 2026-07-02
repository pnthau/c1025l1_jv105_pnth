package controller;

import dto.CategoryDTO;
import entity.Category;
import entity.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CategoryService;
import service.ICategoryService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoryController", value = "/categories/*")
public class CategoryController extends HttpServlet {
    private final ICategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        List<Category> categoryList = null;
        String action = req.getPathInfo();
        String idStr = req.getParameter("id");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "/display":
                categoryList = categoryService.getCategories();
                req.setAttribute("categoryList", categoryList);
                req.getRequestDispatcher("/WEB-INF/category/list.jsp").forward(req, resp);
                break;

        }
    }
}
