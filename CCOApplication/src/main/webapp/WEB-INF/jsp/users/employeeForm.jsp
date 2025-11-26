<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Add/Edit Employee</title>
    <link href="<c:url value='/resources/css/bootstrap.min.css' />" rel="stylesheet">
    <link href="<c:url value='/resources/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/css/style.css' />" rel="stylesheet">
    <!-- scripts -->
	<script src="<c:url value="/resources/script/jquery-3.1.0.min.js" />" type="text/javascript"></script>
	<script src="<c:url value="/resources/script/jquery.validate.min.js" />" type="text/javascript"></script>
	<script src="<c:url value="/resources/script/bootstrap.min.js" />" type="text/javascript"></script>
	<script src="<c:url value="/resources/script/bootstrap3-typeahead.min.js"  />" type="text/javascript"></script>
	<script src="<c:url value="/resources/script/employee.js"  />" type="text/javascript"></script>
	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
      <script src="script/html5shiv.min.js"></script>
      <script src="script/respond.min.js"></script>
    <![endif]-->
</head>
<body>
    <jsp:include page="../header.jsp" />
    <c:if test="${empAll == null && empty actionFrom}">
    <div class="container mt-4">
        <h2>${employee.employeeId == null ? 'Add' : 'Edit'} Employee</h2>
        
			<div id="alert alert-danger" role="alert">
				<c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger">
                        ${errorMessage}
                    </div>
                 </c:if>
                 <c:if test="${not empty successMessage}">
                    <div class="alert alert-success">
                        ${successMessage}
                    </div>
                 </c:if>      
                 <input type="hidden" id="activeTab" name="activeTab" value="${activeTab}" />
			</div>
            <!-- Nav tabs -->
            <ul class="nav nav-tabs" role="tablist">
                <li class="nav-item active">
                    <a class="nav-link active" href="#empDetails" role="tab" data-toggle="tab">Employee Details</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#designation" id="designationTab" role="tab" data-toggle="tab">Designation</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#grade" role="tab" id="gradeTab" data-toggle="tab">Grade</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#salary" role="tab" id="salaryTab" data-toggle="tab">Compensation</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#offBoard" role="tab" id="offBoardTab" data-toggle="tab">Off Board</a>
                </li>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content" style="margin-top:20px;">
               <div role="tabpanel" class="tab-pane fade in active show" id="empDetails">
                	<jsp:useBean id="employee" class="com.vam.cco.dao.entity.Employee" scope="session"  />
                	
                	<jsp:include page="empDetails.jsp"></jsp:include>
                </div>
                <div role="tabpanel" class="tab-pane fade" id="designation">
                	<c:if test="${empty designationHistory}">
                		<jsp:include page="empDesignation.jsp" />
                	</c:if>
                	<c:if test="${not empty designationHistory}">
                		<jsp:include page="empDesignationList.jsp" />
                	</c:if>
                </div>
                <div role="tabpanel" class="tab-pane fade" id="grade">
                	<c:if test="${empty gradeHistory}">
                		<jsp:include page="empGrade.jsp" />
                	</c:if>
                	<c:if test="${not empty gradeHistory}">
                		<jsp:include page="empGradeList.jsp" />
                	</c:if>
                </div>
                <div role="tabpanel" class="tab-pane fade" id="salary">
                	<c:if test="${empty compHistory}">
                		<jsp:include page="empCompensation.jsp" />
                	</c:if>
                	<c:if test="${not empty compHistory}">
                		<jsp:include page="empCompensationList.jsp" />
                	</c:if>
                </div>
                <div role="tabpanel" class="tab-pane fade" id="offBoard">
                	<c:if test="${empty offBoardingHistory}">
                		<jsp:include page="empOffBoarding.jsp" />
                	</c:if>
                	<c:if test="${not empty offBoardingHistory}">
                		<jsp:include page="empOffBoardingList.jsp" />
                	</c:if>
                </div>
            </div>
    </div>
    </c:if>
    <c:if test="${empAll == true}">
    	<div class="container mt-4">
        <h2> Edit Associate</h2>
        <form action="${pageContext.request.contextPath}/admin/employee-allocation/update" method="post">
            <input type="hidden" name="empAllId" value="${employeeAllocation.empAllId}" />
            <div class="row">
            <div class="form-group">
                <label class="col-sm-2"  for="projectName">Project Name</label>
                <div class="col-lg-4">
                	<input type="text" class="form-control" id="projectName" name="projectName" value="${employeeAllocation.projectName}"  readonly="readonly"/>
            	</div>
            </div>
            </div>
            <div class="row">
            <div class="form-group">
                <label class="col-sm-2"  for="empName">Associate Name</label>
                <div class="col-lg-4">
                	<input type="text" class="form-control" id="empName" name="empName" value="${employeeAllocation.empName}"  readonly="readonly"/>
            	</div>
            </div>
            </div>
            <div class="row">
            <div class="form-group">
                <label class="col-sm-2"  for="startDate">Start Date</label>
                <div class="col-lg-4">
                	<fmt:formatDate value="${employeeAllocation.startDate}" pattern="dd-MM-yyyy" var="startDate" />
                	<input type="text" class="form-control" value="${employeeAllocation.startDateStr}" readonly="readonly"/>
            	</div>
            </div>
            </div>
            <div class="row">
            <div class="form-group">
                <label class="col-sm-2"  for="accName">Project Role</label>
                <div class="col-lg-4">
                	<select class="form-control" id="roleId" name="roleId" required>
                		<option value="">Select Role</option>
                		<c:forEach items="${projectRoles}" var="role">
                			<option value="${role.projectRoleId}" ${employeeAllocation.roleId == role.projectRoleId ? 'selected' : ''}>${role.roleName}</option>
                		</c:forEach>
                	</select>	
            	</div>
            </div>
            </div>
            <div class="row">
            <div class="form-group">
                <label class="col-sm-2"  for="accName">Associate Status</label>
                <div class="col-lg-4">
                	<select class="form-control" id="status" name="status" required>
                		<option value="">Select Status</option>
                		<c:forEach items="${statusList}" var="statusStr">
                			<option value="${statusStr}" ${employeeAllocation.status == statusStr ? 'selected' : ''}>${statusStr}</option>
                		</c:forEach>
                	</select>	
            	</div>
            </div>
            </div>
            <div class="row">
            <div class="form-group">
                <label class="col-sm-2"  for="createdBy">End Date</label>
                <div class="col-lg-4">
                <input type="hidden" id="endDateStr" name="endDateStr" value="${employeeAllocation.endDate}" />
                <input type="date" class="form-control" id="endDate" name="endDate"  required />
            	</div>
            </div>
            </div>
             <div class="form-group" style="margin-top:20px;">
		                <div class="col-sm-4">
		                    <button type="submit" class="btn btn-success">Save</button>
		                    <a href="${pageContext.request.contextPath}/admin/employee-allocation/employee-list/${projectId}" class="btn btn-secondary">Cancel</a>
		                </div>
	            	</div>
            </form>
           </div> 
    </c:if>
    <c:if test="${not empty actionFrom }">
    	<div class="container mt-4">
    	<h3>${employee.employeeId == null ? 'Add' : 'Edit'} (${employee.name}) ${requestType } Details</h3>
    		<c:choose>
    			<c:when test="${requestType == 'Employee Details'}">
    				<jsp:include page="../users/empDetails.jsp" />
    			</c:when>
    			<c:when test="${requestType == 'Designation'}">
    				<jsp:include page="../users/empDesignation.jsp" />
    			</c:when>
    			<c:when test="${requestType == 'Grade'}">
    				<jsp:include page="../users/empGrade.jsp" />
    			</c:when>
    			<c:when test="${requestType == 'Compensation'}">
    				<jsp:include page="../users/empCompensation.jsp" />
    			</c:when>
    			<c:when test="${requestType == 'Off Board'}">
    				<jsp:include page="../users/empOffBoarding.jsp" />
    			</c:when>	
    		</c:choose>	
    	</div>
    </c:if>
    <jsp:include page="../footer.jsp" />
</body>
</html>