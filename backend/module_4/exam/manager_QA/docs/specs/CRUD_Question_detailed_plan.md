# Specification: CRUD Question Web Application

## 1. Mục tiêu
Triển khai chức năng CRUD Question theo tài liệu `specs/CRUD_Question_spec.md` và tuân thủ `AGENTS.md`:
- Phân trang ở Backend (Spring Data `Pageable`).
- Sắp xếp: Ưu tiên trạng thái `WAITING` trước, sau đó sắp xếp theo `date` giảm dần (mới nhất).
- Tìm kiếm theo `title`, `content` (nhập câu hỏi), và `status`.
- Xóa câu hỏi: click mở modal cảnh báo Bootstrap 5, xác nhận xóa và hiển thị lại danh sách câu hỏi kèm thông báo toast / flash attribute.
- Kiểm thử đầy đủ: Edge cases và 2 trạng thái `WAITING`, `RESPONSE`.

## 2. Kiến trúc & Cấu trúc thư mục
Package: `com.example.manager_qa`
- `entity/`
  - `QuestionType`: `id` (int/Long), `name` (String, unique)
  - `QuestionStatus`: Enum (`WAITING`, `RESPONSE`)
  - `Question`: `id`, `title`, `content`, `answer`, `questionType` (@ManyToOne), `date` (LocalDate), `status` (QuestionStatus)
- `dto/`
  - `QuestionRequestDTO`: DTO dùng để validate form tạo mới / chỉnh sửa
- `repository/`
  - `IQuestionTypeRepository`
  - `IQuestionRepository`
- `service/`
  - `IQuestionTypeService`
  - `QuestionTypeServiceImpl`
  - `IQuestionService`
  - `QuestionServiceImpl`
- `controller/`
  - `QuestionController`
- `init/`
  - `DataInitializer`: Khởi tạo dữ liệu mẫu nếu DB trống (các loại câu hỏi và câu hỏi mẫu ở 2 trạng thái WAITING & RESPONSE)
- `templates/`
  - `layout.html`
  - `question/list.html`
  - `question/create.html`
  - `question/edit.html`
  - `question/detail.html`
- `test/`
  - `QuestionServiceTest` / `QuestionControllerTest`
