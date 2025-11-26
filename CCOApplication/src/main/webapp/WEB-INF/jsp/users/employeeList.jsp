<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Employees List</title>
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
    <div class="container mt-4">
    	<c:if test="${empAll == null}">
	        <h2>Employees</h2>
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
	        <a href="${pageContext.request.contextPath}/admin/employees/add" class="btn btn-primary mb-2">Add New Employee</a>
        </c:if>
        <c:if test="${empAll == true}">
        	<h2>Project (<c:out value="${projectName}"/>) Associates</h2>
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
        </c:if>	
        <c:if test="${empAll == null}">
        <c:if test="${empty employees }">
            <div class="alert alert-info">
                No employees found.
            </div>
        </c:if>    
        <c:if test="${not empty employees }">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>VAM ID</th>
                <th>Name</th>
                <th>DOJ</th>
                <th>Email</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="employee" items="${employees}">
                <tr>
                    <td>${employee.employeeId}</td>
                    <td>${employee.vamId}</td>
                    <td>${employee.name}</td>
                    <td>
	                    ${employee.dojSearch}
                    </td>
                    <td>${employee.emailId}</td>
                    <td>${employee.status}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/employees/edit/${employee.employeeId}" class="btn btn-sm btn-warning">Edit</a>
                        <a href="${pageContext.request.contextPath}/admin/employees/delete/${employee.employeeId}" class="btn btn-sm btn-danger" onclick="return confirm('Are you sure?')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </c:if>
        </c:if>
        <c:if test="${empAll == true}">
        <c:if test="${empty employees }">
            <div class="alert alert-info">
                No employees found.
            </div>
        </c:if>    
        <c:if test="${not empty employees }">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>VAM ID</th>
                <th>Name</th>
                <th>Role</th>
                <th>DOJ</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="employee" items="${employees}">
                <tr>
                    <td>${employee.employeeId}</td>
                    <td>${employee.vamId}</td>
                    <td>${employee.name}</td>
                    <td>${employee.roleName}</td>
                    <td>
	                    ${employee.doj}
                    </td>
                    <td>
                    	<fmt:formatDate value="${employee.startDate}" pattern="dd-MM-yyyy" var="startDate" />
                    	${startDate}
                    </td>
                    <td>
                    	<fmt:formatDate value="${employee.endDate}" pattern="dd-MM-yyyy" var="endDate" />
                    	${endDate}
                    </td>
                    <td>${employee.status}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/employee-allocation/editEmployee/${employee.empAllId}/${projectId}/${employee.employeeId}" class="btn btn-sm btn-warning">Edit</a>
                        <a href="${pageContext.request.contextPath}/admin/empUpdateRequest/show/${employee.employeeId}/${projectId}" class="btn btn-sm btn-warning">Create Request</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="row">
            <div class="col-md-12">
                <a href="${pageContext.request.contextPath}/admin/employee-allocation/projects-list/${accountId}" class="btn btn-secondary">Back</a>
            </div>
        </div>    
        </c:if>
        </c:if> 
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
    </div>
    <jsp:include page="../footer.jsp" />
</body>
</html>
