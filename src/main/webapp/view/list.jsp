<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh sách Người dùng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center mb-4">Danh sách Người dùng</h1>
    <a class="btn btn-success mb-3" href="/tt/create">Thêm mới</a>

    <!-- Search Form -->
    <form class="d-flex mb-4" action="${pageContext.request.contextPath}/dd/find" method="get">
        <input class="form-control me-2" type="search" name="query" placeholder="Tìm kiếm người dùng" aria-label="Search" value="${param.query}">
        <button class="btn btn-outline-primary" type="submit">Tìm kiếm</button>
    </form>

    <div class="alert alert-info">
        <strong>Tổng số người dùng:</strong> <c:out value="${data.size()}"/>
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
        <c:forEach items="${data}" var="o">
            <tr>
                <td><c:out value="${o.roomID}"/></td>
                <td><c:out value="${o.tenantName}"/></td>
                <td><c:out value="${o.phoneNumber}"/></td>
                <td><c:out value="${o.rentalStartDate}"/></td>
                <td><c:out value="${o.paymentMethodName}"/></td>
                <td><c:out value="${o.notes}"/></td>
                <td>
                    <a class="btn btn-danger btn-sm" onclick="return confirm('Bạn có chắc chắn muốn xóa?')"
                       href="${pageContext.request.contextPath}/dd/delete?roomID=${o.roomID}">Xóa</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
