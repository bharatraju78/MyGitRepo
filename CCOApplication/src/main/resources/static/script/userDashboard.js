$(function() {
	
});

function fetchProjects(accountId) {
    $.ajax({
        type: "GET",
        url: $('#contextPath').val() + "/dashboard/fetchProjects/" + accountId,
        beforeSend: function(xhr) {
            xhr.setRequestHeader('X-CSRF-TOKEN', $('[name="_csrf"]').val());
        },
        success: function(data) {
			if('' != data && data.length > 10) {
				$('#account_'+accountId).html(data);
			} else {
				$('#account_'+accountId).html('<div class="alert alert-info">No projects found for this account.</div>');
			}
          
        },
        dataType: "HTML"
    });
}

function fetchEmployeesAllocations(projectId){
	
	$.ajax({
	       type: "GET",
	       url: $('#contextPath').val() + "/dashboard/fetchEmployees/" + projectId,
	       beforeSend: function(xhr) {
	           xhr.setRequestHeader('X-CSRF-TOKEN', $('[name="_csrf"]').val());
	       },
	       success: function(data) {
			console.log(data);
			if('' != data && data.length > 10) {
				$('#emp_'+projectId).html(data);
			} else {
				$('#emp_'+projectId).html('<div class="alert alert-info">No employee allocation found for this project.</div>');
			}
	         
	       },
	       dataType: "HTML"
	   });
   }