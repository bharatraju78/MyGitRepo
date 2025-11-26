<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Add/Edit User</title>
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
        <h2>${user.id == null ? 'Add' : 'Edit'} User</h2>
        <form action="${pageContext.request.contextPath}/admin/users/user-save" method="post">
            <input type="hidden" name="id" value="${user.id}" />
            <div class="row">
            <div class="form-group ">
                <label class="col-sm-1" for="username">Username</label>
                <div class="col-lg-4">
                    <input type="text" class="form-control" id="username" name="username" value="${user.username}" required  />
                </div>
            </div>
            </div>
            <div class="row">
            <div class="form-group">
                <label class="col-sm-1" for="password">Passowrd</label>
                <div class="col-lg-4">
                    <input type="text" class="form-control" id="password" name="password" value="${user.password}" required  />
                </div>
            </div>
            </div>
             <div class="row">
            <div class="form-group">
                <label class="col-sm-1" for="role">Role</label>
                <div class="col-lg-4">
                    <select class="form-control" id="roleId" name="roleId" required >
                        <option value="">Select Role</option>
                        <c:forEach var="role" items="${roles}">
                            <option value="${role.id}" ${role.id != user.roleId ? '' : 'selected' }>${role.name}</option>
                        </c:forEach>
                    </select>    
                </div>
            </div>
            </div>
            <div class="form-group">
            	<div class="col-sm-4">
		            <button type="submit" class="btn btn-success">Save</button>
		            <a href="${pageContext.request.contextPath}/admin/users/user-list" class="btn btn-secondary">Cancel</a>
        		</div>
        	</div>
        </form>
    </div>
    <jsp:include page="../footer.jsp" />
</body>
</html>