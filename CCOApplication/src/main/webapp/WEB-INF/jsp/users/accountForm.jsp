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
        <h2>${account.accountId == null ? 'Add' : 'Edit'} Account</h2>
        <form action="${pageContext.request.contextPath}/admin/account/account-save" method="post">
            <input type="hidden" name="accountId" value="${account.accountId}" />
            <input type="hidden" name="createdBy" value="${account.createdBy}" />
            <input type="hidden" name="modifiedBy" value="${account.modifiedBy}" />
            <input type="hidden" name="createdDate" value="${account.createdDate}" />
            <input type="hidden" name="modifiedDate" value="${account.modifiedDate}" />
            <input type="hidden" name="accNo" value="${account.accNo}" />
            <div class="row">
            <div class="form-group">
                <label class="col-sm-2"  for="accNo">Account No</label>
                <div class="col-lg-4">
                	<input type="text" class="form-control" value="${account.accNo}" required readonly="readonly"/>
            	</div>
            </div>
            </div>
            <div class="row">
            <div class="form-group">
                <label class="col-sm-2"  for="accName">Account Name</label>
                <div class="col-lg-4">
                	<input type="text" class="form-control" id="accName" name="accName" value="${account.accName}" required />
            	</div>
            </div>
            </div>
            <div class="row">
            <div class="form-group">
                <label class="col-sm-2"  for="accName">Account Status</label>
                <div class="col-lg-4">
                	<select class="form-control" id="accStatus" name="accStatus" required>
                		<option value="">Select Status</option>
                		<c:forEach items="${account.accStatusList}" var="status">
                			<option value="${status}" ${account.accStatus == status ? 'selected' : ''}>${status}</option>
                		</c:forEach>
                	</select>	
            	</div>
            </div>
            </div>
            <div class="row">
            <div class="form-group">
                <label class="col-sm-2"  for="createdBy">Created By</label>
                <div class="col-lg-4">
                <input type="text" class="form-control" value="${account.createdBy}"  readonly="readonly" />
            	</div>
            </div>
            </div>
            <div class="row">
            <div class="form-group">
                <label class="col-sm-2"  for="createdDate">Created Date</label>
                <div class="col-lg-4">
                	<fmt:formatDate value="${account.createdDate}" pattern="dd-MM-yyyy" var="createdDate" />
                	<input type="text" class="form-control" value="${createdDate}" readonly="readonly" />
            	</div>
            </div>
            </div>
            <div class="row">
            <div class="form-group">
                <label class="col-sm-2"  for="modifiedBy">Modified By</label>
                <div class="col-lg-4">
                <input type="text" class="form-control" value="${account.modifiedBy}" readonly="readonly"/>
           		</div>
            </div>
            </div>
            <div class="row">
            <div class="form-group">
                <label class="col-sm-2"  for="modifiedDate">Modified Date</label>
                <div class="col-lg-4">
                	<fmt:formatDate value="${account.modifiedDate}" pattern="dd-MM-yyyy" var="modifiedDate" />
                	<input type="text" class="form-control" value="${modifiedDate}" readonly="readonly"/>
           		</div>
            </div>
            </div>
            <button type="submit" class="btn btn-success">Save</button>
            <a href="${pageContext.request.contextPath}/admin/account/account-list" class="btn btn-secondary">Cancel</a>
        </form>
    </div>
    <jsp:include page="../footer.jsp" />
</body>
</html>
