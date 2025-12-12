<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Add/Edit Rate</title>
    <link href="<c:url value='/resources/css/bootstrap.min.css' />" rel="stylesheet">
    <link href="<c:url value='/resources/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/css/style.css' />" rel="stylesheet">
</head>
<body>
<jsp:include page="../header.jsp" />
<div class="container mt-4">
    <h2>${rate.rateId == null ? 'Add' : 'Edit'} Rate</h2>
    <form action="${pageContext.request.contextPath}/admin/rates/save" method="post">
        <input type="hidden" name="rateId" value="${rate.rateId}" />
        <input type="hidden" name="createdBy" value="${rate.createdBy}" />
        <input type="hidden" name="modifiedBy" value="${rate.modifiedBy}" />
        <input type="hidden" name="createdDate" value="${rate.createdDate}" />
        <input type="hidden" name="modifiedDate" value="${rate.modifiedDate}" />

        <!-- rateCode removed as per requirement -->

        <!-- remove rateName and add Technology / Service Center before skill -->
        <div class="row">
            <div class="form-group">
                <label class="col-sm-2" for="effectiveFrom">Effective From</label>
                <div class="col-lg-4">
                    <input type="date" class="form-control" id="effectiveFrom" name="effectiveFrom" value="${rate.effectiveFrom}" />
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group">
                <label class="col-sm-2" for="effectiveTo">Effective To</label>
                <div class="col-lg-4">
                    <input type="date" class="form-control" id="effectiveTo" name="effectiveTo" value="${rate.effectiveTo}" />
                </div>
            </div>
        </div>
        
        <div class="row">
            <div class="form-group">
                <label class="col-sm-2" for="technologyServiceCenter">TSC</label>
                <div class="col-lg-4">
                    <%-- <input type="text" class="form-control" id="technologyServiceCenter" name="technologyServiceCenter" value="${rate.technologyServiceCenter}" /> --%>
                    <select class="form-control" id="technologyServiceCenter" name="technologyServiceCenter" value="${rate.technologyServiceCenter}" >
                         <c:forEach var="s" items="${rate.techCenterList}">
                             <option value="${s}" ${s == rate.technologyServiceCenter ? 'selected' : ''}>${s}</option>
                         </c:forEach>
                     </select>
                </div>
            </div>
        </div>
        
        <div class="row">
            <div class="form-group">
                <label class="col-sm-2" for="accountName">Account Name</label>
                <div class="col-lg-4">
                    <%-- <input type="text" class="form-control" id="accountName" name="accountName" value="${rate.accountName}" /> --%>
                    <select class="form-control" id="accountName" name="accountName" value="${rate.accountName}" >
                         <c:forEach var="s" items="${rate.accountList}">
                             <option value="${s}" ${s == rate.accountName ? 'selected' : ''}>${s}</option>
                         </c:forEach>
                     </select>
                </div>
            </div>
        </div>
        
        <div class="row">
            <div class="form-group">
                <label class="col-sm-2" for="role">Role</label>
                <div class="col-lg-4">
                    <%-- <input type="text" class="form-control" id="role" name="role" value="${rate.role}" /> --%>
                    <select class="form-control" id="role" name="role" value="${rate.role}" >
                         <c:forEach var="s" items="${rate.projectRoleList}">
                             <option value="${s}" ${s == rate.role ? 'selected' : ''}>${s}</option>
                         </c:forEach>
                     </select>
                </div>
            </div>
        </div>
        
        <div class="row">
            <div class="form-group">
                <label class="col-sm-2" for="skill">Skill</label>
                <div class="col-lg-4">
                    <input type="text" class="form-control" id="skill" name="skill" value="${rate.skill}" />
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group">
                <label class="col-sm-2" for="rateAmount">Rate</label>
                <div class="col-lg-4">
                    <input type="text" class="form-control" id="rateAmount" name="rateAmount" value="${rate.rateAmount}" />
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-4">
                <button type="submit" class="btn btn-success">Save</button>
                <a href="${pageContext.request.contextPath}/admin/rates/list" class="btn btn-secondary">Cancel</a>
            </div>
        </div>
    </form>
</div>
<jsp:include page="../footer.jsp" />
</body>
</html>