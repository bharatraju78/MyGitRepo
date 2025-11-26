$(function() {
	$("#verticalForm").validate({
		rules: {
			verticalName: {
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
			verticalName: {
				required: "Please enter a Vertical Name",
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
					url: $('#contextPath').val()+'/admin/verticals/uploadFile', 
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

function searchVertical(){
	if($('#verticalSerName').val().length >=3){
		$.ajax({
			type: "GET",
			url: $('#contextPath').val() + "/admin/verticals/searchVerticalByName/" + $('#verticalSerName').val(),
			success: renderData,
			dataType: "HTML"
		});
	}
}

function showVertical(){
	$("#verticalForm").trigger('reset');
	$('#addVerticalModel').show();
	$.ajax({
		type: "GET",
		url: $('#contextPath').val() + "/admin/verticals/setAuditFields",
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

function addVertical(){
	var reqData = JSON.stringify(serializeObject($('#verticalForm')));
	if($("#verticalForm" ).valid()){
		$.ajax({
			  type: "post",
			  url: $('#contextPath').val()+"/admin/verticals/saveVertical",
			  beforeSend: function(xhr) {
			        xhr.setRequestHeader('X-CSRF-TOKEN', $('[name="_csrf"]').val());
			    },
			  contentType: "application/json; charset=utf-8",
			  dataType: "HTML",
			  data: reqData,
			  success: closeVerticalPopup
		});
	}
}

function closeVerticalPopup(data){
	$('#addVerticalModel').modal('hide')
	renderData(data);
	$('#containerMain').removeClass('modal-open');
	$('.modal-backdrop').hide();
}

function deleteVertical(val){
	$.ajax({
		  type: "GET",
		  url: $('#contextPath').val()+"/admin/verticals/deleteVertical/"+val,
		  beforeSend: function(xhr) {
		        xhr.setRequestHeader('X-CSRF-TOKEN', $('[name="_csrf"]').val());
		    },
		  success: renderData,
		  dataType: "HTML"
	});
}

function showUpdateVertical(id){
	$.ajax({
		  type: "GET",
		  url: $('#contextPath').val()+"/admin/verticals/editVertical/"+id,
		  beforeSend: function(xhr) {
		        xhr.setRequestHeader('X-CSRF-TOKEN', $('[name="_csrf"]').val());
		    },
		  success: renderUpdateVertical,
		  dataType: "JSON"
	});
}

function renderUpdateVertical(data){
	
	$('#verticalId').val(data.verticalId);
	$('#verticalName').val(data.verticalName);	
   
	$('#createdBy').val(data.createdBy);
	$('#createdDate').prop('valueAsDate', new Date(data.createdDate));
	$('#modifiedBy').val(data.modifiedBy);
	$('#modifiedDate').prop('valueAsDate', new Date(data.modifiedDate));
	
	$('#addVerticalModel').modal('toggle');
}
