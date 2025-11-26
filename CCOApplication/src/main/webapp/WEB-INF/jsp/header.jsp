<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.Authentication" %>
<%
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = (auth != null) ? auth.getName() : "Guest";
%>
<nav id="header" class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<div id="sidebar-toggle-button">
				&nbsp;
			</div>
			<div class="brand">
				<a href="${pageContext.request.contextPath}/home"> Value Momentum 
				</a>
				<input type="hidden" id="contextPath" name="contextPath" value="${pageContext.request.contextPath}"/>
				<security:csrfInput/>
			</div>
		</div>
		
		 <ul class="nav navbar-nav">
		 	<security:authorize access="hasRole('ROLE_ADMIN')">
				<li class="dropdown" id="adminDropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						Admin
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
				          <li><a href='${pageContext.request.contextPath}/admin/roles/role-list'>Admin Roles</a></li>
				          <li><a href="${pageContext.request.contextPath}/admin/users/showUsers">Admin Users</a></li>
				          <li><a href="javascript:getRolesAndLabelsData()">Map Roles Labels</a></li>
			        </ul>
				</li>
			</security:authorize>
			<security:authorize access="hasRole('ROLE_POWER_USER') or hasRole('ROLE_ADMIN')">
			    <li class="dropdown" id="otherDropdown">
			    	<a href="#" class="dropdown-toggle" data-toggle="dropdown">
			    			Masters
			    		<span class="caret"></span>
			    	</a>
			    	<ul class="dropdown-menu">
			    		 <li><a href="${pageContext.request.contextPath}/admin/account/account-list">Account</a></li>
			    		 <li><a href="${pageContext.request.contextPath}/admin/designations/list">Designations</a></li>
			    		 <li><a href="${pageContext.request.contextPath}/admin/grades/list">Grade</a></li>
			    		 <li><a href="${pageContext.request.contextPath}/admin/labels/labels-list">Labels</a></li>
			    		 <li><a href="${pageContext.request.contextPath}/admin/portfolio/mapAccounts">Map Accounts to Portfolio</a></li>
			    		 <li><a href="${pageContext.request.contextPath}/admin/map-associates/accountList">Map Associates</a></li>
			    		 <li><a href="${pageContext.request.contextPath}/admin/portfolio/list">Portfolio</a></li>
			             <li><a href="${pageContext.request.contextPath}/admin/projects/project-list">Project</a></li>
			             <li><a href="${pageContext.request.contextPath}/admin/project-roles/project-role-list">Project Roles</a></li>
			             <li><a href="${pageContext.request.contextPath}/admin/skills/list">Skills</a></li>
			             <li><a href="${pageContext.request.contextPath}/admin/verticals/list">Vertical</a></li>
			        </ul>
			    </li>
			    </security:authorize>
			    <security:authorize access="hasRole('ROLE_HR') or hasRole('ROLE_ADMIN')">
			     <li class="dropdown" id="otherDropdown">
			    	<a href="#" class="dropdown-toggle" data-toggle="dropdown">
			    		Actions
			    		<span class="caret"></span>
			    	</a>
			    	<ul class="dropdown-menu">
			    		 <%-- <c:if test="${fn:contains(mappedHeaders,'Employee')}">
			          		<li><a href="javascript:getEmployeesData();">Employee</a></li>
			            </c:if> --%>
			            <li><a href="${pageContext.request.contextPath}/admin/employees/employee-list">View Employees</a></li>
			            <li><a href="${pageContext.request.contextPath}/admin/empUpdateRequest/showUserRequestsTo/<c:out value="${sessionScope.employeeId }"/>">Service Request</a></li>
			    	</ul>
			     </li>	
			     </security:authorize>
			     <security:authorize access="!hasRole('ROLE_HR')  and !hasRole('ROLE_POWER_USER')">
			     <li class="dropdown" id="otherDropdown">
			    	<a href="#" class="dropdown-toggle" data-toggle="dropdown">
			    		<security:authorize access="hasRole('ROLE_ADMIN')">
			    			Allocation
			    		</security:authorize>
			    		<security:authorize access="!hasRole('ROLE_ADMIN')">
			    			Actions
			    		</security:authorize>
			    		<span class="caret"></span>
			    	</a>
			    	<ul class="dropdown-menu">
			    		<%-- <c:if test="${fn:contains(mappedHeaders,'Employee Allocation')}">
			         		 <li><a href="javascript:showEmpAllPage();">Employee Allocation</a></li>
			         	 </c:if> --%>
			         	 <li><a href="${pageContext.request.contextPath}/admin/employee-allocation/employee-allocation-list">View Account</a></li>
			         	 <li><a href="${pageContext.request.contextPath}/admin/empUpdateRequest/showUserRequestsBy/<c:out value="${sessionScope.employeeId }"/>">Service Request</a></li>
			    	</ul>
			     </li>	
			     </security:authorize>
		</ul>
		<div class="navbar-header" style="float: right;">
			<ul class="nav navbar-nav navbar-right">
			<li class="dropdown" id="otherDropdown">
			    	<a href="#" class="dropdown-toggle" data-toggle="dropdown">
			    		<%= username %>
			    		<span class="caret"></span>
			    	</a>
			    	<ul class="dropdown-menu">
			    		 <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
			    	</ul>
			     </li>	
            </ul>
		</div>	
	</div>
</nav>