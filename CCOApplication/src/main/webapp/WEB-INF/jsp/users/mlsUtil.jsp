<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="row text-center align-middle" style="margin-top: 20px;">
	                    <div class="col-sm-2">
	                        <select class="form-control mt-2" id="leaderShipList[0].empId" name="leaderShipList[0].empId" required>
	                            <option value="">Select Leadership</option>
	                            <c:forEach var="leader"  items="${leadershipList}">
	                                <option value="${leader.empId}">${leader.empName}</option>
	                            </c:forEach>
	                        </select>
	                    </div>
	                    <div class="col-sm-2">
	                        <select class="form-control mt-2" id="leaderShipList[0].roleId" name="leaderShipList[0].roleId" required>
	                            <option value="">Select Role</option>
	                            <c:forEach var="role" items="${leaderShipRoles}">
	                                <option value="${role.projectRoleId}">${role.roleName}</option>
	                            </c:forEach>
	                        </select>
	                    </div>
	                    <div class="col-sm-2">
	                        <select class="form-control mt-2" id="directorList[0].empId" name="directorList[0].empId" required>
	                            <option value="">Select Director</option>
	                            <c:forEach var="director" items="${directorList}">
	                                <option value="${director.empId}">${director.empName}</option>
	                            </c:forEach>
	                        </select>
	                    </div>
	                    <div class="col-sm-2">
	                        <select class="form-control mt-2" id="directorList[0].roleId" name="directorList[0].roleId" required>
	                            <option value="">Select Role</option>
	                            <c:forEach var="role" items="${directorRoles}">
	                                <option value="${role.projectRoleId}">${role.roleName}</option>
	                            </c:forEach>
	                        </select>
	                    </div>
	                    <div class="col-sm-2">
	                        <select class="form-control mt-2" id="managerList[0].empId" name="managerList[0].empId" required>
	                            <option value="">Select Manager</option>
	                            <c:forEach var="manager" items="${managerList}">
	                                <option value="${manager.empId}">${manager.empName}</option>
	                            </c:forEach>
	                        </select>
	                    </div>
	                    <div class="col-sm-1">
	                        <select class="form-control mt-2" id="managerList[0].roleId" name="managerList[0].roleId" required>
	                            <option value="">Select Role</option>
	                            <c:forEach var="role" items="${managerRoles}">
	                                <option value="${role.projectRoleId}">${role.roleName}</option>
	                            </c:forEach>
	                        </select>
	                    </div>
	                    <div class="col-sm-1">
	                        <button class="btn btn-sm btn-danger" onclick="removeLeadership()" title="Remove">
	                            <i class="fa fa-trash"></i>
	                        </button>
	                    </div>
	                </div>