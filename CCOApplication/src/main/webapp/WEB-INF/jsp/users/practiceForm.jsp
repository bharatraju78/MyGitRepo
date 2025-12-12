<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Add/Edit Practice</title>
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
        <h2>${practice.practiceId == null ? 'Add' : 'Edit'} Practice</h2>
        <form action="${pageContext.request.contextPath}/admin/practice/practice-save" method="post">
            <input type="hidden" name="practiceId" value="${practice.practiceId}" />
            <input type="hidden" name="createdBy" value="${practice.createdBy}" />
            <input type="hidden" name="modifiedBy" value="${practice.modifiedBy}" />
            <input type="hidden" name="createdDate" value="${practice.createdDate}" />
            <input type="hidden" name="modifiedDate" value="${practice.modifiedDate}" />

            <div class="row">
                <div class="form-group">
                    <label class="col-sm-2" for="practiceCode">Practice Code</label>
                    <div class="col-lg-4">
                        <input type="text" class="form-control" id="practiceCode" name="practiceCode" value="${practice.practiceCode}" required />
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group">
                    <label class="col-sm-2" for="practiceName">Practice Name</label>
                    <div class="col-lg-4">
                        <input type="text" class="form-control" id="practiceName" name="practiceName" value="${practice.practiceName}" required />
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group">
                    <label class="col-sm-2" for="practiceStatus">Status</label>
                    <div class="col-lg-4">
                        <select class="form-control" id="practiceStatus" name="practiceStatus">
                            <c:forEach var="s" items="${practice.practiceStatusList}">
                                <option value="${s}" ${s == practice.practiceStatus ? 'selected' : ''}>${s}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group">
                    <label class="col-sm-2" for="createdBy">Created By</label>
                    <div class="col-lg-4">
                        <input type="text" class="form-control" value="${practice.createdBy}" readonly="readonly" />
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group">
                    <label class="col-sm-2" for="modifiedBy">Modified By</label>
                    <div class="col-lg-4">
                        <input type="text" class="form-control" value="${practice.modifiedBy}" readonly="readonly" />
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group">
                    <label class="col-sm-2" for="createdDate">Created Date</label>
                    <div class="col-lg-4">
                        <fmt:formatDate value="${practice.createdDate}" pattern="yyyy-MM-dd" var="createdDateFmt" />
                        <input type="text" class="form-control" value="${createdDateFmt}" readonly="readonly" />
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group">
                    <label class="col-sm-2" for="modifiedDate">Modified Date</label>
                    <div class="col-lg-4">
                        <fmt:formatDate value="${practice.modifiedDate}" pattern="yyyy-MM-dd" var="modifiedDateFmt" />
                        <input type="text" class="form-control" value="${modifiedDateFmt}" readonly="readonly" />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-4">
                    <button type="submit" class="btn btn-success">Save</button>
                    <a href="practice-list" class="btn btn-secondary">Cancel</a>
                </div>
            </div>
        </form>
    </div>
    <jsp:include page="../footer.jsp" />
</body>
</html>
