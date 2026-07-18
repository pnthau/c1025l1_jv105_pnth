<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <html>

        <head>
            <title>Thêm mới mặt bằng</title>
            <link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet">
            <style>
                .error {
                    color: red;
                    font-size: 0.9em;
                    margin-top: 5px;
                    display: block;
                }
            </style>
        </head>

    <body>
        <div class="container mt-5 border p-0 rounded shadow-sm bg-white" style="max-width: 800px; overflow: hidden;">
            <div class="bg-primary text-white text-center py-3 mb-4">
                <h3 class="m-0 text-uppercase fw-bold">THÊM MỚI MẶT BẰNG</h3>
            </div>

            <c:if test="${not empty errors.system}">
                <div class="alert alert-danger mx-4">${errors.system}</div>
            </c:if>

            <form action="${pageContext.request.contextPath}/rental-spaces?action=create" method="post"
                id="createForm" onsubmit="return validateForm(event)" class="px-5 pb-5 pt-3">
                
                <div class="row mb-4 align-items-center">
                    <label class="col-sm-3 col-form-label fw-bold">Mã mặt bằng <span class="text-danger">*</span></label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" name="code" id="code" value="${rentalSpace.code}" 
                               placeholder="Nhập mã mặt bằng" required pattern="^[A-Z0-9]{3}-[A-Z0-9]{2}-[A-Z0-9]{2}$" 
                               title="Định dạng XXX-XX-XX (X là số hoặc chữ hoa)" oninput="validateCode()">
                        <span class="error" id="codeError">
                            <c:if test="${not empty errors.code}">${errors.code}</c:if>
                        </span>
                    </div>
                </div>

                <div class="row mb-4 align-items-center">
                    <label class="col-sm-3 col-form-label fw-bold">Diện tích <span class="text-danger">*</span></label>
                    <div class="col-sm-9">
                        <input type="number" step="0.01" class="form-control" name="area" id="area"
                               value="${rentalSpace.area}" placeholder="Nhập diện tích" required min="0" 
                               title="Diện tích phải lớn hơn 20m2" oninput="validateArea()">
                        <span class="error" id="areaError">
                            <c:if test="${not empty errors.area}">${errors.area}</c:if>
                        </span>
                    </div>
                </div>

                <div class="row mb-4 align-items-center">
                    <label class="col-sm-3 col-form-label fw-bold">Trạng thái <span class="text-danger">*</span></label>
                    <div class="col-sm-9">
                        <div class="row">
                            <div class="col-md-6">
                                <select class="form-select" name="status" id="status" required>
                                    <option value="Trống" ${rentalSpace.status=='Trống' ? 'selected' : '' }>Trống</option>
                                    <option value="Hạ tầng" ${rentalSpace.status=='Hạ tầng' ? 'selected' : '' }>Hạ tầng</option>
                                    <option value="Đầy đủ" ${rentalSpace.status=='Đầy đủ' ? 'selected' : '' }>Đầy đủ</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row mb-4 align-items-center">
                    <label class="col-sm-3 col-form-label fw-bold">Tầng <span class="text-danger">*</span></label>
                    <div class="col-sm-9">
                        <div class="row">
                            <div class="col-md-4">
                                <select class="form-select" name="floor" id="floor" required onchange="validateFloor()">
                                    <c:forEach var="i" begin="1" end="15">
                                        <option value="${i}" ${rentalSpace.floor == i ? 'selected' : ''}>${i}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <span class="error" id="floorError">
                            <c:if test="${not empty errors.floor}">${errors.floor}</c:if>
                        </span>
                    </div>
                </div>

                <div class="row mb-4 align-items-center">
                    <label class="col-sm-3 col-form-label fw-bold">Loại mặt bằng <span class="text-danger">*</span></label>
                    <div class="col-sm-9">
                        <div class="row">
                            <div class="col-md-6">
                                <select class="form-select" name="type" id="type" required>
                                    <option value="Văn phòng chia sẻ" ${rentalSpace.type=='Văn phòng chia sẻ' ? 'selected' : ''
                                        }>Văn phòng chia sẻ</option>
                                    <option value="Văn phòng trọn gói" ${rentalSpace.type=='Văn phòng trọn gói' ? 'selected'
                                        : '' }>Văn phòng trọn gói</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row mb-4">
                    <label class="col-sm-3 col-form-label fw-bold pt-0 mt-2">Mô tả chi tiết</label>
                    <div class="col-sm-9">
                        <textarea class="form-control" rows="4" placeholder="Nhập mô tả chi tiết"></textarea>
                    </div>
                </div>

                <div class="row mb-4 align-items-center">
                    <label class="col-sm-3 col-form-label fw-bold">Giá cho thuê <span class="text-danger">*</span></label>
                    <div class="col-sm-9">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="input-group">
                                    <input type="number" step="0.01" class="form-control" name="price" id="price"
                                           value="${rentalSpace.price}" placeholder="Nhập giá" required min="0" 
                                           title="Giá tiền phải lớn hơn 1.000.000 VNĐ" oninput="validatePrice()">
                                    <span class="input-group-text">VNĐ</span>
                                </div>
                            </div>
                        </div>
                        <span class="error" id="priceError">
                            <c:if test="${not empty errors.price}">${errors.price}</c:if>
                        </span>
                    </div>
                </div>

                <div class="text-center mt-5">
                    <button type="submit" class="btn btn-success px-5 py-2 me-3">Lưu</button>
                    <a href="${pageContext.request.contextPath}/rental-spaces" class="btn btn-secondary px-5 py-2">Hủy</a>
                </div>
            </form>
        </div>

        <script>
            function validateCode() {
                const code = document.getElementById('code').value;
                const codeRegex = /^[A-Z0-9]{3}-[A-Z0-9]{2}-[A-Z0-9]{2}$/;
                const errorSpan = document.getElementById('codeError');
                if (!code || !codeRegex.test(code)) {
                    errorSpan.innerText = "Mã mặt bằng không đúng định dạng XXX-XX-XX (X là số hoặc chữ hoa)";
                    return false;
                } else {
                    errorSpan.innerText = "";
                    return true;
                }
            }

            function validateArea() {
                const area = parseFloat(document.getElementById('area').value);
                const errorSpan = document.getElementById('areaError');
                if (isNaN(area) || area <= 20) {
                    errorSpan.innerText = "Diện tích phải lớn hơn 20m2";
                    return false;
                } else {
                    errorSpan.innerText = "";
                    return true;
                }
            }

            function validateFloor() {
                const floor = parseInt(document.getElementById('floor').value);
                const errorSpan = document.getElementById('floorError');
                if (isNaN(floor) || floor < 1 || floor > 15) {
                    errorSpan.innerText = "Tầng phải từ 1 đến 15";
                    return false;
                } else {
                    errorSpan.innerText = "";
                    return true;
                }
            }

            function validatePrice() {
                const price = parseFloat(document.getElementById('price').value);
                const errorSpan = document.getElementById('priceError');
                if (isNaN(price) || price <= 1000000) {
                    errorSpan.innerText = "Giá tiền phải lớn hơn 1.000.000 VNĐ";
                    return false;
                } else {
                    errorSpan.innerText = "";
                    return true;
                }
            }

            function validateForm(event) {
                let isCodeValid = validateCode();
                let isAreaValid = validateArea();
                let isFloorValid = validateFloor();
                let isPriceValid = validatePrice();

                if (!isCodeValid || !isAreaValid || !isFloorValid || !isPriceValid) {
                    event.preventDefault(); // Ngăn chặn submit form
                    return false;
                }
                return true;
            }
        </script>
    </body>

    </html>