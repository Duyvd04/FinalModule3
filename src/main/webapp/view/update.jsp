<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center mb-4">Chỉnh sửa thông tin Người Thuê</h1>

    <div id="editForm" style="display: block;">
        <form action="/dd/update?roomID=<c:out value="${room.roomID}"/>" method="post">
            <div class="form-group mb-3">
                <label for="tenantName">Tên người thuê:</label>
                <input type="text" class="form-control" id="tenantName" name="tenantName" value="<c:out value="${room.tenantName}"/>"  required>
            </div>

            <div class="form-group mb-3">
                <label for="phoneNumber">Số điện thoại:</label>
                <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" value="<c:out value="${room.phoneNumber}"/>"required>
            </div>

            <div class="form-group mb-3">
                <label for="rentalStartDate">Ngày bắt đầu thuê:</label>
                <input type="date" class="form-control" id="rentalStartDate" name="rentalStartDate" value="<c:out value="${room.rentalStartDate}"/>"  required>
            </div>

            <div class="form-group mb-3">
                <label for="paymentMethodID">Hình thức thanh toán:</label>
                <select class="form-control" id="paymentMethodID" name="paymentMethodID" required>
                    <option value="1" ${room.paymentMethodID == 1 ? 'selected' : ''}>1: Thanh toán theo tháng</option>
                    <option value="2" ${room.paymentMethodID == 2 ? 'selected' : ''}>2: Thanh toán theo quý</option>
                    <option value="3" ${room.paymentMethodID == 3 ? 'selected' : ''}>3: Thanh toán theo năm</option>
                </select>

            </div>


            <div class="form-group mb-3">
                <label for="notes">Ghi chú:</label>
                <textarea class="form-control" id="notes" name="notes"><c:out value="${room.notes}"/></textarea>
            </div>


            <button type="submit" class="btn btn-primary">Cập nhật</button>
            <a href="${pageContext.request.contextPath}/dd/list" class="btn btn-secondary">Hủy</a>
        </form>
    </div>
</div>
</body>
</html>
