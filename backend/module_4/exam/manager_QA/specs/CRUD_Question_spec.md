# Use case: CRUD Question
(Tham chiếu: `@file` AGENTS.md)
## Context
- Frontend:
  - list page hiển thị table thông tin câu hỏi
    - Table hiện thị :  STT, Quetion Type, Title, Date, status 
    - Search button: có các input  tiêu đề, nhập câu hỏi, nút tìm kiếm.
    - Delete button :click hiện modal cảnh báo người dùng. hiện thị lại danh sách câu hỏi.
    - Detail : click vào title hiện thỉ modal.
    - Paginate ở backend
- Backend :
  - Có đầy đủ CRUD
  - Hiển thị danh sách  trên tiêu chí sort status WAITING, date mới nhất.
  - Search: theo status và title
## Test case :
  - Test toàn bộ các chức năng trên edge case và status thì test 2 trạng thái của status
  - Test được paginate sang trang, trở lại trang, nếu ở phần tử thứ 0 không thể bị giảm nữa, nếu ở cuối trang không thể tăng nữa.