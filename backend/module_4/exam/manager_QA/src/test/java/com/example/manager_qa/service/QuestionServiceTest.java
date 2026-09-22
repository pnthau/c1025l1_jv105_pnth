package com.example.manager_qa.service;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class QuestionServiceTest {

    @Autowired
    private IQuestionService questionService;

    @Autowired
    private IQuestionRepository questionRepository;

    @Autowired
    private IQuestionTypeRepository questionTypeRepository;

    private QuestionType defaultType;

    @BeforeEach
    void setUp() {
        questionRepository.deleteAll();
        questionTypeRepository.deleteAll();

        defaultType = questionTypeRepository.save(
                QuestionType.builder().name("Khóa học & Lộ trình").build()
        );
    }

    @Test
    @DisplayName("Thêm mới câu hỏi thành công với trạng thái WAITING")
    void testCreateQuestion_Success() {
        QuestionRequestDTO dto = QuestionRequestDTO.builder()
                .title("Tìm hiểu về Spring Security")
                .content("Cho em hỏi khi nào thì học đến phần phân quyền Spring Security?")
                .questionTypeId(defaultType.getId())
                .date(LocalDate.now())
                .status(QuestionStatus.WAITING)
                .build();

        Question created = questionService.create(dto);

        assertNotNull(created.getId());
        assertEquals("Tìm hiểu về Spring Security", created.getTitle());
        assertEquals(QuestionStatus.WAITING, created.getStatus());
        assertEquals(defaultType.getId(), created.getQuestionType().getId());
        assertNull(created.getAnswer());
    }

    @Test
    @DisplayName("Edge Case: Thêm câu hỏi với QuestionType không tồn tại -> Báo lỗi")
    void testCreateQuestion_InvalidType() {
        QuestionRequestDTO dto = QuestionRequestDTO.builder()
                .title("Tiêu đề hợp lệ")
                .content("Nội dung câu hỏi hợp lệ dài hơn 10 ký tự")
                .questionTypeId(999999)
                .date(LocalDate.now())
                .status(QuestionStatus.WAITING)
                .build();

        assertThrows(IllegalArgumentException.class, () -> questionService.create(dto));
    }

    @Test
    @DisplayName("Tìm kiếm câu hỏi theo ID tồn tại và không tồn tại")
    void testFindById() {
        Question q = questionRepository.save(Question.builder()
                .title("Câu hỏi kiểm thử ID")
                .content("Nội dung câu hỏi kiểm thử")
                .questionType(defaultType)
                .date(LocalDate.now())
                .status(QuestionStatus.WAITING)
                .build());

        Optional<Question> found = questionService.findById(q.getId());
        assertTrue(found.isPresent());
        assertEquals(q.getTitle(), found.get().getTitle());

        Optional<Question> notFound = questionService.findById(999999);
        assertFalse(notFound.isPresent());
    }

    @Test
    @DisplayName("Cập nhật câu hỏi và phản hồi câu trả lời, chuyển trạng thái sang RESPONSE")
    void testUpdateQuestion_Success() {
        Question q = questionRepository.save(Question.builder()
                .title("Câu hỏi cần giải đáp")
                .content("Nội dung ban đầu của câu hỏi")
                .questionType(defaultType)
                .date(LocalDate.now().minusDays(1))
                .status(QuestionStatus.WAITING)
                .build());

        QuestionRequestDTO updateDto = QuestionRequestDTO.builder()
                .title("Câu hỏi đã được cập nhật tiêu đề")
                .content("Nội dung câu hỏi sau cập nhật")
                .answer("Đây là câu trả lời chính thức từ trung tâm.")
                .questionTypeId(defaultType.getId())
                .date(LocalDate.now())
                .status(QuestionStatus.RESPONSE)
                .build();

        Question updated = questionService.update(q.getId(), updateDto);

        assertEquals("Câu hỏi đã được cập nhật tiêu đề", updated.getTitle());
        assertEquals("Đây là câu trả lời chính thức từ trung tâm.", updated.getAnswer());
        assertEquals(QuestionStatus.RESPONSE, updated.getStatus());
    }

    @Test
    @DisplayName("Edge Case: Cập nhật câu hỏi không tồn tại -> Báo lỗi")
    void testUpdateQuestion_NotFound() {
        QuestionRequestDTO dto = QuestionRequestDTO.builder()
                .title("Tiêu đề không tồn tại")
                .content("Nội dung không tồn tại")
                .questionTypeId(defaultType.getId())
                .build();

        assertThrows(IllegalArgumentException.class, () -> questionService.update(999999, dto));
    }

    @Test
    @DisplayName("Xóa câu hỏi thành công và Edge case xóa ID không tồn tại")
    void testDeleteQuestion() {
        Question q = questionRepository.save(Question.builder()
                .title("Câu hỏi cần xóa")
                .content("Nội dung câu hỏi sẽ bị xóa")
                .questionType(defaultType)
                .date(LocalDate.now())
                .status(QuestionStatus.WAITING)
                .build());

        Integer id = q.getId();
        questionService.deleteById(id);

        assertFalse(questionRepository.existsById(id));
        assertThrows(IllegalArgumentException.class, () -> questionService.deleteById(id));
    }

    @Test
    @DisplayName("Tiêu chí sắp xếp: Trạng thái WAITING luôn đứng trước RESPONSE, sau đó theo date mới nhất")
    void testSearchAndSort_StatusWaitingFirst() {
        LocalDate today = LocalDate.now();

        // 1. RESPONSE ngày hôm nay (date mới hơn nhưng đã phản hồi)
        Question q1 = questionRepository.save(Question.builder()
                .title("Câu hỏi Response mới nhất")
                .content("Nội dung câu hỏi 1")
                .questionType(defaultType)
                .date(today)
                .status(QuestionStatus.RESPONSE)
                .build());

        // 2. WAITING ngày hôm qua (date cũ hơn nhưng đang chờ)
        Question q2 = questionRepository.save(Question.builder()
                .title("Câu hỏi Waiting ngày hôm qua")
                .content("Nội dung câu hỏi 2")
                .questionType(defaultType)
                .date(today.minusDays(1))
                .status(QuestionStatus.WAITING)
                .build());

        // 3. WAITING ngày hôm nay (mới nhất và đang chờ)
        Question q3 = questionRepository.save(Question.builder()
                .title("Câu hỏi Waiting hôm nay")
                .content("Nội dung câu hỏi 3")
                .questionType(defaultType)
                .date(today)
                .status(QuestionStatus.WAITING)
                .build());

        Page<Question> page = questionService.searchAndSortQuestions(null, null, null, PageRequest.of(0, 10));

        assertEquals(3, page.getTotalElements());

        // Thứ tự mong muốn: q3 (WAITING, today), q2 (WAITING, yesterday), q1 (RESPONSE, today)
        assertEquals(q3.getId(), page.getContent().get(0).getId(), "WAITING hôm nay phải đứng đầu");
        assertEquals(q2.getId(), page.getContent().get(1).getId(), "WAITING hôm qua phải đứng thứ hai");
        assertEquals(q1.getId(), page.getContent().get(2).getId(), "RESPONSE phải đứng sau tất cả WAITING");
    }

    @Test
    @DisplayName("Kiểm thử 2 trạng thái: Tìm kiếm lọc riêng theo WAITING và theo RESPONSE")
    void testSearchByStatus_WaitingAndResponse() {
        questionRepository.save(Question.builder()
                .title("Câu hỏi 1 Waiting")
                .content("Nội dung 1")
                .questionType(defaultType)
                .date(LocalDate.now())
                .status(QuestionStatus.WAITING)
                .build());

        questionRepository.save(Question.builder()
                .title("Câu hỏi 2 Response")
                .content("Nội dung 2")
                .questionType(defaultType)
                .date(LocalDate.now())
                .status(QuestionStatus.RESPONSE)
                .build());

        // Tìm theo WAITING
        Page<Question> waitingPage = questionService.searchAndSortQuestions(null, null, QuestionStatus.WAITING, PageRequest.of(0, 10));
        assertEquals(1, waitingPage.getTotalElements());
        assertEquals(QuestionStatus.WAITING, waitingPage.getContent().get(0).getStatus());

        // Tìm theo RESPONSE
        Page<Question> responsePage = questionService.searchAndSortQuestions(null, null, QuestionStatus.RESPONSE, PageRequest.of(0, 10));
        assertEquals(1, responsePage.getTotalElements());
        assertEquals(QuestionStatus.RESPONSE, responsePage.getContent().get(0).getStatus());
    }

    @Test
    @DisplayName("Tìm kiếm theo Title và Content")
    void testSearchByTitleAndContent() {
        questionRepository.save(Question.builder()
                .title("Tìm hiểu về Docker container")
                .content("Làm thế nào để chạy MySQL trên Docker?")
                .questionType(defaultType)
                .date(LocalDate.now())
                .status(QuestionStatus.WAITING)
                .build());

        questionRepository.save(Question.builder()
                .title("Khái niệm Microservice")
                .content("Học viên hỏi về kiến trúc Microservices")
                .questionType(defaultType)
                .date(LocalDate.now())
                .status(QuestionStatus.RESPONSE)
                .build());

        // Tìm theo title "Docker"
        Page<Question> titleMatch = questionService.searchAndSortQuestions("Docker", null, null, PageRequest.of(0, 10));
        assertEquals(1, titleMatch.getTotalElements());
        assertTrue(titleMatch.getContent().get(0).getTitle().contains("Docker"));

        // Tìm theo content "MySQL"
        Page<Question> contentMatch = questionService.searchAndSortQuestions(null, "MySQL", null, PageRequest.of(0, 10));
        assertEquals(1, contentMatch.getTotalElements());
        assertTrue(contentMatch.getContent().get(0).getContent().contains("MySQL"));

        // Edge case: Tìm không thấy
        Page<Question> noMatch = questionService.searchAndSortQuestions("KhongTonTai", null, null, PageRequest.of(0, 10));
        assertEquals(0, noMatch.getTotalElements());
    }

    @Test
    @DisplayName("Kiểm thử phân trang Service: Sang trang tiếp theo và trở lại trang trước")
    void testPagination_NextAndPreviousPage() {
        for (int i = 1; i <= 6; i++) {
            questionRepository.save(Question.builder()
                    .title("Câu hỏi phân trang Service " + i)
                    .content("Nội dung câu hỏi phân trang " + i)
                    .questionType(defaultType)
                    .date(LocalDate.now().minusDays(i))
                    .status(QuestionStatus.WAITING)
                    .build());
        }

        // Trang đầu tiên (page = 0, size = 2)
        Page<Question> page0 = questionService.searchAndSortQuestions(null, null, null, PageRequest.of(0, 2));
        assertEquals(0, page0.getNumber());
        assertEquals(2, page0.getNumberOfElements());
        assertEquals(3, page0.getTotalPages());
        assertEquals(6, page0.getTotalElements());

        // Sang trang tiếp theo (page = 1, size = 2)
        Page<Question> page1 = questionService.searchAndSortQuestions(null, null, null, PageRequest.of(1, 2));
        assertEquals(1, page1.getNumber());
        assertEquals(2, page1.getNumberOfElements());
        assertNotEquals(page0.getContent().get(0).getId(), page1.getContent().get(0).getId());

        // Trở lại trang trước (page = 0, size = 2)
        Page<Question> page0Back = questionService.searchAndSortQuestions(null, null, null, PageRequest.of(0, 2));
        assertEquals(0, page0Back.getNumber());
        assertEquals(page0.getContent().get(0).getId(), page0Back.getContent().get(0).getId());
    }
}
