$(function() {
	$("#proRoleForm").validate({
		rules: {
			roleName: {
				required: true,
				minlength: 5
			},
			createdBy: {
				required: true
            },
	        createdDate: {
				required: true
			},
			modifiedBy: {	
				required: true
            },
			modifiedDate: {
				required: true
            }
		},
		messages: {
			accNo: {
				required: "Please enter a Role Name",
				minlength: "Role Name must be at least 5 characters long"
			},
			createdBy: {
				required: "Please enter created by"
			},
			createdDate: {
				required: "Please enter created date"
			},
			modifiedBy: {
				required: "Please enter modified by"
			},
			modifiedDate: {
				required: "Please enter modified date"
			}
		},
		errorElement: "em",
		errorPlacement: function(error, element) {
			// Add the `invalid-feedback` class to the error element
			error.addClass("invalid-feedback");

			if (element.prop("type") === "checkbox") {
				error.insertAfter(element.next("label"));
			} else {
				error.insertAfter(element);
			}
		},
		highlight: function(element, errorClass, validClass) {
			$(element).addClass("is-invalid").removeClass("is-valid");
		},
		unhighlight: function(element, errorClass, validClass) {
			$(element).addClass("is-valid").removeClass("is-invalid");
		}
	});
	
	$('#excelFile').on('change', function() {
			var fileName = $(this).val().split('\\').pop();
			$('#file-name').text(fileName);
		});

		$('#uploadButton').click(function() {
			var file = $('#excelFile')[0].files[0];
			if (file) {
				var formData = new FormData();
				formData.append('file', file);

				$.ajax({
					url: $('#contextPath').val()+'/admin/projectRoles/uploadFile', 
					type: 'POST',
					beforeSend: function(xhr) {
						xhr.setRequestHeader('X-CSRF-TOKEN', $('[name="_csrf"]').val());
					},
					data: formData,
					contentType: false,
					processData: false,
					success: function(response) {
						$('#uploadStatus').show();
						$('#uploadStatus').width('40%');
						$('#uploadStatus').html(response);
					},
					error: function(xhr, status, error) {
						$('#uploadStatus').html('<div class="alert alert-danger">An error occurred: ' + error + '</div>');
					}
				});
			} else {
				$('#uploadStatus').html('<div class="alert alert-warning">Please choose a file to upload.</div>');
			}
		});
});

function searchProRole(){
	if ($('#proRoleName').val().length >= 3) {
		$.ajax({
			type: "GET",
			url: $('#contextPath').val() + "/admin/projectRoles/searchProjectRoleByRoleName/" + $('#proRoleName').val(),
			success: renderData,
			dataType: "HTML"
		});
	}
}

function showAddProRole(){
	$("#proRoleForm").trigger('reset');
	$('#addProRoleModel').show();
	$.ajax({
		type: "GET",
		url: $('#contextPath').val() + "/admin/projectRoles/setAuditFields",
		beforeSend: function(xhr) {
			xhr.setRequestHeader('X-CSRF-TOKEN', $('[name="_csrf"]').val());
		},
		success: function(response) {
			$('#projectRoleId').val(response.projectRoleId);
			$('#createdBy').val(response.createdBy);
			$('#createdDate').prop('valueAsDate', new Date(response.createdDate));
			$('#modifiedBy').val(response.modifiedBy);
			$('#modifiedDate').prop('valueAsDate', new Date(response.modifiedDate));
		},
		dataType: "JSON"
	});
	
}

function addProRole(){
	var reqData = JSON.stringify(serializeObject($('#proRoleForm')));
	if($( "#proRoleForm" ).valid()){
		$.ajax({
			  type: "post",
			  url: $('#contextPath').val()+"/admin/projectRoles/saveProjectRole",
			  beforeSend: function(xhr) {
			        xhr.setRequestHeader('X-CSRF-TOKEN', $('[name="_csrf"]').val());
			    },
			  contentType: "application/json; charset=utf-8",
			  dataType: "HTML",
			  data: reqData,
			  success: closeProRolePopup
		});
	}
}

function closeProRolePopup(data){
	$('#addProRoleModel').modal('hide')
	renderData(data);
	$('#containerMain').removeClass('modal-open');
	$('.modal-backdrop').hide();
}

function deleteProRole(val){
	$.ajax({
		  type: "GET",
		  url: $('#contextPath').val()+"/admin/projectRoles/deleteProjectRole/"+val,
		  beforeSend: function(xhr) {
		        xhr.setRequestHeader('X-CSRF-TOKEN', $('[name="_csrf"]').val());
		    },
		  success: renderData,
		  dataType: "HTML"
	});
}

function showUpdateProRole(id){
	$.ajax({
		  type: "GET",
		  url: $('#contextPath').val()+"/admin/projectRoles/showEditProjectRole/"+id,
		  beforeSend: function(xhr) {
		        xhr.setRequestHeader('X-CSRF-TOKEN', $('[name="_csrf"]').val());
		    },
		  success: renderUpdateAccount,
		  dataType: "JSON"
	});
}

function renderUpdateAccount(data){
	
	$('#projectRoleId').val(data.projectRoleId);
	$('#roleName').val(data.roleName);	
   
	$('#createdBy').val(data.createdBy);
	$('#createdDate').prop('valueAsDate', new Date(data.createdDate));
	$('#modifiedBy').val(data.modifiedBy);
	$('#modifiedDate').prop('valueAsDate', new Date(data.modifiedDate));
	
	$('#addProRoleModel').modal('toggle');
}
