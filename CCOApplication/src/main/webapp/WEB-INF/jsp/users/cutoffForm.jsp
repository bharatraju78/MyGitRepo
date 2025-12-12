<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Add/Edit Cut Off Parameters</title>
    <link href="<c:url value='/resources/css/bootstrap.min.css' />" rel="stylesheet">
    <link href="<c:url value='/resources/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/css/style.css' />" rel="stylesheet">
    <!-- scripts -->
	<script src="<c:url value="/resources/script/jquery-3.1.0.min.js" />"	type="text/javascript"></script>
	<script src="<c:url value="/resources/script/jquery.validate.min.js" />"	type="text/javascript"></script>
	<script src="<c:url value="/resources/script/bootstrap.min.js" />"	type="text/javascript"></script>
</head>
<body>
<jsp:include page="../header.jsp" />
<div class="container mt-4">
    <h2>${cutoff.id == null ? 'Add' : 'Edit'} Cut Off Parameters</h2>
    <c:if test="${not empty successMessage}"><div class="alert alert-success">${successMessage}</div></c:if>
    <c:if test="${not empty errorMessage}"><div class="alert alert-danger">${errorMessage}</div></c:if>
    <form id="cutoffForm" action="${pageContext.request.contextPath}/admin/cutoff/save" method="post">
        <input type="hidden" name="id" value="${cutoff.id}" />
        <div class="row mb-2">
            <label class="col-sm-2">Name</label>
            <div class="col-sm-4">
                <input class="form-control" name="name" value="${cutoff.name}" required />
            </div>
        </div>
        <div class="row mb-2">
            <label class="col-sm-2">Cut Percentage</label>
            <div class="col-sm-2">
                <input class="form-control" type="number" step="0.01" name="cutPercentage" value="${cutoff.cutPercentage}" />
            </div>
        </div>
        <div class="row mb-2">
            <label class="col-sm-2">Project Manager %</label>
            <div class="col-sm-2">
                <input class="form-control role-percent" type="number" step="0.01" max="1" name="projectManagerPercentage" value="${cutoff.projectManagerPercentage}" />
            </div>
            <label class="col-sm-2">Business Analyst %</label>
            <div class="col-sm-2">
                <input class="form-control role-percent" type="number" step="0.01" max="1" name="businessAnalystPercentage" value="${cutoff.businessAnalystPercentage}" />
            </div>
        </div>
        <div class="row mb-2">
            <label class="col-sm-2">Development %</label>
            <div class="col-sm-2">
                <input class="form-control role-percent" type="number" step="0.01" max="1" name="developmentPercentage" value="${cutoff.developmentPercentage}" />
            </div>
            <label class="col-sm-2">Quality Assurance %</label>
            <div class="col-sm-2">
                <input class="form-control role-percent" type="number" step="0.01" max="1" name="qualityAssurancePercentage" value="${cutoff.qualityAssurancePercentage}" />
            </div>
        </div>
        <div class="row mb-2">
            <label class="col-sm-2">Assets & Accelerator %</label>
            <div class="col-sm-2">
                <input class="form-control role-percent" type="number" step="0.01" max="1" name="assetsAndAcceleratorPercentage" value="${cutoff.assetsAndAcceleratorPercentage}" />
            </div>
            <label class="col-sm-2">GenAI %</label>
            <div class="col-sm-2">
                <input class="form-control role-percent" type="number" step="0.01" max="1" name="genAIPercentage" value="${cutoff.genAIPercentage}" />
            </div>
        </div>
        <div class="row mb-2">
            <label class="col-sm-2">Total %</label>
            <div class="col-sm-2">
                <input id="totalPercentage" class="form-control" type="number" step="0.01" name="totalPercentage" value="${cutoff.totalPercentage}" readonly />
            </div>
        </div>

        <div class="row mt-3">
            <div class="col-sm-4">
                <button type="submit" class="btn btn-success">Save</button>
                <a href="${pageContext.request.contextPath}/admin/cutoff/list" class="btn btn-secondary">Cancel</a>
            </div>
        </div>
    </form>
</div>

<script type="text/javascript">
(function($){
    'use strict';
    function computeTotal() {
        var sum = 0.0;
        $('.role-percent').each(function(){
            var v = $(this).val();
            if (v !== undefined && v !== null && v !== '') {
                var n = parseFloat(v);
                if (!isNaN(n)) sum += n;
            }
        });
        // round to 2 decimals
        sum = Math.round(sum * 100) / 100;
        $('#totalPercentage').val(sum);
        return sum;
    }

    $(function(){
        // compute on load
        computeTotal();
        // update when any role percent changes
        $('.role-percent').on('input change', function(){ computeTotal(); });

        // form submit validation
        $('#cutoffForm').on('submit', function(e){
            var total = computeTotal();
            // validate each role is <= 1
            var invalidField = null;
            $('.role-percent').each(function(){
                var v = $(this).val();
                if (v !== undefined && v !== null && v !== '') {
                    var n = parseFloat(v);
                    if (!isNaN(n) && n > 1) {
                        invalidField = $(this);
                        return false; // break
                    }
                }
            });
            if (invalidField) {
                e.preventDefault();
                alert('Each role percentage must be <= 1 (enter as fraction where 1 = 100%).');
                invalidField.focus();
                return false;
            }
            // allow small float rounding differences
            var eps = 0.001;
            if (Math.abs(total - 1) > eps) {
                e.preventDefault();
                alert('Total percentage of roles must equal 100% (current=' + (total * 100) + '%)');
                return false;
            }
             return true;
         });
     });
 })(jQuery);
</script>

<jsp:include page="../footer.jsp" />
</body>
</html>