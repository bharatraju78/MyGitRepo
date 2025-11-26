<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<form
	action="${pageContext.request.contextPath}/admin/employees/employeeOffBoard-save"
	method="post">
	<div class="row">
		<input type="hidden" name="id" value="${empOffBoarding.id}" />
		<input type="hidden" name="employeeId" value="${employee.employeeId}" />
		<input type="hidden" name="createdBy" value="${empOffBoarding.createdBy}" /> 
		<input type="hidden" name="modifiedBy" value="${empOffBoarding.modifiedBy}" /> 
		<input type="hidden" name="actionFrom" value="${actionFrom}" />
		<input type="hidden" name="requestType" value="${requestType}" />
		<input type="hidden" name="requestId" value="${requestId}" />
		<div class="form-group">
			<label class="col-sm-2" for="status">Status</label>
			<div class="col-lg-4">
				<select class="form-control" id="status" name="status" required>
					<option value="">Select Status</option>
					<c:forEach items="${status}" var="statusVar">
						<option value="${statusVar}"
							${empOffBoarding.status == statusVar ? 'selected' : ''}>${statusVar}</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="form-group">
			<label class="col-sm-2" for="resignationDate">
				Date</label>
			<div class="col-lg-4">
				<input type="date" class="form-control" id="resignationDate"
					name="resignationDate"
					value="<fmt:formatDate value="${empOffBoarding.resignationDate}" pattern="yyyy-MM-dd" />"
					required />
			</div>
		</div>
	</div>
	<div class="row">
		<div class="form-group">
			<label class="col-sm-2" for="comments">Comments</label>
			<div class="col-lg-4">
				<textarea cols="2" rows="4" class="form-control" id="comments"
					name="comments">${empOffBoarding.comments }</textarea>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="form-group">
			<label class="col-sm-2" for="createdBy">Created By</label>
			<div class="col-lg-4">
				<input type="text" class="form-control"
					value="${empOffBoarding.createdBy}" readonly="readonly" />
			</div>
		</div>
	</div>
	<div class="row">
		<div class="form-group">
			<label class="col-sm-2" for="createdDate">Created Date</label>
			<div class="col-lg-4">
				<fmt:formatDate value="${empOffBoarding.createdDate}"
					pattern="yyy-MM-dd" var="createdDate" />
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
					value="${empOffBoarding.modifiedBy}" readonly="readonly" />
			</div>
		</div>
	</div>
	<div class="row">
		<div class="form-group">
			<label class="col-sm-2" for="modifiedDate">Modified Date</label>
			<div class="col-lg-4">
				<fmt:formatDate value="${empOffBoarding.modifiedDate}"
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
				<a
					href="${pageContext.request.contextPath}/admin/employees/employeeOffBoarding-list/${empOffBoarding.employeeId}"
					class="btn btn-secondary">History</a>		
			</c:if>
			<c:if test="${'HR' == actionFrom }">
				<a
					href="${pageContext.request.contextPath}/admin/empUpdateRequest/showUserRequestsTo/<c:out value="${sessionScope.employeeId }"/>"
					class="btn btn-secondary">Back</a>
				<c:if test="${empty hideAddBtn }">
					<%-- <a
						href="${pageContext.request.contextPath}/admin/employees/addNewEmpRecord/${empOffBoarding.employeeId}/OffBoarding"
						class="btn btn-secondary">Add</a> --%>
				</c:if>	
			</c:if>
		</div>
	</div>
</form>
