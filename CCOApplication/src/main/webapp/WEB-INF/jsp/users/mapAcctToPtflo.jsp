<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Map Accounts to Portfolio</title>
    <link href="<c:url value='/resources/css/bootstrap.min.css' />" rel="stylesheet">
    <link href="<c:url value='/resources/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/css/style.css' />" rel="stylesheet">
    <script src="<c:url value='/resources/script/jquery-3.1.0.min.js' />" type="text/javascript"></script>
    <script src="<c:url value='/resources/script/bootstrap.min.js' />" type="text/javascript"></script>
</head>
<body>
<jsp:include page="../header.jsp" />
<div class="container mt-4">
    <h2>Portfolios</h2>
    <div id="alert alert-danger" role="alert">
				<c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger">
                        ${errorMessage}
                    </div>
                 </c:if>
                 <c:if test="${not empty successMessage}">
                    <div class="alert alert-success">
                        ${successMessage}
                    </div>
                 </c:if>      
                 <input type="hidden" id="activeTab" name="activeTab" value="${activeTab}" />
			</div>
    <div id="portfolioSection">
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Portfolio Number</th>
                    <th>Portfolio Name</th>
                    <th>Accounts</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="portfolio" items="${portfolios}">
                <tr>
                    <td>${portfolio.portfolioNmber}</td>
                    <td>${portfolio.name}</td>
                    <td>
                        <button class="btn btn-secondary btn-sm" onclick="showAccountModal(${portfolio.id})">Show Account</button>
                        
                        <!-- Add Account Modal -->
					    <div class="modal fade" id="showAccountModal_${portfolio.id}" tabindex="-1" role="dialog" aria-labelledby="showAccountModalLabel">
					      <div class="modal-dialog" role="document">
					        <div class="modal-content">
					          <div class="modal-header">
					            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					            <h4 class="modal-title" id="showAccountModalLabel">Accounts of Portfolio</h4>
					          </div>
					          <div class="modal-body">
					            <%-- <form id="addAccountForm" method="post" action="${pageContext.request.contextPath}/admin/portfolio/addAccount"> --%>
					            <c:if test="${not empty portfolio.accountsModel}">
			                        <table class="table table-striped">
                                        <thead>
                                            <tr>
                                                <th>Account Number</th>
                                                <th>Account Name</th>
                                                <th>Start Date</th>
                                                <th>End Date</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="account" items="${portfolio.accountsModel}">
                                                <tr>
                                                    <td>${account.accNo}</td>
                                                    <td>${account.accName}</td>
                                                    <td><fmt:formatDate value="${account.accStartDate}" pattern="dd-MM-yyyy"/></td>
                                                    <td><fmt:formatDate value="${account.accEndDate}" pattern="dd-MM-yyyy"/></td>
                                                    <td><a href="javascript:editAccount('${account.accountId}', '${account.accName}', '${account.accStartDate}', '${account.accEndDate}', 'showAccountModal_${portfolio.id}', '${portfolio.id}');">Edit</a></td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>    
			                     </c:if>
			                     <c:if test="${empty portfolio.accountsModel}">
					                <ul>
			                            <li>No accounts mapped to this portfolio.</li>
			                        </ul>
			                     </c:if>
					            <!-- </form> -->
					          </div>
					        </div>
					      </div>
					    </div>
                    </td>
                    <td>
                        <button class="btn btn-primary btn-sm" onclick="showAddAccountModal(${portfolio.id})">Add Account</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Add Account Modal -->
    <div class="modal fade" id="addAccountModal" tabindex="-1" role="dialog" aria-labelledby="addAccountModalLabel">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="addAccountModalLabel">Add Account to Portfolio</h4>
          </div>
          <div class="modal-body">
            <form id="portfolio" name="portfolio" method="post" action="${pageContext.request.contextPath}/admin/portfolio/addAccount">
                <input type="hidden" id="portfolioId" name="portfolioId" value="" />
                <div class="form-group">
                    <label for="accountId">Select Account</label>
                    <select class="form-control" id="accountId" name="accountId" required>
                    	<option value="">-- Select Account --</option>
                        <c:forEach var="account" items="${accounts}">
                            <option value="${account.accountId}">${account.accNo} - ${account.accName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="startDate">Start Date</label>
                    <input type="date" class="form-control" id="startDate" name="startDate" required>
                </div>
                <div class="form-group">
                    <label for="endDate">End Date</label>
                    <input type="date" class="form-control" id="endDate" name="endDate" required>
                </div>
                <button type="submit" class="btn btn-success">Add</button>
            </form>
          </div>
        </div>
      </div>
    </div>
    <!-- Add Account Modal -->
    <div class="modal fade" id="editAccountModal" tabindex="-1" role="dialog" aria-labelledby="editAccountModalLabel">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="editAccountModalLabel">Edit Account to Portfolio</h4>
          </div>
          <div class="modal-body">
            <form id="portfolio" name="portfolio" method="post" action="${pageContext.request.contextPath}/admin/portfolio/editAccount">
                <input type="hidden" id="portfolioId" name="portfolioId" value="" />
                <div class="form-group">
                    <label for="accountId">Select Account</label>
                    <select class="form-control" id="accountId" name="accountId" required>
                    	<option value="">-- Select Account --</option>
                        <c:forEach var="account" items="${accounts}">
                            <option value="${account.accountId}">${account.accNo} - ${account.accName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="startDate">Start Date</label>
                    <input type="date" class="form-control" id="startDate" name="startDate" required>
                </div>
                <div class="form-group">
                    <label for="endDate">End Date</label>
                    <input type="date" class="form-control" id="endDate" name="endDate" required>
                </div>
                <button type="submit" class="btn btn-success">Edit</button>
            </form>
          </div>
        </div>
      </div>
    </div>
    <!-- Pagination Controls -->
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <c:if test="${currentPage > 1}">
                <li class="page-item">
                    <a class="page-link" href="?page=${currentPage - 1}&size=${size}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            </c:if>
            <c:forEach var="i" begin="1" end="${totalPages}">
                <li class="page-item ${i == currentPage ? 'active' : ''}">
                    <a class="page-link" href="?page=${i}&size=${size}">${i}</a>
                </li>
            </c:forEach>
            <c:if test="${currentPage < totalPages}">
                <li class="page-item">
                    <a class="page-link" href="?page=${currentPage + 1}&size=${size}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </c:if>
        </ul>
    </nav>
    <!-- End Pagination Controls -->
</div>
<jsp:include page="../footer.jsp" />
<script>
function showAddAccountModal(portfolioId) {
    $('#portfolioId').val(portfolioId);
    $('#addAccountModal').modal('show');
}

function showAccountModal(portfolioId) {
    $('#showAccountModal_'+portfolioId).modal('show');
}
function editAccount(accountId, accName, startDate, endDate, modelId, portfolioId) {
	$('#'+modelId).modal('hide');
    $('#editAccountModal').modal('show');
    $('#editAccountModal #portfolioId').val(portfolioId);
    $('#editAccountModal #accountId').val(accountId);
    $('#editAccountModal #startDate').prop('valueAsDate', new Date(startDate));
    $('#editAccountModal #endDate').prop('valueAsDate', new Date(endDate));
}
</script>
</body>
</html>