package com.example.manager_qa.controller;

import com.example.manager_qa.dto.QuestionRequestDTO;
import com.example.manager_qa.entity.Question;
import com.example.manager_qa.entity.QuestionStatus;
import com.example.manager_qa.entity.QuestionType;
import com.example.manager_qa.repository.IQuestionRepository;
import com.example.manager_qa.repository.IQuestionTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Transactional
class QuestionControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private IQuestionRepository questionRepository;

    @Autowired
    private IQuestionTypeRepository questionTypeRepository;

    private MockMvc mockMvc;
    private QuestionType defaultType;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        questionRepository.deleteAll();
        questionTypeRepository.deleteAll();

        defaultType = questionTypeRepository.save(
                QuestionType.builder().name("Khóa học & Lộ trình").build()
        );
    }

    @Test
    @DisplayName("GET /questions: Danh sách câu hỏi hiển thị thành công với phân trang")
    void testListQuestions() throws Exception {
        questionRepository.save(Question.builder()
                .title("Câu hỏi kiểm thử 1")
                .content("Nội dung câu hỏi kiểm thử 1")
                .questionType(defaultType)
                .date(LocalDate.now())
                .status(QuestionStatus.WAITING)
                .build());

        mockMvc.perform(get("/questions"))
                .andExpect(status().isOk())
                .andExpect(view().name("question/list"))
                .andExpect(model().attributeExists("questionsPage"))
                .andExpect(model().attribute("currentPage", 0));
    }

    @Test
    @DisplayName("GET /questions/create: Hiển thị form tạo mới câu hỏi")
    void testShowCreateForm() throws Exception {
        mockMvc.perform(get("/questions/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("question/create"))
                .andExpect(model().attributeExists("questionDTO"))
                .andExpect(model().attributeExists("questionTypes"));
    }

    @Test
    @DisplayName("POST /questions/create: Thêm mới câu hỏi thành công -> Redirect về /questions")
    void testCreateQuestion_Success() throws Exception {
        mockMvc.perform(post("/questions/create")
                        .param("title", "Làm thế nào để kết nối MySQL trong Spring Boot?")
                        .param("content", "Em đã cấu hình application.properties nhưng không kết nối được.")
                        .param("questionTypeId", String.valueOf(defaultType.getId()))
                        .param("date", LocalDate.now().toString())
                        .param("status", "WAITING"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/questions"))
                .andExpect(flash().attributeExists("successMessage"));
    }

    @Test
    @DisplayName("Edge Case: POST /questions/create với dữ liệu không hợp lệ -> Trả về form create kèm lỗi validation")
    void testCreateQuestion_ValidationError() throws Exception {
        mockMvc.perform(post("/questions/create")
                        .param("title", "") // Để trống
                        .param("content", "Ngắn") // Dưới 10 ký tự
                        .param("date", LocalDate.now().toString()))
                .andExpect(status().isOk())
                .andExpect(view().name("question/create"))
                .andExpect(model().attributeHasFieldErrors("questionDTO", "title", "content", "questionTypeId"));
    }

    @Test
    @DisplayName("GET /questions/{id}: Xem chi tiết câu hỏi")
    void testViewDetail() throws Exception {
        Question q = questionRepository.save(Question.builder()
                .title("Chi tiết câu hỏi")
                .content("Nội dung chi tiết câu hỏi cần kiểm tra")
                .questionType(defaultType)
                .date(LocalDate.now())
                .status(QuestionStatus.WAITING)
                .build());

        mockMvc.perform(get("/questions/" + q.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("question/detail"))
                .andExpect(model().attributeExists("question"))
                .andExpect(model().attribute("question", hasProperty("title", is("Chi tiết câu hỏi"))));
    }

    @Test
    @DisplayName("POST /questions/{id}/edit: Cập nhật thành công")
    void testUpdateQuestion_Success() throws Exception {
        Question q = questionRepository.save(Question.builder()
                .title("Tiêu đề cũ trước khi sửa")
                .content("Nội dung ban đầu của câu hỏi")
                .questionType(defaultType)
                .date(LocalDate.now())
                .status(QuestionStatus.WAITING)
                .build());

        mockMvc.perform(post("/questions/" + q.getId() + "/edit")
                        .param("title", "Tiêu đề mới sau khi chỉnh sửa")
                        .param("content", "Nội dung câu hỏi mới đã được cập nhật")
                        .param("answer", "Câu trả lời từ phía giảng viên")
                        .param("questionTypeId", String.valueOf(defaultType.getId()))
                        .param("date", LocalDate.now().toString())
                        .param("status", "RESPONSE"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/questions"))
                .andExpect(flash().attributeExists("successMessage"));
    }

    @Test
    @DisplayName("POST /questions/{id}/delete: Xóa câu hỏi thành công -> Redirect về /questions")
    void testDeleteQuestion() throws Exception {
        Question q = questionRepository.save(Question.builder()
                .title("Câu hỏi sắp bị xóa")
                .content("Nội dung câu hỏi chuẩn bị xóa")
                .questionType(defaultType)
                .date(LocalDate.now())
                .status(QuestionStatus.WAITING)
                .build());

        mockMvc.perform(post("/questions/" + q.getId() + "/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/questions"))
                .andExpect(flash().attributeExists("successMessage"));
    }

    @Test
    @DisplayName("Phân trang: Sang trang tiếp theo và trở lại trang trước")
    void testPagination_NextAndPreviousPage() throws Exception {
        for (int i = 1; i <= 7; i++) {
            questionRepository.save(Question.builder()
                    .title("Câu hỏi phân trang số " + i)
                    .content("Nội dung chi tiết cho câu hỏi số " + i)
                    .questionType(defaultType)
                    .date(LocalDate.now().minusDays(i))
                    .status(QuestionStatus.WAITING)
                    .build());
        }

        // 1. Sang trang (page = 1 với size = 3)
        mockMvc.perform(get("/questions").param("page", "1").param("size", "3"))
                .andExpect(status().isOk())
                .andExpect(view().name("question/list"))
                .andExpect(model().attribute("currentPage", 1))
                .andExpect(model().attribute("totalPages", 3));

        // 2. Trở lại trang (page = 0 với size = 3)
        mockMvc.perform(get("/questions").param("page", "0").param("size", "3"))
                .andExpect(status().isOk())
                .andExpect(view().name("question/list"))
                .andExpect(model().attribute("currentPage", 0))
                .andExpect(model().attribute("totalPages", 3));
    }

    @Test
    @DisplayName("Phân trang: Nếu ở phần tử thứ 0 thì không thể bị giảm nữa (page < 0 -> chuyển về 0)")
    void testPagination_AtFirstPage_CannotDecreaseBelowZero() throws Exception {
        for (int i = 1; i <= 5; i++) {
            questionRepository.save(Question.builder()
                    .title("Câu hỏi boundary " + i)
                    .content("Nội dung boundary " + i)
                    .questionType(defaultType)
                    .date(LocalDate.now())
                    .status(QuestionStatus.WAITING)
                    .build());
        }

        // Yêu cầu page = -1 (cố giảm khi đang ở trang 0)
        mockMvc.perform(get("/questions").param("page", "-1").param("size", "2"))
                .andExpect(status().isOk())
                .andExpect(view().name("question/list"))
                .andExpect(model().attribute("currentPage", 0)); // Vẫn giữ ở trang 0

        // Yêu cầu page = -99
        mockMvc.perform(get("/questions").param("page", "-99").param("size", "2"))
                .andExpect(status().isOk())
                .andExpect(view().name("question/list"))
                .andExpect(model().attribute("currentPage", 0));
    }

    @Test
    @DisplayName("Phân trang: Nếu ở cuối trang thì không thể tăng nữa (page >= totalPages -> giữ ở trang cuối)")
    void testPagination_AtLastPage_CannotIncreasePastTotalPages() throws Exception {
        // 7 phần tử với size = 3 => có 3 trang: 0, 1, 2. Trang cuối cùng là index 2.
        for (int i = 1; i <= 7; i++) {
            questionRepository.save(Question.builder()
                    .title("Câu hỏi cuối trang " + i)
                    .content("Nội dung câu hỏi cuối trang " + i)
                    .questionType(defaultType)
                    .date(LocalDate.now().minusDays(i))
                    .status(QuestionStatus.WAITING)
                    .build());
        }

        // Ở trang cuối cùng hợp lệ (page = 2)
        mockMvc.perform(get("/questions").param("page", "2").param("size", "3"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("currentPage", 2))
                .andExpect(model().attribute("totalPages", 3));

        // Cố tăng tiếp sang page = 3 (vượt quá trang cuối) -> Hệ thống giữ ở trang cuối (index 2)
        mockMvc.perform(get("/questions").param("page", "3").param("size", "3"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("currentPage", 2))
                .andExpect(model().attribute("totalPages", 3));

        // Cố tăng vượt xa page = 100 -> Hệ thống vẫn giữ ở trang cuối (index 2)
        mockMvc.perform(get("/questions").param("page", "100").param("size", "3"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("currentPage", 2))
                .andExpect(model().attribute("totalPages", 3));
    }
}
