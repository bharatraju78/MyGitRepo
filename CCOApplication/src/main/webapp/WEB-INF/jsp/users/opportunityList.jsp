<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Opportunities List</title>
    <link href="<c:url value='/resources/css/bootstrap.min.css' />" rel="stylesheet">
    <link href="<c:url value='/resources/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/css/style.css' />" rel="stylesheet">
    <!-- scripts -->
    <script src="<c:url value="/resources/script/jquery-3.1.0.min.js" />"    type="text/javascript"></script>
    <script src="<c:url value="/resources/script/jquery.validate.min.js" />"    type="text/javascript"></script>
    <script src="<c:url value="/resources/script/bootstrap.min.js" />"    type="text/javascript"></script>
    <script src="<c:url value="/resources/script/bootstrap3-typeahead.min.js"  />" type="text/javascript"></script>
</head>
<body>
    <jsp:include page="../header.jsp" />
    <div class="container mt-4">
        <h2>Opportunities</h2>
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
        <a href="${pageContext.request.contextPath}/admin/opportunity/add" class="btn btn-primary mb-2">Add New Opportunity</a>
        <c:if test="${empty opportunities}">
            <div class="alert alert-info">No opportunities found.</div>
        </c:if>    
        <c:if test="${not empty opportunities}">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Opportunity No</th>
                <th>Opportunity Name</th>
                <th>Created By</th>
                <th>Modified By</th>
                <th>Created Date</th>
                <th>Modified Date</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="opportunity" items="${opportunities}">
                <tr>
                    <td>${opportunity.opportunityId}</td>
                    <td>${opportunity.oppNo}</td>
                    <td>${opportunity.oppName}</td>
                    <td>${opportunity.createdBy}</td>
                    <td>${opportunity.modifiedBy}</td>
                    <td>
                        <fmt:formatDate value="${opportunity.createdDate}" pattern="dd-MM-yyyy" var="createdDate" />
                        ${createdDate}
                    </td>
                    <td>
                        <fmt:formatDate value="${opportunity.modifiedDate}" pattern="dd-MM-yyyy" var="modifiedDate" />
                        ${modifiedDate}
                    </td>
                    <td>
                        <c:if test="${empty fromAction}">
                            <a href="${pageContext.request.contextPath}/admin/opportunity/edit/${opportunity.opportunityId}" class="btn btn-sm btn-warning">Edit</a>
                            <a href="${pageContext.request.contextPath}/admin/opportunity/delete/${opportunity.opportunityId}" class="btn btn-sm btn-danger" onclick="return confirm('Are you sure?')">Delete</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <!-- Pagination Controls -->
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:if test="${page > 1}">
                    <li class="page-item">
                        <a class="page-link" href="?page=${page - 1}&size=${size}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>
                <c:forEach var="i" begin="1" end="${totalPages}">
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
    <jsp:include page="../footer.jsp" />
</body>
</html>