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
	<script src="<c:url value="/resources/script/addMapLeaderShip.js"  />" type="text/javascript"></script>
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
		<h3>Project ( ${project.name} ) Add Associate</h3>
	</div>
	<form id="addMapLeaderShip" name="addMapLeaderShip" action="" method="get">
	</form>
	<form id="addMapAssociatesModel" name="addMapAssociatesModel" method="post" 
	action="${pageContext.request.contextPath}/admin/map-associates/saveMapAssociates">
        <input type="hidden" name="projectId" id="projectId" value="${project.id}" />
        <input type="hidden" name="accountId" id="accountId" value="${project.account.accountId}" />
        <input type="hidden" name="projectName" value="${project.name}" />
        <input type="hidden" name="empAllId" id="empAllId" value="${mapAssociatesModel.empAllId }" />
	<div class="container mt-4">
		<div class="row">
                <div class="form-group">
                    <label class="col-sm-2" for="associateType">Associate Type</label>
                    <div class="col-lg-4">
                        <select class="form-control mt-2" id="associateType" name="associateType" required>
                           <option value="">Select Associate Type</option>
                           <c:set value="Leadership"  var="selVal" />
                           <option value="Leadership" ${selVal == type ? 'selected' : '' }>Leadership</option>
                           <c:set value="Director"  var="selVal" />
                           <option value="Director" ${selVal == type ? 'selected' : '' }>Director</option>
                           <c:set value="Manager"  var="selVal" />
                           <option value="Manager" ${selVal == type ? 'selected' : '' }>Manager</option>
                        </select>   
                    </div>
                </div>
            </div>
            <c:if test="${not empty empList }">
            	<div class="row">
	                <div class="form-group">
	                    <label class="col-sm-2" for="empId">Associate Name</label>
	                    <div class="col-lg-4">
	                        <select class="form-control mt-2" id="empId" name="empId" required>
	                           <option value="">Select Associate</option>
	                           <c:forEach var="emp" items="${empList}">
                                   <option value="${emp.employeeId}" ${emp.employeeId == mapAssociatesModel.empId ? 'selected' : '' }>${emp.name}</option>
                               </c:forEach>    
	                        </select>   
	                    </div>
	                </div>
	            </div>
	            <div class="row">
	                <div class="form-group">
	                    <label class="col-sm-2" for="roleId">Associate Role</label>
	                    <div class="col-lg-4">
	                        <select class="form-control mt-2" id="roleId" name="roleId" required>
	                           <option value="">Select Role</option>
	                           <c:forEach var="role" items="${empRoles}">
		                       	  <option value="${role.projectRoleId}" ${role.projectRoleId == mapAssociatesModel.roleId ? 'selected' : '' } >${role.roleName}</option>
		                       </c:forEach>
	                        </select>   
	                    </div>
	                </div>
	            </div>
	            <div class="container mt-2">
					<div class="row form-group" style="padding-top:20px;">
						<button type="submit" id="save" class="btn btn-primary" >Save</button>
					</div>
				</div>
            </c:if>
	</div>
	</form>
	
	<jsp:include page="../footer.jsp" />
	
</body>
</html>