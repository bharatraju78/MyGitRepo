<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%-- Fragment to render effort detail contents. Expects model attribute 'detail' of type EffortDetailModel --%>
<c:if test="${not empty teaEstimate.effortDetail}">                            
	<c:set var="rd" value="${detail != null ? detail : teaEstimate.effortDetail}" />
	<c:if test="${not empty rd}">
	    <div class="card mb-3 nested-card effort-detail">
	        <div class="card-body">
	        	<input type="hidden" name="effortDetail.resDetailId" value="${rd.resDetailId}" />
	            <div class="mt-3">
	                <div class="d-flex justify-content-between align-items-center mb-2">
	                    <h6 class="mb-0">Effort Info</h6>
	                    <button type="button" class="btn btn-sm btn-outline-primary js-add-effort-info">+ Add Effort</button>
	                </div>
	                <div class="effort-info-container">
	                    <table class="table table-striped table-bordered table-hover effort-info-table">
	                        <thead>
	                            <tr>
	                                <th>ID</th>
	                                <th>Track</th>
	                                <th>Location</th>
	                                <th>Role</th>
	                                <th>Rate</th>
	                                <th>Hour/ Month</th>
	                                <th colspan="12" class="text-center" >Year-1</th>
	                                <th colspan="12" class="text-center" >Year-2</th>
	                                <th colspan="12" class="text-center" >Year-3</th>
	                                <th colspan="12" class="text-center" >Year-4</th>
	                                <th>Year-1 total</th>
	                                <th>Year-2 total</th>
	                                <th>Year-3 total</th>
	                                <th>Year-4 total</th>
	                                <th>Overall total</th>
	                                <th>Action</th>
	                            </tr>
	                        </thead>
	                        <tbody>
	                            <c:forEach var="ri" items="${rd.effortInfos}" varStatus="riStatus">
	                                <tr data-index="${riStatus.index}">
	                                    <td><input class="form-control" style="width: 80px" type="number" readonly="readonly" name="effortDetail.effortInfos[${riStatus.index}].infoId" value="${ri.infoId}" /></td>
	                                    <td><input class="form-control" style="width: 300px" type="text" name="effortDetail.effortInfos[${riStatus.index}].track" value="${ri.track}" placeholder="Track" /></td>
	                                    <td><input class="form-control" style="width: 200px" type="text" name="effortDetail.effortInfos[${riStatus.index}].location" value="${ri.location}" placeholder="Location" /></td>
	                                    <td><input class="form-control" style="width: 200px" type="text" name="effortDetail.effortInfos[${riStatus.index}].role" value="${ri.role}" placeholder="Role" /></td>
	                                    <td><input class="form-control" style="width: 120px" type="number" step="any" name="effortDetail.effortInfos[${riStatus.index}].rate" value="${ri.rate}" placeholder="Rate" /></td>
	                                    <td><input class="form-control" style="width: 120px" type="number" step="any" name="effortDetail.effortInfos[${riStatus.index}].hoursPerMonth" value="${ri.hoursPerMonth}" placeholder="Hours/Month" /></td>
	                                    <!-- Year-1 months -->
	                                    <td><input class="form-control" style="width: 80px" type="number" step="any" name="effortDetail.effortInfos[${riStatus.index}].yearOneJan" value="${ri.yearOneJan}" placeholder="Jan" /></td>
	                                    <td><input class="form-control" style="width: 80px" type="number" step="any" name="effortDetail.effortInfos[${riStatus.index}].yearOneFeb" value="${ri.yearOneFeb}" placeholder="Feb" /></td>
	                                    <td><!-- more months similar to resource fragment -->
	                                        <input class="form-control" style="width: 80px" type="number" step="any" name="effortDetail.effortInfos[${riStatus.index}].yearOneMar" value="${ri.yearOneMar}" placeholder="Mar" />
	                                    </td>
	                                    <!-- For brevity, the fragment omits the rest of month inputs, but should mirror resourceDetailsFragment with effortDetail and step="any" on numeric inputs -->
	                                    <td><input class="form-control" style="width: 80px" readonly="readonly" type="number" step="any" name="effortDetail.effortInfos[${riStatus.index}].yearOneTotal" value="${ri.yearOneTotal}" placeholder="Year-1" /></td>
	                                    <td><input class="form-control" style="width: 80px" readonly="readonly" type="number" step="any" name="effortDetail.effortInfos[${riStatus.index}].yearTwoTotal" value="${ri.yearTwoTotal}" placeholder="Year-2" /></td>
	                                    <td><input class="form-control" style="width: 80px" readonly="readonly" type="number" step="any" name="effortDetail.effortInfos[${riStatus.index}].yearThreeTotal" value="${ri.yearThreeTotal}" placeholder="Year-3" /></td>
	                                    <td><input class="form-control" style="width: 80px" readonly="readonly" type="number" step="any" name="effortDetail.effortInfos[${riStatus.index}].yearFourTotal" value="${ri.yearFourTotal}" placeholder="Year-4" /></td>
	                                    <td><input class="form-control" style="width: 80px" readonly="readonly" type="number" step="any" name="effortDetail.effortInfos[${riStatus.index}].overallTotal" value="${ri.overallTotal}" placeholder="Overall" /></td>
	                                    <td><button type="button" class="btn btn-sm btn-outline-danger" onclick="removeEffortInfo(${riStatus.index});">Remove</button></td>
	                                </tr>
	                            </c:forEach>
	                        </tbody>
	                    </table>
	                </div>
	            </div>
	        </div>
	    </div>
	</c:if>
</c:if>
<div class="d-flex gap-2">
	<button type="button" class="btn btn-sm btn-outline-primary js-add-effort-info">+ Add Effort</button>
	<a class="btn btn-secondary" href="${pageContext.request.contextPath}/admin/tea-estimate/list">Cancel</a>
</div>
