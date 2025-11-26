<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Portfolio List</title>
    <link href="<c:url value='/resources/css/bootstrap.min.css' />" rel="stylesheet">
    <link href="<c:url value='/resources/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/css/style.css' />" rel="stylesheet">
    <script src="<c:url value='/resources/script/jquery-3.1.0.min.js' />" type="text/javascript"></script>
    <script src="<c:url value='/resources/script/jquery.validate.min.js' />" type="text/javascript"></script>
    <script src="<c:url value='/resources/script/bootstrap.min.js' />" type="text/javascript"></script>
    <script src="<c:url value='/resources/script/bootstrap3-typeahead.min.js'  />" type="text/javascript"></script>
</head>
<body>
<jsp:include page="../header.jsp" />
<div class="container mt-4">
    <h2>Portfolios</h2>
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
        <input type="hidden" id="activeTab" name="activeTab" value="${activeTab}" />
    </div>
    <a href="${pageContext.request.contextPath}/admin/portfolio/add" class="btn btn-primary mb-2">Add New Portfolio</a>
    <c:if test="${empty portfolios}">
        <div class="alert alert-info">No portfolios found.</div>
    </c:if>
    <c:if test="${not empty portfolios}">
        <table class="table table-bordered">
            <thead>
            <tr>
                <!-- <th>ID</th> -->
                <th>Portfolio Number</th>
                <th>Name</th>
                <th>Portfolio Leader</th>
                <th>Portfolio HR</th>
                <th>Description</th>
                <th>Active</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="portfolio" items="${portfolios}">
                <tr>
                   <%--  <td>${portfolio.id}</td> --%>
                    <td>${portfolio.portfolioNmber}</td>
                    <td>${portfolio.name}</td>
                    <td>${portfolio.leaderName}</td>
                    <td>${portfolio.hrName}</td>
                    <td>${portfolio.description}</td>
                    <td>${portfolio.active ? 'Yes' : 'No'}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/portfolio/edit/${portfolio.id}" class="btn btn-sm btn-info" title="Edit">
                            <i class="fa fa-pencil"></i>
                        </a>
                        <a href="${pageContext.request.contextPath}/admin/portfolio/delete/${portfolio.id}" class="btn btn-sm btn-danger" title="Delete" onclick="return confirm('Are you sure you want to delete this portfolio?');">
                            <i class="fa fa-trash"></i>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:if test="${page > 1}">
                    <li class="page-item">
                        <a class="page-link" href="?page=${page - 1}&size=${size}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>
                <c:forEach begin="1" end="${totalPages}" var="i">
                    <li class="page-item ${i == page ? 'active' : ''}">
                        <a class="page-link" href="?page=${i}&size=${size}">${i}</a>
                    </li>
                </c:forEach>
                <c:if test="${page < totalPages}">
                    <li class="page-item">
                        <a class="page-link" href="?page=${page + 1}&size=${size}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </c:if>
</div>
</body>
</html>