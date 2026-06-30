package controller;

import entity.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.IProductService;
import service.ProductService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductController", value = "/product/*")
public class ProductController extends HttpServlet {
    private IProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        List<Product> productList = null;
        String action = req.getPathInfo();
        String idStr = req.getParameter("id");

        if (action == null) {
            action = "";
        }
        switch (action) {
            case "/display":
                productList = productService.getProductList();
                req.setAttribute("productList", productList);
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
                if (keyword == null || keyword.trim().isEmpty()) {
                    resp.sendRedirect(req.getContextPath() + "/product/display");
                    return;
                }
                productList = productService.findProductByName(keyword);
                req.setAttribute("productList", productList);
                req.getRequestDispatcher("/WEB-INF/product/product.jsp").forward(req, resp);
                break;
            case "/create":
                req.getRequestDispatcher("/WEB-INF/product/create.jsp").forward(req, resp);
                break;
            case "/edit":
                if (idStr == null || idStr.isEmpty()) {
                    return;
                }
                try {
                    int id = Integer.parseInt(idStr);
                    Product product = productService.viewProduct(id);
                    req.setAttribute("product", product);
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
        int price = 0;
        int id = 0;
        try {
            if (idStr != null && !idStr.isEmpty()) {
                id = Integer.parseInt(idStr);
            }
            if (priceStr != null && !priceStr.isEmpty()) {
                price = Integer.parseInt(priceStr);
            }
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }

        switch (action) {
            case "/create":
                product = new Product();
                product.setName(nameStr);
                product.setPrice(price);
                productService.save(product);
                resp.sendRedirect(req.getContextPath() + "/product/display");
                break;
            case "/edit":
                if (id == 0) {
                    resp.sendRedirect(req.getContextPath() + "/product/display");
                    return;
                }
                id = Integer.parseInt(idStr);
                product = new Product();
                product.setName(nameStr);
                product.setPrice(price);
                productService.update(product, id);
                resp.sendRedirect(req.getContextPath() + "/product/display");
                break;
            case "/remove":
                productService.remove(id);
                resp.sendRedirect(req.getContextPath() + "/product/display");
                break;
        }
    }
}
