<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container mt-4">
        <h2>History</h2>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Grade Name</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Comments</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${empty empGrades}">
                <tr>
                    <td colspan="5" class="text-center">No Designation History Found</td>
                </tr>
            </c:if>
            <c:if test="${not empty empGrades}">    
            <c:forEach var="grade" items="${empGrades}">
                <tr>
                    <td>${grade.gradeId}</td>
                    <td>${grade.gradeName}</td>
                    <td>
                    	<fmt:formatDate value="${grade.startDate}" pattern="dd-MM-yyyy" var="startDate" />
                    	${startDate}
                    </td>
                    <td>
                    	<fmt:formatDate value="${grade.endDate}" pattern="dd-MM-yyyy" var="endDate" />
                    	${endDate}
                    </td>
                    <td>${grade.comments}</td>
                </tr>
            </c:forEach>
            </c:if>
            </tbody>
        </table>
        <!-- Pagination Controls -->
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:if test="${page > 1}">
                    <li class="page-item">
                        <a class="page-link" href="?page=${page - 1}&size=${size}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>
                <c:forEach var="i" begin="1" end="${totalPages}">
                    <li class="page-item ${i == page ? 'active' : ''}">
                        <a class="page-link" href="?page=${i}&size=${size}">${i}</a>
                    </li>
                </c:forEach>
                <c:if test="${page < totalPages}">
                    <li class="page-item">
                        <a class="page-link" href="?page=${page + 1}&size=${size}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </div>
    <div>
    	<a	href="${pageContext.request.contextPath}/admin/employees/edit/${employeeId}"
					class="btn btn-secondary">Back</a>
    </div>