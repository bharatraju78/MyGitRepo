$( document ).ready(function() {
	var contextPath = $('#contextPath').val();
	
	jQuery.validator.addMethod("greaterThan", 
	function(value, element, params) {
	    if (!/Invalid|NaN/.test(new Date(value))) {
	        return new Date(value) > new Date($(params).val());
	    }

	    return isNaN(value) && isNaN($(params).val()) 
	        || (Number(value) > Number($(params).val())); 
	},'Must be greater than {0}.');
	
	$('#accountNameSer').typeahead({
		source: function(query, process) {
			accountNames = [];
			accountMaps = {};
			console.log("=-----query--->" + query);
			$.ajax({
				type: "GET",
				url: $('#contextPath').val() + "/dashboard/fetchAccounts/" + query,
				success: function(data) {
					$('#container').html(data);
				},
				dataType: "HTML",
				error: showError
			});
		},
		minLength: 3,
		updater: function(item, i) {
			return item;
		}
	});
});

function getUploadData(){
	$.ajax({
		type: "GET",
		url: $('#contextPath').val()+"/admin/upload/showUploadDataView",
		success: renderData,
		dataType: "HTML",
		error :showError
	});
}

function getRolesData(){
	$.ajax({
		  type: "GET",
		  url: $('#contextPath').val()+"/admin/roles/searchRoles",
		  success: renderRoles,
		  dataType: "HTML",
		  error :showError
	});
}

function getUsersData(){
	$.ajax({
		  type: "GET",
		  url: $('#contextPath').val()+"/admin/users/searchUsers",
		  success: renderUsers,
		  dataType: "HTML",
		  error :showError
	});
}

function renderRoles(data){
	if(null != data){
		$('#container').html(data);
	}
}
function renderUsers(data){
	if(null != data){
		$('#container').html(data);
	}
}

function serializeObject(formData){
	var values = $(formData).serializeArray();
	var attributes = {};

	$.each(values, 	function(index, field){
	  attributes[field.name] = field.value;
	});	
	return attributes;
}


function getAccountsData(){
	$.ajax({
          type: "GET",
          url: $('#contextPath').val()+"/admin/accounts/searchAccounts",
          success: renderAccounts,
          dataType: "HTML",
		  error :showError
    });
}

function renderAccounts(data){
	if(null != data){
        $('#container').html(data);
    }
}

function getProjectsData(){
    $.ajax({
          type: "GET",
          url: $('#contextPath').val()+"/admin/projects/searchProjects",
          success: renderProjects,
          dataType: "HTML",
		  error :showError
    });
}

function renderProjects(data){
	    if(null != data){
        $('#container').html(data);
    }
}	

function renderData(data){
    if(null != data){
        $('#container').html(data);
    }
}

function getEmployeesData(){
    $.ajax({
          type: "GET",
          url: $('#contextPath').val()+"/admin/employees/searchEmployees",
          success: renderData,
          dataType: "HTML",
		  error :showError
    });
}

function getProRoleAllocationData(){
	$.ajax({
		type: "GET",
		url: $('#contextPath').val() + "/admin/projectRoles/searchProjectRoles",
		success: renderData,
		dataType: "HTML",
		error :showError
	});
}

function getDesignationAllocationData(){
	$.ajax({
		type: "GET",
		url: $('#contextPath').val() + "/admin/designation/searchDesignations",
		success: renderData,
		dataType: "HTML",
		error :showError
	});
}

function getSkillAllocationData(){
	$.ajax({
		type: "GET",
		url: $('#contextPath').val() + "/admin/skills/searchSkills",
		success: renderData,
		dataType: "HTML",
		error :showError
	});
}

function getVerticalAllocationData(){
	$.ajax({
		type: "GET",
		url: $('#contextPath').val() + "/admin/verticals/searchVerticals",
		success: renderData,
		dataType: "HTML",
		error :showError
	});
}

function getEmployeeAllocationData(){
	$.ajax({
		type: "GET",
		url: $('#contextPath').val() + "/admin/employees/searchEmployees",
		success: renderData,
		dataType: "HTML",
		error :showError
	});
}

function showError(jqXHR, textStatus, errorThrown){
	var errorText = "Error:", textStatus, errorThrown;
	if (jqXHR.status === 404) {
		errorText = errorText +" Resource not found.";
	} else if (jqXHR.status === 500) {
		errorText = errorText +"Server error.";
	} else {
		errorText = errorText +"An unexpected error occurred.";
	}
	$('#msgErr').val(errorText);
	$('#msgErr').show();
}

function clearUploadFields(){
	$('#excelFile').val(null);
	$('#file-name').html('');
	$('#uploadStatus').removeAttr('class').hide();
}

function getUserGradesData(){
	$.ajax({
		type: "GET",
		url: $('#contextPath').val() + "/admin/grades/searchGrades",
		success: renderData,
		dataType: "HTML",
		error: showError
	});
}

function showEmpAllPage(){
    $.ajax({
          type: "GET",
          url: $('#contextPath').val()+"/admin/projects-allocation/showEmpAllPage",
          success: renderData,
          dataType: "HTML",
		  error :showError
    });
}

function exportAccount(accountId) {
	
	var projects = [];
	$('#account_'+accountId).find('input[type="checkbox"]:checked').each(function() {
		projects.push($(this).val());
	});
	if(projects.length == 0) {
		window.open($('#contextPath').val() + '/dashboard/exportAccountByAccount/' + accountId);
	} else {
		window.open($('#contextPath').val() + '/dashboard/exportAccount/' + accountId + '/' + projects.join(','));
	}
}

function getLabelsData(){
	$.ajax({
		type: "GET",
		url: $('#contextPath').val() + "/admin/labels/searchLabels",
		success: renderData,
		dataType: "HTML",
		error: showError
	});
}

function getRolesAndLabelsData(){
	$.ajax({
		type: "GET",
		url: $('#contextPath').val() + "/admin/mapRolesLabels/searchMapRolesLabels",
		success: renderData,
		dataType: "HTML",
		error: showError
	});
}