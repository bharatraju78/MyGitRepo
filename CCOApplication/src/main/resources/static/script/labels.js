$(document).ready(function() {
	$("#addLabelForm").validate({
		rules: {
			labelName: {
				required: true,
			}
		},
		messages: {
			roleNameMP: {
				required: "Please enter a role name",
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
				url: $('#contextPath').val() + '/admin/labels/uploadFile',
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

function searchLabel(){
	$.ajax({
		  type: "GET",
		  url: $('#contextPath').val()+"/admin/labels/searchLabelByName/"+$('#labelNameSer').val(),
		  success: renderData,
		  dataType: "HTML"
	});
}

function showAddLabel(){
	$('#addLabelModel').show();
	$.ajax({
		type: "GET",
		url: $('#contextPath').val() + "/admin/labels/fetchAuditFields",
		beforeSend: function(xhr) {
			xhr.setRequestHeader('X-CSRF-TOKEN', $('[name="_csrf"]').val());
		},
		success: function(response) {
			console.log(response);
			$('#orderNo').val(response.orderNo);
			$('#createdBy').val(response.createdBy);
			$('#createdDate').prop('valueAsDate', new Date(response.createdDate));
			$('#modifiedBy').val(response.modifiedBy);
			$('#modifiedDate').prop('valueAsDate', new Date(response.modifiedDate));
		},
		dataType: "JSON"
	});
}

function addLabel(){
	if($('#addLabelForm').valid()){
		var reqData = JSON.stringify(serializeObject($('#addLabelForm')));
		console.log(reqData);
		$.ajax({
			type: "post",
			url: $('#contextPath').val() + "/admin/labels/addLabel",
			beforeSend: function(xhr) {
				xhr.setRequestHeader('X-CSRF-TOKEN', $('[name="_csrf"]').val());
			},
			contentType: "application/json; charset=utf-8",
			dataType: "HTML",
			data: reqData,
			success: closePopup
		});
	}
	return false;
}

function closePopup(data){
	$('.modal-backdrop').remove();
	renderData(data);
	$('#containerMain').removeClass('modal-open');
	$('.modal-backdrop').hide();
}

function deleteLabel(val){
	$.ajax({
		  type: "GET",
		  url: $('#contextPath').val()+"/admin/labels/deleteLabel/"+val,
		  success: renderData,
		  dataType: "HTML"
	});
}

function showUpdateLabel(id){
    $.ajax({
          type: "GET",
          url: $('#contextPath').val()+"/admin/labels/getLabel/"+id,
          success: renderModifyLabel,
          dataType: "JSON"
    });
}

function renderModifyLabel(data){
	if(null != data){
	    $('#labelId').val(data.labelId);
	    $('#labelName').val(data.labelName);
		$('#orderNo').val(data.orderNo);
		$('#createdBy').val(data.createdBy);
		$('#createdDate').prop('valueAsDate', new Date(data.createdDate));
		$('#modifiedBy').val(data.modifiedBy);
		$('#modifiedDate').prop('valueAsDate', new Date(data.modifiedDate));
		$('#addLabelModel').modal("show");
	}
	
}
