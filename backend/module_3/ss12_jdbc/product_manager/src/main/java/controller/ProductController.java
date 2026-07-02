package controller;

import entity.Category;
import entity.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CategoryService;
import service.ICategoryService;
import service.IProductService;
import service.ProductService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductController", value = "/products/*")
public class ProductController extends HttpServlet {
    private final IProductService productService = new ProductService();
    private final ICategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        List<Product> productList = null;
        List<Category> categoryList = null;
        String action = req.getPathInfo();
        String idStr = req.getParameter("id");

        if (action == null) {
            action = "";
        }
        switch (action) {
            case "":
            case "/":
            case "/display":
                productList = productService.getProductList();
                categoryList = categoryService.getCategories();
                req.setAttribute("productList", productList);
                req.setAttribute("categoryList", categoryList);
                req.getRequestDispatcher("/WEB-INF/product/product.jsp").forward(req, resp);
                break;
            case "/view":
                if (idStr == null || idStr.isEmpty()) {
                    return;
                }
                try {
                    int id = Integer.parseInt(idStr);
                    Product product = productService.viewProduct(id);
                    req.setAttribute("product", product);
                    req.getRequestDispatcher("/WEB-INF/product/view.jsp").forward(req, resp);
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }
                break;
            case "/search":
                String keyword = req.getParameter("keyword");
                String categoryIdStr = req.getParameter("category");
                int categoryId = 0;

                try {
                    if (categoryIdStr != null && !categoryIdStr.isEmpty()) {
                        categoryId = Integer.parseInt(categoryIdStr);
                    }
                } catch (NumberFormatException ex) {
                    resp.sendRedirect(req.getContextPath() + "/products/display");
                    return;
                }

                if (keyword == null) {
                    keyword = "";
                }

                if (keyword.trim().isEmpty() && categoryId == 0) {
                    resp.sendRedirect(req.getContextPath() + "/products/display");
                    return;
                }

                productList = productService.findProductByName(keyword, categoryId);

                categoryList = categoryService.getCategories();
                req.setAttribute("productList", productList);
                req.setAttribute("categoryList", categoryList);
                req.getRequestDispatcher("/WEB-INF/product/product.jsp").forward(req, resp);
                break;
            case "/create": {
                categoryList = categoryService.getCategories();
                req.setAttribute("categoryList", categoryList);
                req.getRequestDispatcher("/WEB-INF/product/create.jsp").forward(req, resp);
                break;
            }
            case "/edit":
                if (idStr == null || idStr.isEmpty()) {
                    return;
                }
                try {
                    int id = Integer.parseInt(idStr);
                    Product product = productService.viewProduct(id);
                    req.setAttribute("product", product);
                    categoryList = categoryService.getCategories();
                    req.setAttribute("categoryList", categoryList);
                    req.getRequestDispatcher("/WEB-INF/product/edit.jsp").forward(req, resp);
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        List<Product> productList = null;
        String action = req.getPathInfo();
        if (action == null) {
            action = "";
        }
        Product product = null;
        String idStr = req.getParameter("id");
        String nameStr = req.getParameter("name");
        String priceStr = req.getParameter("price");
        String categoryIdStr = req.getParameter("category");
        int price = 0;
        int id = 0;
        int categoryId = 0;
        try {
            if (idStr != null && !idStr.isEmpty()) {
                id = Integer.parseInt(idStr);
            }
            if (priceStr != null && !priceStr.isEmpty()) {
                price = Integer.parseInt(priceStr);
            }
            if (categoryIdStr != null && !categoryIdStr.isEmpty()) {
                categoryId = Integer.parseInt(categoryIdStr);
            }
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }

        switch (action) {
            case "/create":
                product = new Product();
                product.setName(nameStr);
                product.setPrice(price);
                product.setCategoryId(categoryId);
                productService.save(product);
                resp.sendRedirect(req.getContextPath() + "/products/display");
                break;
            case "/edit":
                if (id == 0) {
                    resp.sendRedirect(req.getContextPath() + "/products/display");
                    return;
                }
                id = Integer.parseInt(idStr);
                product = new Product();
                product.setName(nameStr);
                product.setPrice(price);
                product.setCategoryId(categoryId);
                productService.update(product, id);
                resp.sendRedirect(req.getContextPath() + "/products/display");
                break;
            case "/remove":
                productService.remove(id);
                resp.sendRedirect(req.getContextPath() + "/products/display");
                break;
        }
    }
}
