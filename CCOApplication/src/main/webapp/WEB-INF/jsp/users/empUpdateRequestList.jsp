<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Request List</title>
    <link href="<c:url value='/resources/css/bootstrap.min.css' />" rel="stylesheet">
    <link href="<c:url value='/resources/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/css/style.css' />" rel="stylesheet">
    <script src="<c:url value='/resources/script/jquery-3.1.0.min.js' />" type="text/javascript"></script>
    <script src="<c:url value='/resources/script/bootstrap.min.js' />" type="text/javascript"></script>
    <script src="<c:url value='/resources/script/empUpdateRequestList.js' />" type="text/javascript"></script>
</head>
<body>
<jsp:include page="../header.jsp" />
<div class="container mt-4">
    <h2>Request List</h2>
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
    <ul class="nav nav-tabs" id="requestTabs" role="tablist">
        <li class="nav-item">
            <a class="nav-link active" id="created-tab" data-toggle="tab" href="#created" role="tab" aria-controls="created" aria-selected="true">CREATED (${eur_count_created })</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="inprogress-tab" data-toggle="tab" href="#inprogress" role="tab" aria-controls="inprogress" aria-selected="false">IN_PROGRESS (${eur_count_in_progress })</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="completed-tab" data-toggle="tab" href="#completed" role="tab" aria-controls="completed" aria-selected="false">COMPLETED</a>
        </li>
    </ul>
    <div class="tab-content mt-3" id="requestTabsContent">
        <div class="tab-pane fade show active" id="created" role="tabpanel" aria-labelledby="created-tab">
            <table class="table table-bordered">
                <thead>
                    <tr>
                    	<th>Request Number</th>
			            <th>Request By</th>
			            <th>Request To</th>
			            <th>Request Type</th>
			            <th>Employee</th>
			            <th>Start Date</th>
			            <th>Comments</th>
			            <th>Status</th>
			            <security:authorize access="hasRole('ROLE_HR')">
			           	 <th>Actions</th>
			            </security:authorize>
                    </tr>
                </thead>
                <tbody>
                	<c:if test="${empty eur_created}">
                        <tr>
                            <td colspan="10" class="text-center">No requests found.</td>
                        </tr>
                    </c:if>
                    <c:if test="${not empty eur_created}">
	                	<c:forEach items="${eur_created}" var="request">
	                        <tr>
	                        	<td>${request.requestNumber}</td>
				                <td>${request.requestByName}</td>
				                <td>${request.requestToName}</td>
				                <td>${request.requestType}</td>
				                <td>${request.employeeName}</td>
				                <td><fmt:formatDate value="${request.startDate}" pattern="dd-MM-yyyy"/></td>
				                <td>${request.comments}</td>
				                <td>${request.status}</td>
				                <security:authorize access="hasRole('ROLE_HR')">
				                <td>
					                <a href="${pageContext.request.contextPath}/admin/empUpdateRequest/requestToAction/${request.id}/${request.employeeId}"><i class="fa fa-cogs" title="Actions"></i></a> 
				                </td>
				                </security:authorize>
	                        </tr>
	                     </c:forEach> 
                     </c:if>  
                </tbody>
            </table>
        </div>
        <div class="tab-pane fade" id="inprogress" role="tabpanel" aria-labelledby="inprogress-tab">
            <table class="table table-bordered">
                <thead>
                    <tr>
                    	<th>Request Number</th>
                        <th>Request By</th>
			            <th>Request To</th>
			            <th>Request Type</th>
			            <th>Employee</th>
			            <th>Start Date</th>
			            <th>Comments</th>
			            <th>Status</th>
			            <security:authorize access="hasRole('ROLE_HR')">
			            	<th>Actions</th>
			            </security:authorize>
                    </tr>
                </thead>
                <tbody>
                	<c:if test="${empty eur_in_progress}">
                        <tr>
                            <td colspan="10" class="text-center">No requests found.</td>
                        </tr>
                    </c:if>
                    <c:if test="${not empty eur_in_progress}">    
	                    <c:forEach items="${eur_in_progress}" var="request">
	                        <tr>
	                        	<td>${request.requestNumber}</td>
				                <td>${request.requestByName}</td>
				                <td>${request.requestToName}</td>
				                <td>${request.requestType}</td>
				                <td>${request.employeeName}</td>
				                <td><fmt:formatDate value="${request.startDate}" pattern="dd-MM-yyyy"/></td>
				                <td>${request.comments}</td>
				                <td>${request.status}</td>
				                <security:authorize access="hasRole('ROLE_HR')">
					                <td>
					                   <a href="${pageContext.request.contextPath}/admin/empUpdateRequest/requestToAction/${request.id}/${request.employeeId}"><i class="fa fa-cogs" title="Actions"></i></a>
					                </td>
				                </security:authorize>
	                        </tr>
	                     </c:forEach> 
                     </c:if> 
                </tbody>
            </table>
        </div>
        <div class="tab-pane fade" id="completed" role="tabpanel" aria-labelledby="completed-tab">
        	<input type="hidden" id="fromPage" name="fromPage" value="${fromPage}" />
            <table class="table table-bordered">
                <thead>
                    <tr>
                    	<th>Request Number</th>
                        <th>Request By</th>
			            <th>Request To</th>
			            <th>Request Type</th>
			            <th>Employee</th>
			            <th style="min-width:90px;">Start Date</th>
			            <th style="min-width:90px;">End Date</th>
			            <th>Comments</th>
			            <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${empty eur_completed}">
                        <tr>
                            <td colspan="10" class="text-center">No requests found.</td>
                        </tr>
                    </c:if>
                    <c:if test="${not empty eur_completed}">    
	                    <c:forEach items="${eur_completed}" var="request">
	                        <tr>
	                        	<td>${request.requestNumber}</td>
				                <td>${request.requestByName}</td>
				                <td>${request.requestToName}</td>
				                <td>${request.requestType}</td>
				                <td>${request.employeeName}</td>
				                <td><fmt:formatDate value="${request.startDate}" pattern="dd-MM-yyyy"/></td>
				                <td><fmt:formatDate value="${request.endDate}" pattern="dd-MM-yyyy"/></td>
				                <td>${request.comments}</td>
				                <td>${request.status}</td>
	                        </tr>
	                     </c:forEach> 
                     </c:if>
                </tbody>
            </table>
            <!-- Pagination Controls -->
	        <nav aria-label="Page navigation">
	            <ul class="pagination">
	                <c:if test="${page > 1}">
	                    <li class="page-item">
	                        <a class="page-link" href="?page=${page - 1}&size=${size}&fromPage=completed" aria-label="Previous">
	                            <span aria-hidden="true">&laquo;</span>
	                        </a>
	                    </li>
	                </c:if>
	                <c:forEach var="i" begin="1" end="${totalPages}">
	                    <li class="page-item ${i == page ? 'active' : ''}">
	                        <a class="page-link" href="?page=${i}&size=${size}&fromPage=completed">${i}</a>
	                    </li>
	                </c:forEach>
	                <c:if test="${page < totalPages}">
	                    <li class="page-item">
	                        <a class="page-link" href="?page=${page + 1}&size=${size}&fromPage=completed" aria-label="Next">
	                            <span aria-hidden="true">&raquo;</span>
	                        </a>
	                    </li>
	                </c:if>
	            </ul>
	        </nav>
        </div>
    </div>
</div>
<jsp:include page="../footer.jsp" />
<script type="text/javascript">
	$(document).ready(function() {
        if($('#fromPage') && $('#fromPage').val() === 'completed') {
        	$('#completed-tab').click();
        }
    });
</script>
</body>
</html>