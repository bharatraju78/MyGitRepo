<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%-- Fragment to render the contents of #estimateDetailsContainer. Expects model attribute 'detail' of type EstimateDetailModel --%>
<c:set var="ed" value="${detail != null ? detail : teaEstimate.estimateDetail}" />
<c:if test="${not empty ed}">
    <div class="card mb-3 nested-card estimate-detail">
        <div class="card-body">
            <div class="mt-3">
                <div class="d-flex justify-content-between align-items-center mb-2">
                    <h6 class="mb-0">Estimation Items</h6>
                    <button type="button" class="btn btn-sm btn-outline-primary js-add-estimation-item">+ Add Item</button>
                </div>
                <div class="estimation-items-container">
                    <table class="table table-striped table-bordered table-hover estimation-items-table">
                        <thead>
                        	<tr>
								<th colspan="9" class="text-center" >Engagement Name</th>
                      			<th colspan="1" class="text-center" title="Total Efforts in Person Hours, Inclusive of Assets and Accelerators & GenAI">Total Efforts(PH)</th>
                      			<th colspan="6" class="text-center" >Estimate hours by roles</th>
                      			<th colspan="3" class="text-center" >For resource loading</th>
                      			<th colspan="1" class="text-center" >&nbsp;</th>
							</tr>
                            <tr>
                            	<th colspan="9"><input style="width: 1600px" required class="form-control" name="estimateDetail.engagementName" value="${ed.engagementName}" /></td>
                                <th colspan="1"><input style="width: 80px" class="form-control" type="number" readonly="readonly" name="estimateDetail.totalEffortInPersonHours" value="${ed.totalEffortInPersonHours}" /></th>
                                <th colspan="1"><input style="width: 80px" class="form-control" type="number" readonly="readonly" name="estimateDetail.totalProjectManagementEffort" value="${ed.totalProjectManagementEffort}" /></th>
                                <th colspan="1"><input style="width: 80px" class="form-control" type="number" readonly="readonly" name="estimateDetail.totalBusinessAnalysisEffort" value="${ed.totalBusinessAnalysisEffort}" /></th>
                                <th colspan="1"><input style="width: 80px" class="form-control" type="number" readonly="readonly" name="estimateDetail.totalDevelopmentEffort" value="${ed.totalDevelopmentEffort}" /></th>
                                <th colspan="1"><input style="width: 80px" class="form-control" type="number" readonly="readonly" name="estimateDetail.totalQualityAssuranceEffort" value="${ed.totalQualityAssuranceEffort}" /></th>
                                <th colspan="1"><input style="width: 80px" class="form-control" type="number" readonly="readonly" name="estimateDetail.totalAssetsAndAcceleratorsEffort" value="${ed.totalAssetsAndAcceleratorsEffort}" /></th>
                                <th colspan="1"><input style="width: 80px" class="form-control" type="number" readonly="readonly" name="estimateDetail.totalGenAIEffort" value="${ed.totalGenAIEffort}" /></th>
                                <th colspan="1"><input style="width: 80px" class="form-control" type="number" readonly="readonly" name="estimateDetail.totalPersonHours" value="${ed.totalPersonHours}" /></th>
                                <th colspan="1"><input style="width: 80px" class="form-control" type="number" readonly="readonly" name="estimateDetail.totalPersonDays" value="${ed.totalPersonDays}" /></th>
                                <th colspan="1"><input style="width: 100px" class="form-control" type="number" readonly="readonly" name="estimateDetail.totalPersonMonths" value="${ed.totalPersonMonths}" /></th>
                                <th colspan="1"><input style="width: 60px" type="hidden" name="estimateDetail.estDetailId" value="${ed.estDetailId}" /><input type="hidden" name="estimateDetail.version" value="${ed.version}" /></th>
                            </tr>
                            <tr>
                                <th>ID</th>
                                <th>Track</th>
                                <th>Line Item</th>
                                <th>Classification</th>
                                <th>Assumption Reference</th>
                                <th>Opti</th>
                                <th>Pesi</th>
                                <th>Mean</th>
                                <th>3P Est</th>
                                <th>Tot Eff</th>
                                <th>PM</th>
                                <th>BA</th>
                                <th>Dev</th>
                                <th>QA</th>
                                <th>AnA</th>
                                <th>Gen AI</th>
                                <th>Per Hrs</th>
                                <th>Per Dys</th>
                                <th>Per Mnts</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="ei" items="${ed.estimationItems}" varStatus="eiStatus">
                                <tr data-index="${eiStatus.index}">
                                    <td><input class="form-control" style="width: 80px" type="number" readonly="readonly" name="estimateDetail.estimationItems[${eiStatus.index}].lineItemID" value="${ei.lineItemID}" /></td>
                                    <td><input class="form-control" style="width: 300px" type="text" name="estimateDetail.estimationItems[${eiStatus.index}].track" value="${ei.track}" placeholder="Track" /></td>
                                    <td><input class="form-control" style="width: 400px" type="text" name="estimateDetail.estimationItems[${eiStatus.index}].lineItem" value="${ei.lineItem}" placeholder="Line item" /></td>
                                    <td><input class="form-control" style="width: 300px" type="text" name="estimateDetail.estimationItems[${eiStatus.index}].classification" value="${ei.classification}" placeholder="Classification" /></td>
                                    <td><input class="form-control" style="width: 200px" type="text" name="estimateDetail.estimationItems[${eiStatus.index}].assumptionReference" value="${ei.assumptionReference}" placeholder="Assumption Ref" /></td>
                                    <td><input class="form-control" style="width: 80px" type="number" name="estimateDetail.estimationItems[${eiStatus.index}].optimisticPersonHours" value="${ei.optimisticPersonHours}" placeholder="Opt hrs" /></td>
                                    <td><input class="form-control" style="width: 80px" type="number" name="estimateDetail.estimationItems[${eiStatus.index}].pessimisticPersonHours" value="${ei.pessimisticPersonHours}" placeholder="Pesi hrs" /></td>
                                    <td><input class="form-control" style="width: 80px" type="number" name="estimateDetail.estimationItems[${eiStatus.index}].meanPersonHours" value="${ei.meanPersonHours}" placeholder="MP hrs" /></td>
                                    <td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="estimateDetail.estimationItems[${eiStatus.index}].threePointEstimate" value="${ei.threePointEstimate}" placeholder="3 P Est" /></td>
                                    <td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="estimateDetail.estimationItems[${eiStatus.index}].effortInPersonHours" value="${ei.effortInPersonHours}" placeholder="Tot hrs" /></td>
                                    <td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="estimateDetail.estimationItems[${eiStatus.index}].projectManagementEffort" value="${ei.projectManagementEffort}" placeholder="PM hrs" /></td>
                                    <td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="estimateDetail.estimationItems[${eiStatus.index}].businessAnalysisEffort" value="${ei.businessAnalysisEffort}" placeholder="BA hrs" /></td>
                                    <td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="estimateDetail.estimationItems[${eiStatus.index}].developmentEffort" value="${ei.developmentEffort}" placeholder="Dev hrs" /></td>
                                    <td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="estimateDetail.estimationItems[${eiStatus.index}].qualityAssuranceEffort" value="${ei.qualityAssuranceEffort}" placeholder="QA hrs" /></td>
                                    <td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="estimateDetail.estimationItems[${eiStatus.index}].assetsAndAcceleratorsEffort" value="${ei.assetsAndAcceleratorsEffort}" placeholder="AnA hrs" /></td>
                                    <td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="estimateDetail.estimationItems[${eiStatus.index}].genAIEffort" value="${ei.genAIEffort}" placeholder="GenAI hrs" /></td>
                                    <td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="estimateDetail.estimationItems[${eiStatus.index}].personHours" value="${ei.personHours}" placeholder="Per hrs" /></td>
                                    <td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="estimateDetail.estimationItems[${eiStatus.index}].personDays" value="${ei.personDays}" placeholder="Per days" /></td>
                                    <td><input class="form-control" style="width: 80px" readonly="readonly" type="number" name="estimateDetail.estimationItems[${eiStatus.index}].personMonths" value="${ei.personMonths}" placeholder="Per mnth" /></td>
                                    <td><button type="button" class="btn btn-sm btn-outline-danger remove-estimation-item" onclick="removeEstimationItem(${eiStatus.index});">Remove</button></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</c:if>
<div class="d-flex gap-2">
	<button type="button" class="btn btn-sm btn-outline-primary js-add-estimation-item">+ Add Item</button>
	<a class="btn btn-secondary" href="${pageContext.request.contextPath}/admin/tea-estimate/list">Cancel</a>
</div>
