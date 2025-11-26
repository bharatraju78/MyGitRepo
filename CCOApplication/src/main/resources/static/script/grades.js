$(function() {
	$("#gradeForm").validate({
		rules: {
			gradeName: {
				required: true,
				minlength: 2
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
			gradeName: {
				required: "Please enter a Grade Name",
				minlength: "Role Name must be at least 2 characters long"
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
					url: $('#contextPath').val()+'/admin/grades/uploadFile', 
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

function searchGrade(){
	if($('#gradeSerName').val().length >=1){
		$.ajax({
			type: "GET",
			url: $('#contextPath').val() + "/admin/grades/searchGradeByName/" + $('#gradeSerName').val(),
			success: renderData,
			dataType: "HTML"
		});
	}
}

function showGrade(){
	$("#gradeForm").trigger('reset');
	$('#addGradeModel').show();
	$.ajax({
		type: "GET",
		url: $('#contextPath').val() + "/admin/grades/setAuditFields",
		beforeSend: function(xhr) {
			xhr.setRequestHeader('X-CSRF-TOKEN', $('[name="_csrf"]').val());
		},
		success: function(response) {
			$('#createdBy').val(response.createdBy);
			$('#createdDate').prop('valueAsDate', new Date(response.createdDate));
			$('#modifiedBy').val(response.modifiedBy);
			$('#modifiedDate').prop('valueAsDate', new Date(response.modifiedDate));
		},
		dataType: "JSON"
	});
	
}

function addGrade(){
	var reqData = JSON.stringify(serializeObject($('#gradeForm')));
	if($("#gradeForm" ).valid()){
		$.ajax({
			  type: "post",
			  url: $('#contextPath').val()+"/admin/grades/saveGrade",
			  beforeSend: function(xhr) {
			        xhr.setRequestHeader('X-CSRF-TOKEN', $('[name="_csrf"]').val());
			    },
			  contentType: "application/json; charset=utf-8",
			  dataType: "HTML",
			  data: reqData,
			  success: closeGradePopup
		});
	}
}

function closeGradePopup(data){
	$('#addGradeModel').modal('hide')
	renderData(data);
	$('#containerMain').removeClass('modal-open');
	$('.modal-backdrop').hide();
}

function deleteGrade(val){
	$.ajax({
		  type: "GET",
		  url: $('#contextPath').val()+"/admin/grades/deleteGrade/"+val,
		  beforeSend: function(xhr) {
		        xhr.setRequestHeader('X-CSRF-TOKEN', $('[name="_csrf"]').val());
		    },
		  success: renderData,
		  dataType: "HTML"
	});
}

function showUpdateGrade(id){
	$.ajax({
		  type: "GET",
		  url: $('#contextPath').val()+"/admin/grades/editGrade/"+id,
		  beforeSend: function(xhr) {
		        xhr.setRequestHeader('X-CSRF-TOKEN', $('[name="_csrf"]').val());
		    },
		  success: renderUpdateGrade,
		  dataType: "JSON"
	});
}

function renderUpdateGrade(data){
	
	$('#gradeId').val(data.gradeId);
	$('#gradeName').val(data.gradeName);	
   
	$('#createdBy').val(data.createdBy);
	$('#createdDate').prop('valueAsDate', new Date(data.createdDate));
	$('#modifiedBy').val(data.modifiedBy);
	$('#modifiedDate').prop('valueAsDate', new Date(data.modifiedDate));
	
	$('#addGradeModel').modal('toggle');
}
