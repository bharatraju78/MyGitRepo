<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Add/Edit Employee Allocation</title>
    <link href="<c:url value='/resources/css/bootstrap.min.css' />" rel="stylesheet">
    <link href="<c:url value='/resources/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/css/style.css' />" rel="stylesheet">
    <!-- scripts -->
	<script src="<c:url value="/resources/script/jquery-3.1.0.min.js" />" type="text/javascript"></script>
	<script src="<c:url value="/resources/script/jquery.validate.min.js" />" type="text/javascript"></script>
	<script src="<c:url value="/resources/script/bootstrap.min.js" />" type="text/javascript"></script>
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
        <h2>${employeeAllocation.empAllId == null ? 'Add' : 'Edit'} Employee Allocation</h2>
        <form action="${pageContext.request.contextPath}/admin/employee-allocation/employee-allocation-save" method="post">
            <input type="hidden" name="empAllId" value="${employeeAllocation.empAllId}" />
            <input type="hidden" name="createdBy" value="${employeeAllocation.createdBy}" />
            <input type="hidden" name="modifiedBy" value="${employeeAllocation.modifiedBy}" />
            <input type="hidden" name="createdDate" value="${employeeAllocation.createdDate}" />
            <input type="hidden" name="modifiedDate" value="${employeeAllocation.modifiedDate}" />
            <input type="hidden" name="accountId" value="${employeeAllocation.accountId}" />
            <input type="hidden" name="projectId" value="${employeeAllocation.projectId}" />
            <input type="hidden" name="projectId" value="${employeeAllocation.projectId}" />
            <div class="row">
                <div class="form-group">
                    <label class="col-sm-2" for="projectName">Project Name</label>
                    <div class="col-lg-4">
                        <input type="text" class="form-control" id="projectName" name="projectName" value="${employeeAllocation.projectName}" required />
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <label class="col-sm-2" for="employeeName">Employee Name</label>
                    <div class="col-lg-4">
                        <%-- <input type="text" class="form-control" id="empName" name="empName" value="${employeeAllocation.empName}" required /> --%>
                        <select class="form-control" id="empId" name="empId" required>
                            <option value="">Select Employee</option>
                            <c:forEach var="employee" items="${employees}">
                                <option value="${employee.employeeId}" ${employeeAllocation.empId == employee.employeeId ? 'selected' : ''}>
                                    ${employee.name}
                                </option>
                            </c:forEach>
                         </select>   
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <label class="col-sm-2" for="employeeName">Role</label>
                    <div class="col-lg-4">
                        <%-- <input type="text" class="form-control" id="empName" name="empName" value="${employeeAllocation.empName}" required /> --%>
                        <select class="form-control" id="roleId" name="roleId" required>
                            <option value="">Select Role</option>
                            <c:forEach var="role" items="${roles}">
                                <option value="${role.projectRoleId}" ${employeeAllocation.roleId == role.projectRoleId ? 'selected' : ''}>
                                    ${role.roleName}
                                </option>
                            </c:forEach>
                          </select>  
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <label class="col-sm-2" for="startDate">Status</label>
                    <div class="col-lg-4">
                        <select class="form-control" id="status" name="status" required>
                            <option value="">Select Status</option>
                            <c:forEach var="statusStr" items="${statusList}">
                                <option value="${statusStr}" ${employeeAllocation.status == statusStr ? 'selected' : ''}>
                                    ${statusStr}
                                </option>
                            </c:forEach>    
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <label class="col-sm-2" for="startDate">Start Date</label>
                    <div class="col-lg-4">
                        <input type="date" class="form-control" id="startDate" name="startDate" value="${employeeAllocation.startDate}" required />
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <label class="col-sm-2" for="endDate">End Date</label>
                    <div class="col-lg-4">
                        <input type="date" class="form-control" id="endDate" name="endDate" value="${employeeAllocation.endDate}" />
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <label class="col-sm-2" for="createdBy">Created By</label>
                    <div class="col-lg-4">
                        <input type="text" class="form-control" value="${employeeAllocation.createdBy}" readonly="readonly" />
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <label class="col-sm-2" for="createdDate">Created Date</label>
                    <div class="col-lg-4">
                        <fmt:formatDate value="${employeeAllocation.createdDate}" pattern="dd-MM-yyyy" var="createdDate" />
                        <input type="text" class="form-control" value="${createdDate}" readonly="readonly" />
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <label class="col-sm-2" for="modifiedBy">Modified By</label>
                    <div class="col-lg-4">
                        <input type="text" class="form-control" value="${employeeAllocation.modifiedBy}" readonly="readonly" />
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <label class="col-sm-2" for="modifiedDate">Modified Date</label>
                    <div class="col-lg-4">
                        <fmt:formatDate value="${employeeAllocation.modifiedDate}" pattern="dd-MM-yyyy" var="modifiedDate" />
                        <input type="text" class="form-control" value="${modifiedDate}" readonly="readonly" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-4">
                    <button type="submit" class="btn btn-success">Save</button>
                    <a href="${pageContext.request.contextPath}/admin/employee-allocation/employee-allocation-list" class="btn btn-secondary">Cancel</a>
                </div>
            </div>
        </form>
    </div>
    <jsp:include page="../footer.jsp" />
</body>
</html>