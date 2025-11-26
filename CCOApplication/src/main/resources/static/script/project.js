
$(function() {
	$("#projectForm").validate({
		rules: {
			name: {
				required: true,
				minlength: 2
			},
			description: {
				required: true,
				minlength: 2
			},
			code: {
				required: true,
				minlength: 2
			},
			status: {
				required: true
			},
			startDate: {
				required: true
			},
			endDate: {
				required: true,
				greaterThan: '#startDate'
			}
		},
		messages: {
			name: {
				required: "Please enter a project name",
				minlength: "Project name must be at least 9 characters long"
			},
			description: {
				required: "Please enter a description",
				minlength: "Description must be at least 15 characters long"
			},
			code: {
				required: "Please enter a code",
				minlength: "Code must be at least 5 characters long"
			},
			status: {
				required: "Please enter a status"
			},
			startDate: {
				required: "Please enter a start date"
			},
			endDate: {
				required: "Please enter a end date"
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
				url: $('#contextPath').val() + '/admin/projects/uploadFile',
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

function searchProject(){
	if($('#projectName').val().lenght >= 3){
		$.ajax({
			type: "GET",
			url: $('#contextPath').val() + "/admin/projects/searchProjectsByName/" + $('#projectName').val(),
			//data: $('#sales').serialize(),
			success: renderData,
			dataType: "HTML"
		});
	}
}

function showAddProject(){
	$("#projectForm").trigger('reset');
	$('#addProjectModel').show();
	$.ajax({
			type: "GET",
			url: $('#contextPath').val() + "/admin/projects/generateAccountNumber",
			beforeSend: function(xhr) {
				xhr.setRequestHeader('X-CSRF-TOKEN', $('[name="_csrf"]').val());
			},
			success: function(response) {
				$('#code').val(response.code);
				$('#createdBy').val(response.createdBy);
				$('#createdDate').prop('valueAsDate', new Date(response.createdDate));
				$('#modifiedBy').val(response.modifiedBy);
				$('#modifiedDate').prop('valueAsDate', new Date(response.modifiedDate));
				let status = $('#status');
				status.empty();
				status.append(new Option('Select', ''));
				response.statusList.forEach(function(value) {
					status.append(new Option(value, value));
				});
			},
			dataType: "JSON"
		});
}

function addProject(){
	var reqData = JSON.stringify(serializeObject($('#projectForm')));
	if($("#projectForm").valid()){
		$.ajax({
			  type: "post",
			  url: $('#contextPath').val()+"/admin/projects/addProject",
			  beforeSend: function(xhr) {
			        xhr.setRequestHeader('X-CSRF-TOKEN', $('[name="_csrf"]').val());
			    },
			  contentType: "application/json; charset=utf-8",
			  dataType: "HTML",
			  data: reqData,
			  success: closeProjectPopup
		});
	}
}

function closeProjectPopup(data){
	$('.modal-backdrop').remove();
	renderUsers(data);
	$('#containerMain').removeClass('modal-open');
	$('.modal-backdrop').hide();
}

function deleteProject(val){
	$.ajax({
		  type: "GET",
		  url: $('#contextPath').val()+"/admin/projects/deleteProject/"+val,
		  beforeSend: function(xhr) {
		        xhr.setRequestHeader('X-CSRF-TOKEN', $('[name="_csrf"]').val());
		    },
		  //data: $('#sales').serialize(),
		  success: renderUsers,
		  dataType: "HTML"
	});
}

function showUpdateProject(id){
	$.ajax({
		  type: "GET",
		  url: $('#contextPath').val()+"/admin/projects/editProject/"+id,
		  beforeSend: function(xhr) {
		        xhr.setRequestHeader('X-CSRF-TOKEN', $('[name="_csrf"]').val());
		    },
		  //data: $('#sales').serialize(),
		  success: renderUpdateProject,
		  dataType: "JSON"
	});
}

function renderUpdateProject(data){
	$('#id').val(data.id);
	$('#name').val(data.name);
	$('#description').val(data.description);
	$('#code').val(data.code);
	$('#accountId').val(data.accountId);
	if(null != data.startDate && data.startDate != undefined){
		$('#startDate').prop('valueAsDate', new Date(data.startDate));
	}
	if(null != data.endDate && data.endDate != undefined){
        $('#endDate').prop('valueAsDate', new Date(data.endDate));
    }
	let status = $('#status');
	status.empty();
	status.append(new Option('Select', ''));
	data.statusList.forEach(function(value) {
		status.append(new Option(value, value));
	});
	$('#status').val(data.status);
	$('#createdBy').val(data.createdBy);
	$('#createdDate').prop('valueAsDate', new Date(data.createdDate));
	$('#modifiedBy').val(data.modifiedBy);
	$('#modifiedDate').prop('valueAsDate', new Date(data.modifiedDate));
	$('#addProjectModel').modal('toggle');
}