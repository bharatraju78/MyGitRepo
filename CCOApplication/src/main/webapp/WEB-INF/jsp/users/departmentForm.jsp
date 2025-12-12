<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Add/Edit Department</title>
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
        <h2>${department.departmentId == null ? 'Add' : 'Edit'} Department</h2>
        <form action="${pageContext.request.contextPath}/admin/department/department-save" method="post">
            <input type="hidden" name="departmentId" value="${department.departmentId}" />
            <input type="hidden" name="createdBy" value="${department.createdBy}" />
            <input type="hidden" name="modifiedBy" value="${department.modifiedBy}" />
            <input type="hidden" name="createdDate" value="${department.createdDate}" />
            <input type="hidden" name="modifiedDate" value="${department.modifiedDate}" />

            <div class="row">
                <div class="form-group">
                    <label class="col-sm-2" for="departmentCode">Department Code</label>
                    <div class="col-lg-4">
                        <input type="text" class="form-control" id="departmentCode" name="departmentCode" value="${department.departmentCode}" required />
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group">
                    <label class="col-sm-2" for="departmentName">Department Name</label>
                    <div class="col-lg-4">
                        <input type="text" class="form-control" id="departmentName" name="departmentName" value="${department.departmentName}" required />
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group">
                    <label class="col-sm-2" for="departmentStatus">Status</label>
                    <div class="col-lg-4">
                        <select class="form-control" id="departmentStatus" name="departmentStatus">
                            <c:forEach var="s" items="${department.departmentStatusList}">
                                <option value="${s}" ${s == department.departmentStatus ? 'selected' : ''}>${s}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group">
                    <label class="col-sm-2" for="createdBy">Created By</label>
                    <div class="col-lg-4">
                        <input type="text" class="form-control" value="${department.createdBy}" readonly="readonly" />
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group">
                    <label class="col-sm-2" for="modifiedBy">Modified By</label>
                    <div class="col-lg-4">
                        <input type="text" class="form-control" value="${department.modifiedBy}" readonly="readonly" />
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group">
                    <label class="col-sm-2" for="createdDate">Created Date</label>
                    <div class="col-lg-4">
                        <fmt:formatDate value="${department.createdDate}" pattern="yyyy-MM-dd" var="createdDateFmt" />
                        <input type="text" class="form-control" value="${createdDateFmt}" readonly="readonly" />
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group">
                    <label class="col-sm-2" for="modifiedDate">Modified Date</label>
                    <div class="col-lg-4">
                        <fmt:formatDate value="${department.modifiedDate}" pattern="yyyy-MM-dd" var="modifiedDateFmt" />
                        <input type="text" class="form-control" value="${modifiedDateFmt}" readonly="readonly" />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-4">
                    <button type="submit" class="btn btn-success">Save</button>
                    <a href="department-list" class="btn btn-secondary">Cancel</a>
                </div>
            </div>
        </form>
    </div>
    <jsp:include page="../footer.jsp" />
</body>
</html>
