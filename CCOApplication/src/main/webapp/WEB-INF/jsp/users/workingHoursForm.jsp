<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Add/Edit Working Hours</title>
    <link href="<c:url value='/resources/css/bootstrap.min.css' />" rel="stylesheet">
    <link href="<c:url value='/resources/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/css/style.css' />" rel="stylesheet">
    <!-- scripts -->
	<script src="<c:url value="/resources/script/jquery-3.1.0.min.js" />"	type="text/javascript"></script>
	<script src="<c:url value="/resources/script/jquery.validate.min.js" />"	type="text/javascript"></script>
	<script src="<c:url value="/resources/script/bootstrap.min.js" />"	type="text/javascript"></script>
	<script src="<c:url value="/resources/script/bootstrap3-typeahead.min.js"  />" type="text/javascript"></script>
</head>
<body>
    <jsp:include page="../header.jsp" />
    <div class="container mt-4">
        <h2>${workingHours.workingHoursId == null ? 'Add' : 'Edit'} Working Hours</h2>
        <form action="${pageContext.request.contextPath}/admin/working-hours/save" method="post">
            <input type="hidden" name="workingHoursId" value="${workingHours.workingHoursId}" />
            <input type="hidden" name="createdBy" value="${workingHours.createdBy}" />
            <input type="hidden" name="modifiedBy" value="${workingHours.modifiedBy}" />
            <input type="hidden" name="createdDate" value="${workingHours.createdDate}" />
            <input type="hidden" name="modifiedDate" value="${workingHours.modifiedDate}" />

            <div class="row">
                <div class="form-group">
                    <label class="col-sm-2" for="location">Location</label>
                    <div class="col-lg-4">
                        <input type="text" class="form-control" id="location" name="location" value="${workingHours.location}" required />
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group">
                    <label class="col-sm-2" for="hoursPerMonth">Hours Per Month</label>
                    <div class="col-lg-4">
                        <input type="text" class="form-control" id="hoursPerMonth" name="hoursPerMonth" value="${workingHours.hoursPerMonth}" required />
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group">
                    <label class="col-sm-2" for="createdByDisplay">Created By</label>
                    <div class="col-lg-4">
                        <input type="text" class="form-control" id="createdByDisplay" value="${workingHours.createdBy}" readonly="readonly" />
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group">
                    <label class="col-sm-2" for="modifiedByDisplay">Modified By</label>
                    <div class="col-lg-4">
                        <input type="text" class="form-control" id="modifiedByDisplay" value="${workingHours.modifiedBy}" readonly="readonly" />
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group">
                    <label class="col-sm-2" for="createdDateDisplay">Created Date</label>
                    <div class="col-lg-4">
                        <fmt:formatDate value="${workingHours.createdDate}" pattern="yyyy-MM-dd" var="createdDateFmt" />
                        <input type="text" class="form-control" id="createdDateDisplay" value="${createdDateFmt}" readonly="readonly" />
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group">
                    <label class="col-sm-2" for="modifiedDateDisplay">Modified Date</label>
                    <div class="col-lg-4">
                        <fmt:formatDate value="${workingHours.modifiedDate}" pattern="yyyy-MM-dd" var="modifiedDateFmt" />
                        <input type="text" class="form-control" id="modifiedDateDisplay" value="${modifiedDateFmt}" readonly="readonly" />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-4">
                    <button type="submit" class="btn btn-success">Save</button>
                    <a href="${pageContext.request.contextPath}/admin/working-hours/list" class="btn btn-secondary">Cancel</a>
                </div>
            </div>
        </form>
    </div>
    <jsp:include page="../footer.jsp" />
</body>
</html>