<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Add/Edit EmpUpdateRequest</title>
    <link href="<c:url value='/resources/css/bootstrap.min.css' />" rel="stylesheet">
    <link href="<c:url value='/resources/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/css/style.css' />" rel="stylesheet">
    <script src="<c:url value='/resources/script/jquery-3.1.0.min.js' />" type="text/javascript"></script>
    <script src="<c:url value='/resources/script/bootstrap.min.js' />" type="text/javascript"></script>
</head>
<body>
<jsp:include page="../header.jsp" />
<div class="container mt-4">
    <h2>${empUpdateRequest.id == null ? 'Add' : 'Edit'} EmpUpdateRequest</h2>
    <form action="${pageContext.request.contextPath}/admin/empUpdateRequest/add" method="post">
        <c:if test="${empUpdateRequest.id != null}">
            <input type="hidden" name="id" value="${empUpdateRequest.id}"/>
        </c:if>
        <input type="hidden" name="projectId" value="${projectId}"/>
        <input type="hidden" name="actionFrom" value="${actionFrom}"/>
        <div class="row">
            <div class="form-group">
            	<input type="hidden" name="requestById" value="${empUpdateRequest.requestById}"/>
                <label class="col-sm-2" for="requestByName">Request By</label>
                <div class="col-lg-4">
                    <input type="text" class="form-control" id="requestByName" name="requestByName" value="${empUpdateRequest.requestByName}" readonly="readonly"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
            	<input type="hidden" name="employeeId" value="${empUpdateRequest.employeeId}"/>
                <label class="col-sm-2" for="employeeName">Employee</label>
                <div class="col-lg-4">
                    <input type="text" class="form-control" id="employeeName" name="employeeName" value="${empUpdateRequest.employeeName}" readonly="readonly"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <label class="col-sm-2" for="requestToId">Request To</label>
                <div class="col-lg-4">
                    <%-- <input type="text" class="form-control" id="requestToId" name="requestToId" value="${empUpdateRequest.requestToId}" required/> --%>
                    <select class="form-control" id="requestToId" name="requestToId" required>
                        <option value="">Select Request To</option>
                        <c:forEach items="${employees}" var="emp">
                            <option value="${emp.employeeId}" ${empUpdateRequest.requestToId == emp.employeeId ? 'selected' : ''}>${emp.name}</option>
                        </c:forEach>
                    </select>    
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <label class="col-sm-2" for="requestType">Request Type</label>
                <div class="col-lg-4">
                    <%-- <input type="text" class="form-control" id="requestToId" name="requestToId" value="${empUpdateRequest.requestToId}" required/> --%>
                    <select class="form-control" id="requestType" name="requestType" required>
                        <option value="">Select Request Type</option>
                        <option value="Employee Details" ${empUpdateRequest.requestType == 'Employee Details' ? 'selected' : ''}>Employee Details</option>
                        <option value="Designation" ${empUpdateRequest.requestType == 'Designation' ? 'selected' : ''}>Designation</option>
                        <option value="Grade" ${empUpdateRequest.requestType == 'Grade' ? 'selected' : ''}>Grade</option>
                        <option value="Compensation" ${empUpdateRequest.requestType == 'Compensation' ? 'selected' : ''}>Compensation</option>
                        <option value="Off Board" ${empUpdateRequest.requestType == 'Off Board' ? 'selected' : ''}>Off Board</option>
                    </select>    
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <label class="col-sm-2" for="comments">Comments</label>
                <div class="col-lg-4">
                    <textarea class="form-control" id="comments" name="comments">${empUpdateRequest.comments}</textarea>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-4">
                <button type="submit" class="btn btn-success">Submit</button>
                <a href="${pageContext.request.contextPath}/admin/employee-allocation/employee-list/${projectId}" class="btn btn-secondary">Cancel</a>
            </div>
        </div>
    </form>
</div>
<jsp:include page="../footer.jsp" />
</body>
</html>