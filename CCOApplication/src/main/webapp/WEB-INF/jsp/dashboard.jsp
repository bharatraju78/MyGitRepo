<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>CCO Dashboard</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/userDashboard.css" />" rel="stylesheet">
<style>
   .invalid-feedback {
	    display: none;
	    width: 100%;
	    margin-top: .25rem;
	    font-size: 80%;
	    color: #dc3545;
	}
    </style>
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

<script src="<c:url value="/resources/script/dashboard.js"  />" type="text/javascript"></script>

</head>

<body id="containerMain">
	<!-- header -->
	<jsp:include page="header.jsp" />
	<!-- /header -->

	<!-- sidebar -->
	<%-- <jsp:include page="sidebar.jsp" /> --%>
	<!-- /sidebar -->

	<div id="page-content-wrapper">

		<!-- footer -->
		<jsp:include page="footer.jsp" />
		<!-- /footer -->
	</div>
</body>
</html>