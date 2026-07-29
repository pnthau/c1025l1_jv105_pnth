# Thêm thông tin cho một mặt bằng cho thuê
-Mã mặt bằng phải  đúng định dạng XXX-XX-XX với X là số hoặc các ký tự alphabet viết hoa. (1 điểm)
-area > 20m2 
-status 3 giá trị: Trống, Hạ tầng, Đầy đủ 
-Tầng: 15 tầng tất cả 
-Loại mặt bằng bao gồm 2 giá trị: Văn phòng chia sẻ, Văn phòng trọn gói.
- price > 1.000.000 VNĐ


Sau khi thêm mới thành công thì dữ liệu sẽ được lưu vào cơ sở dữ liệu và hiển thị trang danh sách
# Hiển thị danh sách Mặt bằng đang cho thuê tại tòa nhà
- Sắp xếp tăng dần theo Diện tích.
Cho phép tìm kiếm theo 2 điều kiện Loại Mặt bằng,Tầng

# Xóa thông tin từ danh sách thông tin mặt bằng
- thông báo bằng modal "“Bạn có chắc chắn muốn xóa mặt bằng với mã số XXX không?"
    Nếu người dùng  xác nhận Yes thì thực hiện xóa và quay trở lại danh sách mặt bằng đã cập nhật sau khi xóa.
-Nếu người dùng xác nhận chọn No thì quay lại màn hình danh sách mặt bằng.

# Yêu cầu kỹ thuật bổ sung
- **Repository Layer**: Sử dụng abstract class `BaseRepository` (quản lý kết nối JDBC). `RentalSpaceRepository` kế thừa `BaseRepository` và thực thi interface `IRentalSpaceRepository`.
- **Validation**: Cần xử lý validation dữ liệu trên cả 2 phía: Backend (Java) và Frontend (bằng JS).
- **Thao tác xóa bằng Modal**: Sử dụng `async/await` trong Javascript để gửi (request) và nhận phản hồi (response) một cách bất đồng bộ khi xác nhận xóa.
