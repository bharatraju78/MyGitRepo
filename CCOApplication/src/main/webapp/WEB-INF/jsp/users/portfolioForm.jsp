<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Add/Edit Portfolio</title>
    <link href="<c:url value='/resources/css/bootstrap.min.css' />" rel="stylesheet">
    <link href="<c:url value='/resources/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/css/style.css' />" rel="stylesheet">
    <script src="<c:url value='/resources/script/jquery-3.1.0.min.js' />" type="text/javascript"></script>
    <script src="<c:url value='/resources/script/jquery.validate.min.js' />" type="text/javascript"></script>
    <script src="<c:url value='/resources/script/bootstrap.min.js' />" type="text/javascript"></script>
    <script src="<c:url value='/resources/script/bootstrap3-typeahead.min.js'  />" type="text/javascript"></script>
</head>
<body>
<jsp:include page="../header.jsp" />
<div class="container mt-4">
    <h2>${portfolio.id == null ? 'Add' : 'Edit'} Portfolio</h2>
    <form action="${pageContext.request.contextPath}/admin/portfolio/save" method="post">
        <input type="hidden" name="id" value="${portfolio.id}" />
        <input type="hidden" name="createdBy" value="${portfolio.createdBy}" />
        <input type="hidden" name="updatedBy" value="${portfolio.updatedBy}" />
        <%-- <input type="hidden" name="createdAt" value="${portfolio.createdAt}" />
        <input type="hidden" name="updatedAt" value="${portfolio.updatedAt}" /> --%>
        <div class="row">
            <div class="form-group">
                <label class="col-sm-2" for="portfolioNmber">Portfolio Number</label>
                <div class="col-lg-4">
                    <input type="text" class="form-control" id="portfolioNmber" name="portfolioNmber" value="${portfolio.portfolioNmber}" required readonly="readonly" />
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <label class="col-sm-2" for="name">Portfolio Name</label>
                <div class="col-lg-4">
                    <input type="text" class="form-control" id="name" name="name" value="${portfolio.name}" required />
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <label class="col-sm-2" for="portfolioLeader">Portfolio Leader</label>
                <div class="col-lg-4">
                    <select class="form-control" id="portfolioLeader" name="portfolioLeader" required>
                        <option value="">Select Leader</option>
                        <c:forEach items="${leaderShipList}" var="leader">
                            <option value="${leader.employeeId}" ${portfolio.portfolioLeader == leader.employeeId ? 'selected' : ''}>${leader.name}</option>
                        </c:forEach>
                    </select>    
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <label class="col-sm-2" for="portfolioHR">Portfolio HR</label>
                <div class="col-lg-4">
                    <select class="form-control" id="portfolioHR" name="portfolioHR" required>
                        <option value="">Select Leader</option>
                        <c:forEach items="${hrEmployees}" var="hr">
                            <option value="${hr.employeeId}" ${portfolio.portfolioHR == hr.employeeId ? 'selected' : ''}>${hr.name}</option>
                        </c:forEach>
                    </select>    
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <label class="col-sm-2" for="portfolioHR1">Portfolio HR 1</label>
                <div class="col-lg-4">
                    <select class="form-control" id="portfolioHR1" name="portfolioHR1">
                        <option value="">Select Leader</option>
                        <c:forEach items="${hrEmployees}" var="hr">
                            <option value="${hr.employeeId}" ${portfolio.portfolioHR1 == hr.employeeId ? 'selected' : ''}>${hr.name}</option>
                        </c:forEach>
                    </select>    
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <label class="col-sm-2" for="description">Description</label>
                <div class="col-lg-4">
                    <textarea class="form-control" id="description" name="description">${portfolio.description}</textarea>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <label class="col-sm-2" for="active">Active</label>
                <div class="col-lg-4">
                    <input type="checkbox" id="active" name="active" ${portfolio.active ? 'checked' : ''} />
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <label class="col-sm-2"  for="createdBy">Created By</label>
                <div class="col-lg-4">
                <input type="text" class="form-control" value="${portfolio.createdBy}"  readonly="readonly" />
            	</div>
            </div>
            </div>
            <div class="row">
            <div class="form-group">
                <label class="col-sm-2"  for="createdDate">Created Date</label>
                <div class="col-lg-4">
                 <input type="date" class="form-control" value="<fmt:formatDate value='${portfolio.createdAt}' pattern="yyyy-MM-dd"/>" readonly="readonly" />
             </div>
            </div>
            </div>
            <div class="row">
            <div class="form-group">
                <label class="col-sm-2"  for="modifiedBy">Modified By</label>
                <div class="col-lg-4">
                <input type="text" class="form-control" value="${portfolio.updatedBy}" readonly="readonly"/>
           		</div>
            </div>
            </div>
            <div class="row">
            <div class="form-group">
                <label class="col-sm-2"  for="modifiedDate">Modified Date</label>
                <div class="col-lg-4">
                 <input type="date" class="form-control" value="<fmt:formatDate value='${portfolio.updatedAt}' pattern="yyyy-MM-dd"/>" readonly="readonly"/>
            	</div>
            </div>
            </div>
        <button type="submit" class="btn btn-success">Save</button>
        <a href="${pageContext.request.contextPath}/admin/portfolio/list" class="btn btn-secondary">Cancel</a>
    </form>
</div>
</body>
</html>