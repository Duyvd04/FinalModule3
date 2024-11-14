<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh sách Người dùng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="../view/styles.css">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center mb-4">Danh sách Người dùng</h1>
    <!-- Button trigger modal -->
    <button type="button" class="btn btn-success mb-3" data-bs-toggle="modal" data-bs-target="#addUserModal">
        Thêm mới
    </button>

    <!-- Search Form -->
    <form class="d-flex mb-4" action="${pageContext.request.contextPath}/dd/find" method="get">
        <input class="form-control me-2" type="search" name="keyword" placeholder="Tìm kiếm" aria-label="Search">
        <button class="btn btn-outline-primary" type="submit">Tìm kiếm</button>
    </form>

    <div class="alert alert-info">
        Tổng số người dùng: <c:out value="${data.size()}"/>
    </div>
    <table class="table table-striped table-bordered">
        <thead class="table-dark">
        <tr>
            <th>Mã phòng trọ</th>
            <th>Tên người thuê trọ</th>
            <th>Số điện thoại</th>
            <th>Ngày bắt đầu thuê</th>
            <th>Hình thức Thanh toán</th>
            <th>Ghi chú </th>
            <th>Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${data}" var="d">
            <tr>
                <td><c:out value="${d.roomID}"/></td>
                <td><c:out value="${d.tenantName}"/></td>
                <td><c:out value="${d.phoneNumber}"/></td>
                <td><c:out value="${d.rentalStartDate}"/></td>
                <td><c:out value="${d.paymentMethodName}"/></td>
                <td><c:out value="${d.notes}"/></td>
                <td>
                    <a class="btn btn-danger btn-sm" onclick="return confirm('Bạn có chắc chắn muốn xóa?')"
                       href="${pageContext.request.contextPath}/dd/delete?roomID=${d.roomID}">Xóa</a>
                    <a class="btn btn-primary btn-sm" href="${pageContext.request.contextPath}/dd/update?roomID=${d.roomID}">Sửa</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div class="modal fade" id="addUserModal" tabindex="-1" aria-labelledby="addUserModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="${pageContext.request.contextPath}/dd/create" method="post">
                <div class="modal-header">
                    <h5 class="modal-title" id="addUserModalLabel">Thêm Người Thuê Trọ</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="mb-3">
                        <label for="tenantName" class="form-label">Tên người thuê trọ</label>
                        <input type="text" class="form-control" id="tenantName" name="tenantName" required
                               minlength="5" maxlength="50"
                               pattern="^[A-Za-zÀ-ỹ\s]{5,50}$"
                               title="Tên người thuê trọ không được chứa số, kí tự đặc biệt và phải có độ dài từ 5 đến 50 kí tự.">
                    </div>

                    <div class="mb-3">
                        <label for="phoneNumber" class="form-label">Số điện thoại</label>
                        <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" maxlength="10" required oninput="validatePhoneNumber(this)">
                    </div>
                    <div class="mb-3">
                        <label for="rentalStartDate" class="form-label">Ngày bắt đầu thuê</label>
                        <input type="date" class="form-control" id="rentalStartDate" name="rentalStartDate" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Hình thức Thanh toán</label>
                        <select class="form-control" name="paymentMethodID" required>
                            <c:if test="${not empty result}">
                                <c:forEach var="d" items="${result}">
                                    <option value="${d.paymentMethodID}">${d.paymentMethodName}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="notes" class="form-label">Ghi chú</label>
                        <textarea class="form-control" id="notes" name="notes" maxlength="200"></textarea>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                    <button type="submit" class="btn btn-primary">Lưu</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<script>
    function validatePhoneNumber(input) {
        input.value = input.value.replace(/[^0-9]/g, '');
    }
</script>
</body>
</html>
