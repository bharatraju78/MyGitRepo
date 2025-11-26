<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Verticals List</title>
    <link href="<c:url value='/resources/css/bootstrap.min.css' />" rel="stylesheet">
    <link href="<c:url value='/resources/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/css/style.css' />" rel="stylesheet">
    <!-- scripts -->
	<script src="<c:url value="/resources/script/jquery-3.1.0.min.js" />"	type="text/javascript"></script>
	<script src="<c:url value="/resources/script/jquery.validate.min.js" />"	type="text/javascript"></script>
	<script src="<c:url value="/resources/script/bootstrap.min.js" />"	type="text/javascript"></script>
	<script src="<c:url value="/resources/script/bootstrap3-typeahead.min.js"  />" type="text/javascript"></script>
	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
      <script src="script/html5shiv.min.js"></script>
      <script src="script/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<jsp:include page="../header.jsp" />
<div class="container">
    <h2>Verticals</h2>
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
    <a href="${pageContext.request.contextPath}/admin/verticals/add" class="btn btn-primary mb-2">Add New Vertical</a>
    <c:if test="${empty verticals }">
        <div class="alert alert-info">
            No verticals found.
        </div>    
     </c:if>
     <c:if test="${not empty verticals }">   
        <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Created By</th>
            <th>Modified By</th>
            <th>Created Date</th>
            <th>Modified Date</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="vertical" items="${verticals}">
            <tr>
                <td>${vertical.verticalId}</td>
                <td>${vertical.verticalName}</td>
                <td>${vertical.createdBy}</td>
                <td>${vertical.modifiedBy}</td>
                <td>
                	<fmt:formatDate value="${vertical.createdDate}" pattern="dd-MM-yyyy" var="createdDate" />
                	${createdDate}
                </td>
                <td>
                	<fmt:formatDate value="${vertical.modifiedDate}" pattern="dd-MM-yyyy" var="modifiedDate" />
                	${modifiedDate}
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/verticals/edit/${vertical.verticalId}" class="btn btn-sm btn-warning">Edit</a>
                    <a href="${pageContext.request.contextPath}/admin/verticals/delete/${vertical.verticalId}" class="btn btn-sm btn-danger" onclick="return confirm('Are you sure?')">Delete</a>
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