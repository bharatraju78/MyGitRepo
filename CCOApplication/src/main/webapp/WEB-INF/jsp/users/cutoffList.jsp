<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Cut Off Parameters</title>
    <link href="<c:url value='/resources/css/bootstrap.min.css' />" rel="stylesheet">
    <link href="<c:url value='/resources/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/css/style.css' />" rel="stylesheet">
</head>
<body>
<jsp:include page="../header.jsp" />
<div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h3>Cut Off Parameters</h3>
        <a class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/admin/cutoff/add">Add</a>
    </div>
    <c:if test="${not empty successMessage}"><div class="alert alert-success">${successMessage}</div></c:if>
    <c:if test="${not empty errorMessage}"><div class="alert alert-danger">${errorMessage}</div></c:if>
    <table class="table table-striped table-bordered">
        <thead>
            <tr>
                <th>Name</th>
                <th>PM %</th>
                <th>BA %</th>
                <th>Dev %</th>
                <th>QA %</th>
                <th>AnA %</th>
                <th>GenAI %</th>
                <th>Total %</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="it" items="${items}">
                <tr>
                    <td>${it.name}</td>
                    <td>${it.projectManagerPercentage}</td>
                    <td>${it.businessAnalystPercentage}</td>
                    <td>${it.developmentPercentage}</td>
                    <td>${it.qualityAssurancePercentage}</td>
                    <td>${it.assetsAndAcceleratorPercentage}</td>
                    <td>${it.genAIPercentage}</td>
                    <td>${it.totalPercentage}</td>
                    <td>
                        <a class="btn btn-sm btn-secondary" href="${pageContext.request.contextPath}/admin/cutoff/edit/${it.id}">Edit</a>
                        <a class="btn btn-sm btn-danger" href="${pageContext.request.contextPath}/admin/cutoff/delete/${it.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<jsp:include page="../footer.jsp" />
</body>
</html>
