<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form
	action="${pageContext.request.contextPath}/admin/employees/employeeDetails-save"
	method="post">
	<input type="hidden" name="employeeId" value="${employee.employeeId}" />
	<input type="hidden" name="createdBy" value="${employee.createdBy}" />
	<input type="hidden" name="modifiedBy" value="${employee.modifiedBy}" />
	<input type="hidden" name="actionFrom" value="${actionFrom}" />
	<input type="hidden" name="requestType" value="${requestType}" />
	<input type="hidden" name="requestId" value="${requestId}" />
	<div class="row">
		<div class="form-group">
			<label class="col-sm-2" for="vamId">VAM ID</label>
			<div class="col-lg-4">
				<input type="text" class="form-control" id="vamId" name="vamId"
					value="${employee.vamId}" required />
			</div>
		</div>
	</div>
	<div class="row">
		<div class="form-group">
			<label class="col-sm-2" for="name">Employee Name</label>
			<div class="col-lg-4">
				<input type="text" class="form-control" id="name" name="name"
					value="${employee.name}" required />
			</div>
		</div>
	</div>
	<div class="row">
		<div class="form-group">
			<label class="col-sm-2" for="doj">Date Of Joining</label>
			<div class="col-lg-4">
				<input type="date" class="form-control" id="doj" name="doj"
					value="<fmt:formatDate value='${employee.doj}' pattern='yyyy-MM-dd'/>"
					required />
			</div>
		</div>
	</div>
	<div class="row">
		<div class="form-group">
			<label class="col-sm-2" for="name">Email</label>
			<div class="col-lg-4">
				<input type="text" class="form-control" id="emailId" name="emailId"
					value="${employee.emailId}" required />
			</div>
		</div>
	</div>
	<div class="row">
		<div class="form-group">
			<label class="col-sm-2" for="status">Employee Status</label>
			<div class="col-lg-4">
				<select class="form-control" id="status" name="status" required>
					<option value="">Select Status</option>
					<c:forEach items="${status}" var="statusVar">
						<option value="${statusVar}"
							${employee.status == statusVar ? 'selected' : ''}>${statusVar}</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="form-group">
			<label class="col-sm-2" for="vamExp">VAM Experience</label>
			<div class="col-lg-4">
				<input type="text" class="form-control" id="vamExp" name="vamExp"
					value="${employee.vamExp}" required />
			</div>
		</div>
	</div>
	<div class="row">
		<div class="form-group">
			<label class="col-sm-2" for="totalExp">Total Experience</label>
			<div class="col-lg-4">
				<input type="text" class="form-control" id="totalExp"
					name="totalExp" value="${employee.totalExp}" required />
			</div>
		</div>
	</div>
	<div class="row">
		<div class="form-group">
			<label class="col-sm-2" for="password">Password</label>
			<div class="col-lg-4">
				<input type="password" class="form-control" id="password"
					name="password" value="${employee.password}" required />
			</div>
		</div>
	</div>
	<div class="row">
		<div class="form-group">
			<label class="col-sm-2" for="createdBy">Created By</label>
			<div class="col-lg-4">
				<input type="text" class="form-control"
					value="${employee.createdBy}" readonly="readonly" />
			</div>
		</div>
	</div>
	<div class="row">
		<div class="form-group">
			<label class="col-sm-2" for="createdDate">Created Date</label>
			<div class="col-lg-4">
				<fmt:formatDate value="${employee.createdDate}" pattern="yyyy-MM-dd"
					var="createdDate" />
				<input type="date" class="form-control" id="createdDate"
					name="createdDate" value="${createdDate}" readonly="readonly" />
			</div>
		</div>
	</div>
	<div class="row">
		<div class="form-group">
			<label class="col-sm-2" for="modifiedBy">Modified By</label>
			<div class="col-lg-4">
				<input type="text" class="form-control"
					value="${employee.modifiedBy}" readonly="readonly" />
			</div>
		</div>
	</div>
	<div class="row">
		<div class="form-group">
			<label class="col-sm-2" for="modifiedDate">Modified Date</label>
			<div class="col-lg-4">
				<fmt:formatDate value="${employee.modifiedDate}"
					pattern="yyyy-MM-dd" var="modifiedDate" />
				<input type="date" class="form-control" id="modifiedDate"
					name="modifiedDate" value="${modifiedDate}" readonly="readonly" />
			</div>
		</div>
	</div>
	<div class="form-group" style="margin-top: 20px;">
		<div class="col-sm-4">
			<button type="submit" class="btn btn-success">Save</button>
			<c:if test="${empty actionFrom }">
				<a
					href="${pageContext.request.contextPath}/admin/employees/employee-list"
					class="btn btn-secondary">Cancel</a>
			</c:if>
			<c:if test="${'HR' == actionFrom }">
				<a
					href="${pageContext.request.contextPath}/admin/empUpdateRequest/showUserRequestsTo/<c:out value="${sessionScope.employeeId }"/>"
					class="btn btn-secondary">Back</a>
			</c:if>
		</div>
	</div>
</form>