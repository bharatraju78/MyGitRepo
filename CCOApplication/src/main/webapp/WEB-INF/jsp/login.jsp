<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>PMS Login Page</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/font-awesome/css/font-awesome.min.css"/>"	rel="stylesheet">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<!-- scripts -->
<script src="<c:url value="/resources/script/jquery-1.11.1.min.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/script/bootstrap.min.js" />" type="text/javascript"></script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="/script/html5shiv.min.js"></script>
      <script src="/script/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<div class="login-form">
		<form:form action="login" method="post" class="login-form" id="loginFormId">
			<h2 class="text-center">Log in</h2>
			<div class="form-group">
				<input name="username" class="form-control" placeholder="Username"
					required="required" pattern="^[a-zA-Z0-9._\- ]+$" title="Only letters, numbers, dot, underscore, hyphen, and space are allowed." />
			</div>
			<div class="form-group">
				<input type="password" name="password" class="form-control"
					placeholder="Password" required="required" />
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-primary btn-block">Log	in</button>
			</div>
		</form:form>
	</div>
	<script>
$(function() {
  $('#loginFormId').on('submit', function(e) {
    var username = $(this).find('input[name="username"]').val();
    var regex = /^[a-zA-Z0-9._\- ]+$/;
    if (!regex.test(username)) {
      alert('Username contains invalid characters. Only letters, numbers, dot, underscore, hyphen, and space are allowed.');
      e.preventDefault();
    }
  });
});
</script>
</body>
</html>