package com.example.manager_qa.init;

import com.example.manager_qa.entity.Question;
import com.example.manager_qa.entity.QuestionStatus;
import com.example.manager_qa.entity.QuestionType;
import com.example.manager_qa.repository.IQuestionRepository;
import com.example.manager_qa.repository.IQuestionTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final IQuestionTypeRepository questionTypeRepository;
    private final IQuestionRepository questionRepository;

    @Override
    public void run(String... args) {
        // Khởi tạo các loại câu hỏi nếu chưa có
        QuestionType typeHocTap = questionTypeRepository.findByName("Học tập")
                .orElseGet(() -> questionTypeRepository.save(QuestionType.builder().name("Học tập").build()));

        QuestionType typeGiaoVu = questionTypeRepository.findByName("Giáo vụ")
                .orElseGet(() -> questionTypeRepository.save(QuestionType.builder().name("Giáo vụ").build()));

        QuestionType typeKhac = questionTypeRepository.findByName("Khác")
                .orElseGet(() -> questionTypeRepository.save(QuestionType.builder().name("Khác").build()));

        QuestionType typeHocPhi = questionTypeRepository.findByName("Học phí")
                .orElseGet(() -> questionTypeRepository.save(QuestionType.builder().name("Học phí").build()));

        QuestionType typeKyThuat = questionTypeRepository.findByName("Kỹ thuật")
                .orElseGet(() -> questionTypeRepository.save(QuestionType.builder().name("Kỹ thuật").build()));

        // Nạp đủ 15 câu hỏi để phục vụ test phân trang 3 trang (mỗi trang 5 phần tử)
        if (questionRepository.count() < 15) {
            questionRepository.deleteAll();

            List<Question> questions = Arrays.asList(
                    // --- Trang 1 (khớp chính xác 100% với table.png) ---
                    Question.builder()
                            .title("Module 4: Xin demo bài AOP")
                            .content("Nhờ giảng viên gởi bài demo")
                            .answer("Bạn xem demo tại link sau đây: example.com")
                            .questionType(typeHocTap)
                            .date(LocalDate.of(2021, 1, 8))
                            .status(QuestionStatus.WAITING)
                            .build(),
                    Question.builder()
                            .title("Module 4: Nhờ GV giải thích lại Session")
                            .content("Em chưa hiểu rõ về vòng đời của Session và Cookie trong Spring Boot, nhờ thầy cô giải thích lại.")
                            .answer(null)
                            .questionType(typeHocTap)
                            .date(LocalDate.of(2021, 1, 8))
                            .status(QuestionStatus.WAITING)
                            .build(),
                    Question.builder()
                            .title("Trung Tâm: điều hòa phòng Jame bị hỏng")
                            .content("Điều hòa tại phòng thực hành Jame đang chảy nước và không mát, nhờ ban quản trị kiểm tra.")
                            .answer("Kỹ thuật đã tiếp nhận và sẽ sửa chữa vào sáng mai bạn nhé.")
                            .questionType(typeKhac)
                            .date(LocalDate.of(2021, 1, 7))
                            .status(QuestionStatus.RESPONSE)
                            .build(),
                    Question.builder()
                            .title("Module 2: Lỗi bài tập trên Jame")
                            .content("Bài tập phần OOP bài 3 trên hệ thống Jame bị lỗi chấm điểm tự động.")
                            .answer("Hệ thống Jame đã được cập nhật test case mới, bạn nộp lại bài giúp mình nhé.")
                            .questionType(typeHocTap)
                            .date(LocalDate.of(2021, 1, 6))
                            .status(QuestionStatus.RESPONSE)
                            .build(),
                    Question.builder()
                            .title("Module 3: Xác nhận lại lịch học")
                            .content("Lớp C1025L1 có học bù vào chủ nhật tuần này không ạ?")
                            .answer("Lịch học giữ nguyên như thời khóa biểu ban đầu, không có buổi bù bạn nhé.")
                            .questionType(typeGiaoVu)
                            .date(LocalDate.of(2021, 1, 5))
                            .status(QuestionStatus.RESPONSE)
                            .build(),

                    // --- Trang 2 ---
                    Question.builder()
                            .title("Module 4: Thắc mắc về Spring Data JPA")
                            .content("Làm sao để viết query tùy chỉnh với annotation @Query trong Spring Data JPA?")
                            .answer("Bạn có thể định nghĩa JPQL hoặc native query bên trong annotation @Query nhé.")
                            .questionType(typeHocTap)
                            .date(LocalDate.of(2021, 1, 4))
                            .status(QuestionStatus.RESPONSE)
                            .build(),
                    Question.builder()
                            .title("Đăng ký thi lại Module 3 Database")
                            .content("Em bị lỡ buổi thi thực hành Module 3 do ốm, muốn xin đăng ký thi lại đợt 2.")
                            .answer("Bạn vui lòng nộp giấy xác nhận của bệnh viện cho giáo vụ trước ngày 10/1 nhé.")
                            .questionType(typeGiaoVu)
                            .date(LocalDate.of(2021, 1, 3))
                            .status(QuestionStatus.RESPONSE)
                            .build(),
                    Question.builder()
                            .title("Thủ tục cấp bảng điểm tạm thời")
                            .content("Em cần bảng điểm tạm thời để nộp hồ sơ xin thực tập, thủ tục như thế nào ạ?")
                            .answer("Bạn liên hệ trực tiếp phòng Giáo vụ tầng 2 để nhận bảng điểm sau 2 ngày làm việc nhé.")
                            .questionType(typeGiaoVu)
                            .date(LocalDate.of(2021, 1, 2))
                            .status(QuestionStatus.RESPONSE)
                            .build(),
                    Question.builder()
                            .title("Xin cấp lại tài khoản hệ thống Jame")
                            .content("Em bị quên mật khẩu tài khoản học tập Jame, nhờ trung tâm reset giúp.")
                            .answer("Đã reset mật khẩu về mặc định và gửi thông tin vào email của bạn.")
                            .questionType(typeKhac)
                            .date(LocalDate.of(2021, 1, 1))
                            .status(QuestionStatus.RESPONSE)
                            .build(),
                    Question.builder()
                            .title("Đóng học phí kỳ 2 qua ngân hàng")
                            .content("Em đã chuyển khoản học phí đợt 2 nhưng chưa thấy hệ thống cập nhật xác nhận.")
                            .answer("Kế toán đã đối soát và cập nhật trạng thái học phí thành công cho bạn rồi nhé.")
                            .questionType(typeHocPhi)
                            .date(LocalDate.of(2020, 12, 30))
                            .status(QuestionStatus.RESPONSE)
                            .build(),

                    // --- Trang 3 ---
                    Question.builder()
                            .title("Module 4: Cách cấu hình Validation trong Spring")
                            .content("Annotation @Valid và BindingResult dùng như thế nào trong Controller?")
                            .answer("BindingResult phải được đặt ngay sau tham số có gắn @Valid bạn nhé.")
                            .questionType(typeHocTap)
                            .date(LocalDate.of(2020, 12, 28))
                            .status(QuestionStatus.RESPONSE)
                            .build(),
                    Question.builder()
                            .title("Mượn sách thư viện chuyên ngành Java")
                            .content("Em muốn mượn cuốn Clean Code và Effective Java tại tủ sách trung tâm.")
                            .answer("Sách hiện có sẵn tại thư viện tầng 1, bạn có thể đến quầy lễ tân để mượn.")
                            .questionType(typeKhac)
                            .date(LocalDate.of(2020, 12, 25))
                            .status(QuestionStatus.RESPONSE)
                            .build(),
                    Question.builder()
                            .title("Quy định bảo lưu khóa học tối đa bao lâu")
                            .content("Em có dự định đi công tác 2 tháng, em có thể xin bảo lưu khóa học không?")
                            .answer("Thời gian bảo lưu tối đa theo quy chế là 6 tháng, bạn làm đơn gửi giáo vụ duyệt nhé.")
                            .questionType(typeGiaoVu)
                            .date(LocalDate.of(2020, 12, 20))
                            .status(QuestionStatus.RESPONSE)
                            .build(),
                    Question.builder()
                            .title("Lỗi mạng wifi tại tầng 3 trung tâm")
                            .content("Wifi CodeGym_Guest tầng 3 thường xuyên bị mất kết nối vào buổi chiều.")
                            .answer("IT đã kiểm tra bộ phát Access Point và nâng cấp băng thông mạng tầng 3.")
                            .questionType(typeKyThuat)
                            .date(LocalDate.of(2020, 12, 18))
                            .status(QuestionStatus.RESPONSE)
                            .build(),
                    Question.builder()
                            .title("Chương trình học bổng khóa nâng cao Spring Cloud")
                            .content("Học viên xuất sắc có được học bổng khóa microservices tiếp theo không ạ?")
                            .answer("Top 3 học viên có điểm đồ án cao nhất sẽ nhận học bổng 50% khóa học nâng cao nhé.")
                            .questionType(typeHocPhi)
                            .date(LocalDate.of(2020, 12, 15))
                            .status(QuestionStatus.RESPONSE)
                            .build()
            );

            questionRepository.saveAll(questions);
        }
    }
}
