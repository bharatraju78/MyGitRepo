<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="compensation-entry">
			<c:forEach items="${empCompModel.employeeCompensations}" var="empComp" varStatus="status">
                <div class="row">
                    <div class="form-group">
                        <label class="col-sm-1" for="startDate">Start Date</label>
                        <div class="col-lg-2">
                            <input type="date" class="form-control"
                                name="employeeCompensations[${status.index}].startDate"
                                value="<fmt:formatDate value='${empComp.startDate}' pattern='yyyy-MM-dd' />"
                                required />
                        </div>
                    </div>
                    <label class="col-sm-1">CTC</label>
                    <div class="col-lg-2">
                        <!-- Add salary fields here -->
                        <input type="number" class="form-control"
                            name="employeeCompensations[${status.index}].ctc"
                            value="${empComp.ctc}" />
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1" for="totalExp">Comments</label>
                        <div class="col-lg-4">
                            <textarea cols="2" rows="2" class="form-control"
                                name="employeeCompensations[${status.index}].comments">${empComp.comments }</textarea>
                        </div>
                    </div>
                    <button type="button" class="btn btn-danger remove-entry"
                        style="margin-left:10px;">
                        <i class="fa fa-trash" aria-hidden="true" title="Remove"></i>
                    </button>
                  </div>
                            
			</c:forEach>
			
		</div>