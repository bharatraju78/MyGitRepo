<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Add/Edit Opportunity</title>
    <link href="<c:url value='/resources/css/bootstrap.min.css' />" rel="stylesheet">
    <link href="<c:url value='/resources/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/css/style.css' />" rel="stylesheet">
    <!-- scripts -->
    <script src="<c:url value="/resources/script/jquery-3.1.0.min.js" />"    type="text/javascript"></script>
    <script src="<c:url value="/resources/script/jquery.validate.min.js" />"    type="text/javascript"></script>
    <script src="<c:url value="/resources/script/bootstrap.min.js" />"    type="text/javascript"></script>
    <script src="<c:url value="/resources/script/bootstrap3-typeahead.min.js"  />" type="text/javascript"></script>
</head>
<body>
    <jsp:include page="../header.jsp" />
    <div class="container mt-4">
        <h2>${opportunity.opportunityId == null ? 'Add' : 'Edit'} Opportunity</h2>
        <form action="${pageContext.request.contextPath}/admin/opportunity/opportunity-save" method="post">
            <input type="hidden" name="opportunityId" value="${opportunity.opportunityId}" />
            <input type="hidden" name="createdBy" value="${opportunity.createdBy}" />
            <input type="hidden" name="modifiedBy" value="${opportunity.modifiedBy}" />
            <input type="hidden" name="createdDate" value="${opportunity.createdDate}" />
            <input type="hidden" name="modifiedDate" value="${opportunity.modifiedDate}" />
            <input type="hidden" name="oppNo" value="${opportunity.oppNo}" />
            <div class="row">
            <div class="form-group">
                <label class="col-sm-2"  for="oppNo">Opportunity No</label>
                <div class="col-lg-4">
                    <input type="text" class="form-control" value="${opportunity.oppNo}" required readonly="readonly"/>
                </div>
            </div>
            </div>
            <div class="row">
            <div class="form-group">
                <label class="col-sm-2"  for="oppName">Opportunity Name</label>
                <div class="col-lg-4">
                    <input type="text" class="form-control" id="oppName" name="oppName" value="${opportunity.oppName}" required />
                </div>
            </div>
            </div>
            <div class="row">
            <div class="form-group">
                <label class="col-sm-2"  for="oppStatus">Opportunity Status</label>
                <div class="col-lg-4">
                    <select class="form-control" id="oppStatus" name="oppStatus" required>
                        <option value="">Select Status</option>
                        <c:forEach items="${opportunity.oppStatusList}" var="status">
                            <option value="${status}" ${opportunity.oppStatus == status ? 'selected' : ''}>${status}</option>
                        </c:forEach>
                    </select>    
                </div>
            </div>
            </div>
            <div class="row">
            <div class="form-group">
                <label class="col-sm-2"  for="createdBy">Created By</label>
                <div class="col-lg-4">
                <input type="text" class="form-control" value="${opportunity.createdBy}"  readonly="readonly" />
                </div>
            </div>
            </div>
            <div class="row">
            <div class="form-group">
                <label class="col-sm-2"  for="createdDate">Created Date</label>
                <div class="col-lg-4">
                    <fmt:formatDate value="${opportunity.createdDate}" pattern="dd-MM-yyyy" var="createdDate" />
                    <input type="text" class="form-control" value="${createdDate}" readonly="readonly" />
                </div>
            </div>
            </div>
            <div class="row">
            <div class="form-group">
                <label class="col-sm-2"  for="modifiedBy">Modified By</label>
                <div class="col-lg-4">
                <input type="text" class="form-control" value="${opportunity.modifiedBy}" readonly="readonly"/>
                </div>
            </div>
            </div>
            <div class="row">
            <div class="form-group">
                <label class="col-sm-2"  for="modifiedDate">Modified Date</label>
                <div class="col-lg-4">
                    <fmt:formatDate value="${opportunity.modifiedDate}" pattern="dd-MM-yyyy" var="modifiedDate" />
                    <input type="text" class="form-control" value="${modifiedDate}" readonly="readonly"/>
                </div>
            </div>
            </div>
            <button type="submit" class="btn btn-success">Save</button>
            <a href="${pageContext.request.contextPath}/admin/opportunity/opportunity-list" class="btn btn-secondary">Cancel</a>
        </form>
    </div>
    <jsp:include page="../footer.jsp" />
</body>
</html>