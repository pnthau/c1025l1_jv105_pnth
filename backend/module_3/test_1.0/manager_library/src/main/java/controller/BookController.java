package controller;

import entity.Book;
import entity.Card;
import entity.Student;
import service.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utility.Validator;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(name = "BookController", value = { "/books", "/books/*" })
public class BookController extends HttpServlet {
    private final IBookService bookService = new BookService();
    private final IStudentService studentService = new StudentService();
    private final ICardService cardService = new CardService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String pathInfo = req.getPathInfo();

        if ("/borrow".equals(pathInfo)) {
            borrowBook(req, resp);
        } else {
            listBooks(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String pathInfo = req.getPathInfo();

        if ("/borrow".equals(pathInfo)) {
            handleBorrowBook(req, resp);
        } else {
            listBooks(req, resp);
        }
    }

    private void handleBorrowBook(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String cardCode = req.getParameter("cardCode");
        String bookIdStr = req.getParameter("bookId");
        String studentIdStr = req.getParameter("studentId");
        String borrowDateStr = req.getParameter("borrowDate");
        String returnDateStr = req.getParameter("returnDate");

        Book book = null;
        int bookId = 0;
        if (Validator.isPositiveNumber(bookIdStr)) {
            bookId = Integer.parseInt(bookIdStr);
            book = bookService.findById(bookId);
        }
        Student student = null;
        int studentId = 0;
        if (Validator.isPositiveNumber(studentIdStr)) {
            studentId = Integer.parseInt(studentIdStr);
            student = studentService.findById(studentId);
        }
        LocalDate borrowDate = null;
        if (Validator.isValidDate(borrowDateStr)) {
            borrowDate = LocalDate.parse(borrowDateStr);
        }
        LocalDate returnDate = null;
        if (Validator.isValidDate(returnDateStr)) {
            returnDate = LocalDate.parse(returnDateStr);
        }

        if (cardCode == null || cardCode.trim().isEmpty() || !cardCode.matches("^MS-\\d{4}$")) {
            sendJsonResponse(resp, false, "Lỗi Mã Mượn Sách", "Mã mượn sách không được để trống và phải đúng định dạng MS-XXXX (ví dụ: MS-0001).");
            return;
        }

        if (borrowDate != null && returnDate != null && returnDate.isBefore(borrowDate)) {
            sendJsonResponse(resp, false, "Lỗi Ngày Mượn Trả", "Ngày trả sách không được phép trước ngày mượn sách.");
            return;
        }

        if (book != null && book.getQuantity() <= 0) {
            sendJsonResponse(resp, false, "Hết Sách", "Sách này hiện đã hết trong thư viện.");
            return;
        }

        if (student != null && book != null && cardService.existsByStudentAndBook(studentId, bookId)) {
            sendJsonResponse(resp, false, "Lỗi Mượn Trùng", student.getName() + " đã mượn sách này rồi.");
            return;
        }

        if (cardCode != null && !cardCode.trim().isEmpty() && cardService.existsByCardCode(cardCode)) {
            sendJsonResponse(resp, false, "Lỗi Mã Mượn Sách", "Mã mượn sách '" + cardCode + "' đã tồn tại.");
            return;
        }

        Card card = new Card(0, cardCode, bookId, studentId, borrowDate, returnDate);
        boolean isSuccess = cardService.save(card);

        if (isSuccess) {
            sendJsonResponse(resp, true, "Thành Công", "Mượn sách thành công.");
        } else {
            sendJsonResponse(resp, false, "Lỗi", "Mượn sách thất bại.");
        }
    }

    private void borrowBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        if (idStr == null || idStr.trim().isEmpty()) {
            idStr = req.getParameter("bookId");
        }
        int id = 0;
        if (Validator.isPositiveNumber(idStr)) {
            id = Integer.parseInt(idStr);
        }

        List<Student> studentList = studentService.findAll();
        Book book = bookService.findById(id);
        LocalDate today = LocalDate.now();

        req.setAttribute("book", book);
        req.setAttribute("studentList", studentList);
        req.setAttribute("today", today);

        req.getRequestDispatcher("/WEB-INF/view/books/borrow.jsp").forward(req, resp);
    }

    private void listBooks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookService.findAll();
        req.setAttribute("books", books);
        req.getRequestDispatcher("/WEB-INF/view/books/list.jsp").forward(req, resp);
    }

    private void sendJsonResponse(HttpServletResponse resp, boolean success, String errorTitle, String errorMessage)
            throws IOException {
        resp.setContentType("application/json; charset=UTF-8");

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("success", success);
        if (!success) {
            responseData.put("errorTitle", errorTitle != null ? errorTitle : "Lỗi");
            responseData.put("errorMessage", errorMessage != null ? errorMessage : "Có lỗi xảy ra");
        }

        ObjectMapper mapper = new ObjectMapper();
        try (PrintWriter out = resp.getWriter()) {
            mapper.writeValue(out, responseData);
        }
    }
}
