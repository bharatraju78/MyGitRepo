<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Edit Employee</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<div class="container">
    <h2>Edit Employee</h2>
    <ul class="nav nav-tabs" role="tablist">
        <li class="active"><a href="#editEmployee" role="tab" data-toggle="tab">Employee Details</a></li>
        <li><a href="#editDesignation" role="tab" data-toggle="tab">Designation</a></li>
        <li><a href="#editGrade" role="tab" data-toggle="tab">Grade</a></li>
        <li><a href="#editSalary" role="tab" data-toggle="tab">Salary</a></li>
    </ul>
    <div class="tab-content" style="margin-top:20px;">
        <div class="tab-pane fade in active" id="editEmployee">
            <form:form method="post" modelAttribute="employee" class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-2 control-label">VAM ID</label>
                    <div class="col-sm-4">
                        <form:input path="vamId" cssClass="form-control" readonly="true" />
                    </div>
                    <label class="col-sm-2 control-label">Name</label>
                    <div class="col-sm-4">
                        <form:input path="name" cssClass="form-control" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Email Id</label>
                    <div class="col-sm-4">
                        <form:input path="emailId" cssClass="form-control" />
                    </div>
                    <label class="col-sm-2 control-label">Date Of Joining</label>
                    <div class="col-sm-4">
                        <form:input path="doj" type="date" cssClass="form-control" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Status</label>
                    <div class="col-sm-4">
                        <form:input path="status" cssClass="form-control" />
                    </div>
                    <label class="col-sm-2 control-label">Skill Data From LD</label>
                    <div class="col-sm-4">
                        <form:input path="skillDataFromLD" cssClass="form-control" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Current Skill</label>
                    <div class="col-sm-4">
                        <form:input path="currentSkill" cssClass="form-control" />
                    </div>
                    <label class="col-sm-2 control-label">Status With Days</label>
                    <div class="col-sm-4">
                        <form:input path="statusWithDays" cssClass="form-control" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">VAM Exp</label>
                    <div class="col-sm-4">
                        <form:input path="vamExp" cssClass="form-control" />
                    </div>
                     <label class="col-sm-2 control-label">Total Exp</label>
                    <div class="col-sm-4">
                        <form:input path="totalExp" cssClass="form-control" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Final Status</label>
                    <div class="col-sm-4">
                        <form:input path="finalStatus" cssClass="form-control" />
                    </div>
                    <label class="col-sm-2 control-label">Resignation Date</label>
                    <div class="col-sm-4">
                        <form:input path="resignationDate" type="date" cssClass="form-control" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Attrition Risk</label>
                    <div class="col-sm-4">
                        <form:input path="attritionRisk" cssClass="form-control" />
                    </div>
                     <label class="col-sm-2 control-label">CTC</label>
                    <div class="col-sm-4">
                        <form:input path="ctc" cssClass="form-control" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">TODO</label>
                    <div class="col-sm-4">
                        <form:input path="todo" cssClass="form-control" />
                    </div>
                     <label class="col-sm-2 control-label">Year One Hike</label>
                    <div class="col-sm-4">
                        <form:input path="yearOneHike" cssClass="form-control" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Created By</label>
                    <div class="col-sm-4">
                        <form:input path="createdBy" cssClass="form-control" readonly="true" />
                    </div>
                    <label class="col-sm-2 control-label">Created Date</label>
                    <div class="col-sm-4">
                        <form:input path="createdDate" type="date" cssClass="form-control" readonly="true" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Modified By</label>
                    <div class="col-sm-4">
                        <form:input path="modifiedBy" cssClass="form-control" readonly="true" />
                    </div>
                     <label class="col-sm-2 control-label">Modified Date</label>
                    <div class="col-sm-4">
                        <form:input path="modifiedDate" type="date" cssClass="form-control" readonly="true" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Password</label>
                    <div class="col-sm-4">
                        <form:password path="password" cssClass="form-control" />
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-primary">Save</button>
                        <a href="searchEmployee" class="btn btn-default">Cancel</a>
                    </div>
                </div>
            </form:form>
        </div>
        <div class="tab-pane fade" id="editDesignation">
            <form class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-2 control-label">Designation</label>
                    <div class="col-sm-4">
                        <form:select path="designation" cssClass="form-control" >
                            <form:option value="">Select Designation</form:option>
                            <form:options items="${designations}" itemValue="id" itemLabel="name" />
                        </form:select> 
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-primary">Save Designation</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="tab-pane fade" id="editGrade">
            <form class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-2 control-label">Grade</label>
                    <div class="col-sm-4">
                        <form:select path="grade" cssClass="form-control" >
                            <form:option value="">Select Grade</form:option>
                            <form:options items="${grades}" itemValue="id" itemLabel="name" />
                        </form:select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-primary">Save Grade</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="tab-pane fade" id="editSalary">
            <form class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-2 control-label">Salary</label>
                    <div class="col-sm-10">
                        <input type="number" class="form-control" name="salary" placeholder="Enter new salary">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-primary">Save Salary</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</body>
</html>