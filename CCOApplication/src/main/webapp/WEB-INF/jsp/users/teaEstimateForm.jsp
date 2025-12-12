<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Tea Estimate</title>
    <link href="<c:url value='/resources/css/bootstrap.min.css' />" rel="stylesheet">
    <link href="<c:url value='/resources/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/css/style.css' />" rel="stylesheet">
    <!-- scripts -->
	<script src="<c:url value="/resources/script/jquery-3.1.0.min.js" />" type="text/javascript"></script>
	<script src="<c:url value="/resources/script/bootstrap.min.js" />" type="text/javascript"></script>
	<script src="<c:url value="/resources/script/jquery.validate.min.js" />" type="text/javascript"></script>
	<script src="<c:url value="/resources/script/bootstrap3-typeahead.min.js"  />" type="text/javascript"></script>
	<script src="<c:url value='/resources/script/teaEstimateForm.js' />" type="text/javascript"></script>
</head>
<body>
	<jsp:include page="../header.jsp" />
<div class="container-fluid">

    <div class="d-flex justify-content-between align-items-center mb-3">
        <h3 class="mb-0">Tea Estimate</h3>
        <div>
            <a class="btn btn-outline-secondary btn-sm" href="${pageContext.request.contextPath}/admin/tea-estimate/list">Back to list</a>
        </div>
    </div>

    <c:if test="${not empty successMessage}">
        <div class="alert alert-success">${successMessage}</div>
    </c:if>
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger">${errorMessage}</div>
    </c:if>

    <form id="teaEstimateForm" method="post" action="${pageContext.request.contextPath}/admin/tea-estimate/save" novalidate>
        <input type="hidden" name="id" value="${teaEstimate.id}" />

        <!-- Tabs (Bootstrap 3 markup) -->
        <ul class="nav nav-tabs mb-3" id="teaTabs" role="tablist">
            <li class="active"><a href="#generalSection" data-toggle="tab" id="general-tab">General</a></li>
            <li><a href="#estimateDetailsSection" data-toggle="tab" id="estimate-tab">Estimate Details</a></li>
            <li><a href="#resourceDetailsSection" data-toggle="tab" id="resource-tab">Resource Details</a></li>
        </ul>

        <div class="tab-content">
            <!-- General section -->
            <div class="tab-pane active" id="generalSection" role="tabpanel" aria-labelledby="general-tab">
                <div class="card card-panel mb-3">
                    <div class="card-body">
                        <div class="row g-3">
                            <div class="col-md-6">
                                <label class="form-label required">Engagement Name</label>
                                <input required class="form-control ${not empty org.springframework.validation.BindingResult.teaEstimate.fieldErrors['engagementName'] ? 'is-invalid' : ''}" name="engagementName" value="${teaEstimate.engagementName}" />
                                <form:errors path="engagementName" cssClass="invalid-feedback d-block" />
                            </div>
                            <div class="col-md-6">
                                <label class="form-label required">Client / Prospect</label>
                                <input required class="form-control ${not empty org.springframework.validation.BindingResult.teaEstimate.fieldErrors['clientOrProspect'] ? 'is-invalid' : ''}" name="clientOrProspect" value="${teaEstimate.clientOrProspect}" />
                                <form:errors path="clientOrProspect" cssClass="invalid-feedback d-block" />
                            </div>
                            <div class="col-md-6">
                                <label class="form-label">Project Start Date</label>
                                <input class="form-control ${not empty org.springframework.validation.BindingResult.teaEstimate.fieldErrors['projectStartDate'] ? 'is-invalid' : ''}" name="projectStartDate" placeholder="yyyy-MM-dd" pattern="\\d{4}-\\d{2}-\\d{2}" value="${teaEstimate.projectStartDate}" />
                                <form:errors path="projectStartDate" cssClass="invalid-feedback d-block" />
                                <div class="form-text small-muted">Format: yyyy-MM-dd</div>
                            </div>
                            <div class="col-md-6">
                                <label class="form-label">Estimated By</label>
                                <input class="form-control" name="estimatedBy" value="${teaEstimate.estimatedBy}" />
                            </div>
                            <div class="col-md-6">
                                <label class="form-label">Verified By</label>
                                <input class="form-control" name="verifiedBy" value="${teaEstimate.verifiedBy}" />
                            </div>
                            <div class="col-md-12">
                                <label class="form-label">Business Objective</label>
                                <textarea class="form-control" rows="2" name="businessObjective">${teaEstimate.businessObjective}</textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Estimate Details (section ID added) -->
            <div class="tab-pane" id="estimateDetailsSection" role="tabpanel" aria-labelledby="estimate-tab">
                <div class="card card-panel mb-3">
                    <div class="card-header d-flex justify-content-between align-items-center">
                        <strong>Estimate Details</strong>
                     </div>
                     <div class="card-body">
                         <div id="estimateDetailsContainer">
                            <!-- Server-include the estimate details fragment so the initial page uses the same fragment the AJAX endpoints return -->
                            <jsp:include page="fragments/estimateDetailsFragment.jsp" />
                         </div>
                     </div>
                 </div>
             </div>

             <!-- Resource Details (section ID added) -->
             <div class="tab-pane" id="resourceDetailsSection" role="tabpanel" aria-labelledby="resource-tab">
                 <div class="card card-panel mb-3">
                     <div class="card-header d-flex justify-content-between align-items-center">
                         <strong>Resource Details</strong>
                     </div>
                     <div class="card-body">
                         <div id="resourceDetailsContainer">
                            <!-- Converted to singular resourceDetail on TeaEstimateModel -->
                            <jsp:include page="fragments/resourceDetailsFragment.jsp" />
                         </div>
                     </div>
                 </div>
             </div>
        </div>

        <div class="d-flex gap-2">
        	<!-- <button type="button" class="btn btn-sm btn-outline-primary js-add-estimation-item">+ Add Item</button> -->
            <button type="submit" class="btn btn-success">Save</button>
        </div>

    </form>
</div>

<!-- Nested remove confirmation modal -->
<div class="modal fade" id="nestedDeleteModal" tabindex="-1" aria-labelledby="nestedDeleteModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-sm modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="nestedDeleteModalLabel">Confirm Remove</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        Remove this item?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary btn-sm" data-bs-dismiss="modal">Cancel</button>
        <button type="button" id="nestedRemoveConfirmBtn" class="btn btn-danger btn-sm">Remove</button>
      </div>
    </div>
  </div>
</div>