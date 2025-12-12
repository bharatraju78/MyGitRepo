<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Rates List</title>
    <link href="<c:url value='/resources/css/bootstrap.min.css' />" rel="stylesheet">
    <link href="<c:url value='/resources/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/css/style.css' />" rel="stylesheet">
</head>
<body>
<jsp:include page="../header.jsp" />
<div class="container mt-4">
    <h2>Rates</h2>
    <div id="alert alert-danger" role="alert">
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger">
                ${errorMessage}
            </div>
        </c:if>
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success">
                ${successMessage}
            </div>
        </c:if>
    </div>
    <a href="${pageContext.request.contextPath}/admin/rates/add" class="btn btn-primary mb-2">Add New Rate</a>
    <c:if test="${empty rateModels }">
        <div class="alert alert-info">No rates found.</div>
    </c:if>
    <c:if test="${not empty rateModels }">
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Technology Service Center</th>
                    <th>Account Name</th>
                    <th>Skill</th>
                    <th>Role</th>
                    <th>Rate</th>
                    <th>Effective From</th>
                    <th>Effective To</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="r" items="${rateModels}">
                    <tr>
                        <td>${r.rateId}</td>
                        <td>${r.technologyServiceCenter}</td>
                        <td>${r.accountName}</td>
                        <td>${r.skill}</td>
                        <td>${r.role}</td>
                        <td>${r.rateAmount}</td>
                        <td><fmt:formatDate value="${r.effectiveFrom}" pattern="dd-MM-yyyy"/></td>
                        <td><fmt:formatDate value="${r.effectiveTo}" pattern="dd-MM-yyyy"/></td>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/rates/edit/${r.rateId}" class="btn btn-sm btn-warning">Edit</a>
                            <a href="${pageContext.request.contextPath}/admin/rates/delete/${r.rateId}" class="btn btn-sm btn-danger" onclick="return confirm('Are you sure?')">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
<jsp:include page="../footer.jsp" />
</body>
</html>