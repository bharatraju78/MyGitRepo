<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<form
	action="${pageContext.request.contextPath}/admin/employees/employeeCompensation-save"
	method="post" name="empComp" id="empComp">
	<input type="hidden" name="id" value="${empComp.id}" />
	<input type="hidden" name="employeeId" value="${employee.employeeId}" />
	<input type="hidden" name="createdBy" value="${employee.createdBy}" />
	<input type="hidden" name="modifiedBy" value="${employee.modifiedBy}" />
	<input type="hidden" name="actionFrom" value="${actionFrom}" />
	<input type="hidden" name="requestType" value="${requestType}" />
	<input type="hidden" name="requestId" value="${requestId}" />

	<div id="compensation-entries">
		<c:forEach items="${empComp.employeeCompensations}" var="empComp" varStatus="status">
            <div class="compensation-entry">
			<div class="row">
				<div class="form-group">
					<div class="form-group">
						<label class="col-sm-1" for="startDate">Start Date</label>
						<div class="col-lg-2">
							<input type="date" class="form-control" name="employeeCompensations[${status.index }].startDate"
								value="<fmt:formatDate value='${empComp.startDate}' pattern='yyyy-MM-dd' />"
								required />
						</div>
					</div>
					<label class="col-sm-1">CTC</label>
					<div class="col-lg-2">
						<!-- Add salary fields here -->
						<input type="number" class="form-control" name="employeeCompensations[${status.index }].ctc"
							value="${empComp.ctc}" />
					</div>
					<div class="form-group">
						<label class="col-sm-1" for="totalExp">Comments</label>
						<div class="col-lg-4">
							<textarea cols="2" rows="2" class="form-control" name="employeeCompensations[${status.index }].comments">${empComp.comments }</textarea>
						</div>
					</div>
					<button type="button" class="btn btn-danger remove-entry"
						style="margin-left:10px;">
						<i class="fa fa-trash" aria-hidden="true" title="Remove"></i>
					</button>
				</div>
			</div>
		</div>
		</c:forEach>
		
	</div>
	<div class="form-group" style="margin-top: 20px;">
		<div class="col-sm-4">
			<button type="submit" id="saveBtn" style="display:none;" class="btn btn-primary">Save</button>
			<button type="button" id="checkBtn" class="btn btn-secondary">Validate</button>
			<c:if test="${empty actionFrom }">
				<a
					href="${pageContext.request.contextPath}/admin/employees/employee-list"
					class="btn btn-secondary">Cancel</a>
				<a
					href="${pageContext.request.contextPath}/admin/employees/employeeComp-list/${empDesg.employeeId}"
					class="btn btn-secondary">History</a>		
			</c:if>
			<c:if test="${'HR' == actionFrom }">
				<a
					href="${pageContext.request.contextPath}/admin/empUpdateRequest/showUserRequestsTo/<c:out value='${sessionScope.employeeId }'/>"
					class="btn btn-secondary">Back</a>
					
				<button type="button" class="btn btn-secondary" id="add-entry">Add Row</button>	
				<c:if test="${empty hideAddBtn }">
					<%-- <a
						href="${pageContext.request.contextPath}/admin/employees/addNewEmpRecord/${empComp.employeeId}/Compensation"
						class="btn btn-secondary">Add</a> --%>
				</c:if>	
			</c:if>
		</div>
	</div>
</form>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$(document).ready(function() {
    // Add new compensation entry
    $('#add-entry').click(function() {
        var $clone = $('.compensation-entry:first').clone();
        $clone.find('input, textarea').val('');
        $clone.find('input, textarea').each(function() {
            var name = $(this).attr('name');
            if (name) {
                // Update the index in the name attribute
                var newIndex = $('.compensation-entry').length;
                $(this).attr('name', name.replace(/\[\d+\]/, '[' + newIndex + ']'));
            }
        });
        $('#compensation-entries').append($clone);
    });
    // Remove compensation entry
    $(document).on('click', '.remove-entry', function() {
        if ($('.compensation-entry').length > 1) {
            $(this).closest('.compensation-entry').remove();
        }
    });
    
    $('#checkBtn').bind('click', function() {
    	var $entries = $('.compensation-entry');
        $entries.sort(function(a, b) {
            var aDate = $(a).find('input[name$=".startDate"]').val();
            var bDate = $(b).find('input[name$=".startDate"]').val();
            return new Date(aDate) - new Date(bDate);
        });
        $('#compensation-entries').html($entries);
        // Re-index the name attributes after sorting
        $entries.each(function(idx) {
            $(this).find('input, textarea').each(function() {
                var name = $(this).attr('name');
                if (name) {
                    $(this).attr('name', name.replace(/\[\d+\]/, '[' + idx + ']'));
                }
            });
        });
        if(!confirm('Rows are sorted by date, please check and submit the form?')) {
            e.preventDefault();
            return false;
        }else{
            $('#saveBtn').show();
            $('#checkBtn').hide();
        }
    });
    /* $('form[name="empComp"]').on('submit', function(e) {
        var $entries = $('.compensation-entry');
        $entries.sort(function(a, b) {
            var aDate = $(a).find('input[name$=".startDate"]').val();
            var bDate = $(b).find('input[name$=".startDate"]').val();
            return new Date(aDate) - new Date(bDate);
        });
        $('#compensation-entries').html($entries);
        // Re-index the name attributes after sorting
        $entries.each(function(idx) {
            $(this).find('input, textarea').each(function() {
                var name = $(this).attr('name');
                if (name) {
                    $(this).attr('name', name.replace(/\[\d+\]/, '[' + idx + ']'));
                }
            });
        });
        if(!confirm('Dates are sorted by dates, Are you sure you want to submit the form?')) {
            e.preventDefault();
            return false;
        }
    }); */
});
</script>