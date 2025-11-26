<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Add/Edit Account</title>
    <link href="<c:url value='/resources/css/bootstrap.min.css' />" rel="stylesheet">
    <link href="<c:url value='/resources/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/css/style.css' />" rel="stylesheet">
    <!-- scripts -->
	<script src="<c:url value="/resources/script/jquery-3.1.0.min.js" />"	type="text/javascript"></script>
	<script src="<c:url value="/resources/script/jquery.validate.min.js" />"	type="text/javascript"></script>
	<script src="<c:url value="/resources/script/bootstrap.min.js" />"	type="text/javascript"></script>
	<script src="<c:url value="/resources/script/bootstrap3-typeahead.min.js"  />" type="text/javascript"></script>
	<script src="<c:url value="/resources/script/mapLeaderShip.js"  />" type="text/javascript"></script>
	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
      <script src="script/html5shiv.min.js"></script>
      <script src="script/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="container mt-2">
		<h3>Project ( ${project.name} )</h3>
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
	</div>
        <input type="hidden" name="projectId" value="${project.id}" />
        <input type="hidden" name="accountId" value="${project.account.accountId}" />
        <input type="hidden" name="projectName" value="${project.name}" />
        <input type="hidden" name="leadershipListSize" id="leadershipListSize" value="${leadershipList.size()}" />
        <input type="hidden" name="directorListSize" id="directorListSize" value="${directorList.size()}" />
        <input type="hidden" name="managerListSize" id="managerListSize" value="${managerList.size()}" />
	<div class="container mt-4">
        <div class="row font-weight-bold text-center align-items-center" style="border-bottom:1px solid #ccc;">
            <div class="col-sm-4">Name</div>
            <div class="col-sm-4">Role</div>
            <div class="col-sm-4">Actions</div>
        </div>
        <c:forEach var="emp" items="${mapAssociatesModel.employeeAllocationList}">
            <div class="row text-center align-items-center" style="border-bottom:1px solid #eee;">
                <div class="col-sm-4">${emp.empName}</div>
                <div class="col-sm-4">${emp.roleName}</div>
                <div class="col-sm-4">
                    <button class="btn btn-sm btn-primary" onclick="showEditRow('${pageContext.request.contextPath}/admin/map-associates/showEdit/${projectId}/${accountId}/${emp.empAllId}/${emp.associateType }');" title="Edit"><i class="fa fa-edit"></i></button>
                    <button class="btn btn-sm btn-danger" onclick="deleteRow('${pageContext.request.contextPath}/admin/map-associates/delete/${projectId}/${accountId}/${emp.empAllId}');" title="Delete"><i class="fa fa-trash"></i></button>
                </div>
            </div>
        </c:forEach>
    </div>
	<div class="container mt-2">
		<div class="row form-group" style="padding-top:20px;">
			<a href="${pageContext.request.contextPath}/admin/map-associates/addNewMapping/${projectId}/${accountId}" class="btn btn-primary">Add New Associate</a>
		</div>
	</div>
	
	
	<jsp:include page="../footer.jsp" />
	
</body>
</html>