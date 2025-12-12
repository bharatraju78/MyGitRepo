<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Tea Estimate List</title>
    <!-- Bootstrap 5 CSS -->
    <!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-" crossorigin="anonymous"> -->
    <link href="<c:url value='/resources/css/bootstrap.min.css' />" rel="stylesheet">
    <link href="<c:url value='/resources/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/css/style.css' />" rel="stylesheet">
    <!-- scripts -->
	<script src="<c:url value="/resources/script/jquery-3.1.0.min.js" />"	type="text/javascript"></script>
	<script src="<c:url value="/resources/script/jquery.validate.min.js" />"	type="text/javascript"></script>
	<script src="<c:url value="/resources/script/bootstrap.min.js" />"	type="text/javascript"></script>
	<script src="<c:url value="/resources/script/bootstrap3-typeahead.min.js"  />" type="text/javascript"></script>
	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
      <script src="script/html5shiv.min.js"></script>
      <script src="script/respond.min.js"></script>
    <![endif]-->
    <!-- <style>
        .table-actions a { margin-right: 6px; }
        .top-actions { display:flex; gap:10px; align-items:center; }
        .search-input { max-width: 340px; }
        .nowrap { white-space: nowrap; }
    </style> -->
</head>
<body>
<div class="container my-4">
<jsp:include page="../header.jsp" />
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h2 class="mb-0">Tea Estimates</h2>
        <div class="top-actions">
            <a href="${pageContext.request.contextPath}/admin/tea-estimate/add" class="btn btn-primary">Add New</a>
            <a href="${pageContext.request.contextPath}/admin/tea-estimate/list" class="btn btn-outline-secondary">Refresh</a>
        </div>
    </div>

    <div class="row mb-3">
        <div class="col-md-6">
            <c:if test="${not empty successMessage}">
                <div class="alert alert-success">${successMessage}</div>
            </c:if>
            <c:if test="${not empty errorMessage}">
                <div class="alert alert-danger">${errorMessage}</div>
            </c:if>
        </div>
        <div class="col-md-6 text-md-end">
            <!-- Search form (GET) - backend can handle 'q' param if implemented -->
            <form class="d-flex justify-content-md-end" method="get" action="${pageContext.request.contextPath}/admin/tea-estimate/list">
                <input name="q" class="form-control form-control-sm me-2 search-input" type="search" placeholder="Search by engagement or client" aria-label="Search" value="${param.q}" />
                <input type="hidden" name="size" value="${size}" />
                <button class="btn btn-sm btn-outline-primary" type="submit">Search</button>
            </form>
        </div>
    </div>

    <div class="card">
        <div class="card-body p-0">
            <div class="table-responsive">
                <table class="table table-striped table-hover mb-0">
                    <thead class="table-light">
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Engagement Name</th>
                        <th scope="col">Client / Prospect</th>
                        <th scope="col">Project Start Date</th>
                        <th scope="col" class="text-end">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${empty teaEstimateModels}">
                        <tr>
                            <td colspan="5" class="text-center py-4">No records found.</td>
                        </tr>
                    </c:if>
                    <c:forEach var="t" items="${teaEstimateModels}">
                        <tr>
                            <td class="align-middle">${t.id}</td>
                            <td class="align-middle">${t.engagementName}</td>
                            <td class="align-middle">${t.clientOrProspect}</td>
                            <td class="align-middle"> <c:out value="${t.projectStartDate}"/> </td>
                            <td class="align-middle nowrap text-end">
                                <a class="btn btn-sm btn-outline-primary" title="Edit" href="${pageContext.request.contextPath}/admin/tea-estimate/edit/${t.id}">
                                    <i class="bi bi-pencil"></i> Edit
                                </a>
                                <button type="button" class="btn btn-sm btn-outline-danger" data-id="${t.id}" data-name="${t.engagementName}" onclick="confirmDelete(this)">
                                    <i class="bi bi-trash"></i> Delete
                                </button>
                                <a class="btn btn-sm btn-outline-secondary" title="View" href="${pageContext.request.contextPath}/admin/tea-estimate/view/${t.id}">View</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <!-- Pagination -->
    <div class="d-flex justify-content-between align-items-center mt-3">
        <div class="small text-muted">Showing <strong>${teaEstimateModels.size()}</strong> results</div>
        <div>
            <nav aria-label="Page navigation">
                <ul class="pagination mb-0">
                    <c:set var="currPage" value="${page != null ? page : 1}" />
                    <c:set var="pageSize" value="${size != null ? size : 10}" />
                    <c:set var="totalPagesVar" value="${totalPages != null ? totalPages : 1}" />

                    <li class="page-item ${currPage <= 1 ? 'disabled' : ''}">
                        <a class="page-link" href="${pageContext.request.contextPath}/admin/tea-estimate/list?page=${currPage-1}&size=${pageSize}${param.q != null ? '&q=' + param.q : ''}">Prev</a>
                    </li>

                    <c:forEach begin="1" end="${totalPagesVar}" var="p">
                        <li class="page-item ${p == currPage ? 'active' : ''}">
                            <a class="page-link" href="${pageContext.request.contextPath}/admin/tea-estimate/list?page=${p}&size=${pageSize}${param.q != null ? '&q=' + param.q : ''}">${p}</a>
                        </li>
                    </c:forEach>

                    <li class="page-item ${currPage >= totalPagesVar ? 'disabled' : ''}">
                        <a class="page-link" href="${pageContext.request.contextPath}/admin/tea-estimate/list?page=${currPage+1}&size=${pageSize}${param.q != null ? '&q=' + param.q : ''}">Next</a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>

</div>

<!-- Delete confirmation modal -->
<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="deleteModalLabel">Confirm Delete</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Are you sure you want to delete <strong id="deleteTargetName"></strong> (ID: <span id="deleteTargetId"></span>)?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                <a id="deleteConfirmBtn" class="btn btn-danger" href="#">Delete</a>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS + Icons -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">

<script>
    function confirmDelete(btn) {
        const id = btn.getAttribute('data-id');
        const name = btn.getAttribute('data-name');
        document.getElementById('deleteTargetName').textContent = name || '';
        document.getElementById('deleteTargetId').textContent = id;
        const confirmBtn = document.getElementById('deleteConfirmBtn');
        confirmBtn.href = `${pageContext.request.contextPath}/admin/tea-estimate/delete/${id}`;
        var deleteModal = new bootstrap.Modal(document.getElementById('deleteModal'));
        deleteModal.show();
    }
</script>
</body>
</html>