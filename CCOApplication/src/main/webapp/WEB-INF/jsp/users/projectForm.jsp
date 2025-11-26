<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Add/Edit Project</title>
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
        <h2>${project.id == null ? 'Add' : 'Edit'} Project</h2>
        <form action="${pageContext.request.contextPath}/admin/projects/project-save" method="post">
            <input type="hidden" name="id" value="${project.id}" />
            <input type="hidden" name="createdBy" value="${project.createdBy}" />
            <input type="hidden" name="modifiedBy" value="${project.modifiedBy}" />
            <input type="hidden" name="createdDate" value="${project.createdDate}" />
            <input type="hidden" name="modifiedDate" value="${project.modifiedDate}" />
            <div class="row">
            <div class="form-group">
                <label class="col-sm-2" for="code">Project Code</label>
                <div class="col-lg-4">
                    <input type="text" class="form-control" id="code" name="code" value="${project.code}" readonly="readonly" />
                </div>
            </div>
            </div>
            <div class="row">
            <div class="form-group">
                <label class="col-sm-2" for="name">Project Name</label>
                <div class="col-lg-4">
                    <input type="text" class="form-control" id="name" name="name" value="${project.name}" required />
                </div>
            </div>
            </div>
            <div class="row">
            <div class="form-group">
                <label class="col-sm-2" for="startDate">Start Date</label>
                <div class="col-lg-4">
                    <input type="date" class="form-control" id="startDate" name="startDate" value="<fmt:formatDate value='${project.startDate}' pattern='yyyy-MM-dd'/>" required />
                </div>
            </div>
            </div>
            <div class="row">
            <div class="form-group">
                <label class="col-sm-2" for="endDate">End Date</label>
                <div class="col-lg-4">
                    <input type="date" class="form-control" id="endDate" name="endDate" value="<fmt:formatDate value='${project.endDate}' pattern='yyyy-MM-dd'/>" required greaterThan="#startDate"/>
                    <span id="endDateError" class="help-block text-danger" style="display:none;">End Date must be greater than Start Date.</span>
                </div>
            </div>
            </div>
            <div class="row">
            <div class="form-group">
                <label class="col-sm-2" for="accountId">Account</label>
                <div class="col-lg-4">
                	<select id="accountId" name="accountId"  class="form-control" required>
		              	<option value="">Select</option>
		              	<c:forEach items="${accounts}" var="account">
		              		<option value="${account.accountId}" ${account.accountId != project.accountId ? '' : 'selected' }>${account.accName}</option>
		              	</c:forEach>
		            </select>
                </div>
            </div>
            </div>
            <div class="row">
            <div class="form-group">
                <label class="col-sm-2" for="accountId">Status</label>
                <div class="col-lg-4">
                	<select class="form-control" id="status" name="status" required>
                        <option value="">Select Status</option>
                        <c:forEach items="${project.statusList}" var="statusVar">
                            <option value="${statusVar}" ${project.status == statusVar ? 'selected' : ''}>${statusVar}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            </div>
            <div class="row">
            <div class="form-group">
                <label class="col-sm-2"  for="createdBy">Created By</label>
                <div class="col-lg-4">
                <input type="text" class="form-control" value="${project.createdBy}"  readonly="readonly" />
            	</div>
            </div>
            </div>
            <div class="row">
            <div class="form-group">
                <label class="col-sm-2"  for="createdDate">Created Date</label>
                <div class="col-lg-4">
                	<fmt:formatDate value="${project.createdDate}" pattern="dd-MM-yyyy" var="createdDate" />
                	<input type="text" class="form-control" value="<fmt:formatDate value='${project.createdDate}' pattern='yyyy-MM-dd'/>" readonly="readonly" />
            	</div>
            </div>
            </div>
            <div class="row">
            <div class="form-group">
                <label class="col-sm-2"  for="modifiedBy">Modified By</label>
                <div class="col-lg-4">
                <input type="text" class="form-control" value="${project.modifiedBy}" readonly="readonly"/>
           		</div>
            </div>
            </div>
            <div class="row">
            <div class="form-group">
                <label class="col-sm-2"  for="modifiedDate">Modified Date</label>
                <div class="col-lg-4">
                	<fmt:formatDate value="${project.modifiedDate}" pattern="dd-MM-yyyy" var="modifiedDate" />
                	<input type="text" class="form-control" value="<fmt:formatDate value='${project.modifiedDate}' pattern='yyyy-MM-dd'/>" readonly="readonly"/>
           		</div>
            </div>
            </div>
            <div class="form-group">
                <div class="col-sm-4">
                    <button type="submit" class="btn btn-success">Save</button>
                    <a href="${pageContext.request.contextPath}/admin/projects/project-list" class="btn btn-secondary">Cancel</a>
                </div>
            </div>
        </form>
    </div>
    <jsp:include page="../footer.jsp" />
    <script type="text/javascript">
        $(document).ready(function() {
            $('form').on('submit', function(e) {
                var startDate = $('#startDate').val();
                var endDate = $('#endDate').val();
                if (startDate && endDate && endDate <= startDate) {
                    $('#endDateError').show();
                    $('#endDate').focus();
                    $('#endDate').addClass('has-error');
                    e.preventDefault();
                } else {
                	$('#endDate').removeClass('has-error');
                    $('#endDateError').hide();
                }
            });
            $('#endDate, #startDate').on('change', function() {
                var startDate = $('#startDate').val();
                var endDate = $('#endDate').val();
                if (startDate && endDate && endDate <= startDate) {
                    $('#endDateError').show();
                    $('#endDate').addClass('has-error');
                } else {
                	$('#endDate').removeClass('has-error');
                    $('#endDateError').hide();
                }
            });
        });
    </script>
</body>
</html>