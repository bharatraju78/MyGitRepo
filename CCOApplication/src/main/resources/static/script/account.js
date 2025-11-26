$(function() {
	$("#accountForm").validate({
		rules: {
			accNo: {
				required: true,
				minlength: 5
			},
			accName: {
				required: true,
				minlength: 8
			},
			accCreatedDate: {
				required: true
			},
			accStartDate: {
				required: true
			},
			accClassification: {
				required: true
			},
			accTier: {
				required: true
			},
			accStatus: {
                required: true
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
				required: "Please enter a account number",
				minlength: "Account number must be at least 5 characters long"
			},
			accName: {
				required: "Please enter a account name",
				minlength: "Account name must be at least 8 characters long"
			},
			accCreatedDate: {
				required: "Please enter a created date"
			},
			accStartDate: {
				required: "Please enter a start date"
			},
			accClassification: {
				required: "Please enter a Classification"
			},
			accTier: {
				required: "Please enter account tier"
			},
			accStatus: {
				required: "Please enter account status"
			},
			createdBy: {
				required: "Please enter created by"
            },
			createdDate: {
				required: "Please enter created date"
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
					url: $('#contextPath').val()+'/admin/accounts/uploadFile', 
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

function searchAccount(){
	if($('#accountName').val().length >=3){
		$.ajax({
				  type: "GET",
				  url: $('#contextPath').val()+"/admin/accounts/searchAccountsByName/"+$('#accountName').val(),
				  //data: $('#sales').serialize(),
				  success: renderUsers,
				  dataType: "HTML"
			});
	}
}

function showAddAccount(){
	$("#accountForm").trigger('reset');
	$('#addAccountModel').show();
	$.ajax({
		type: "GET",
		url: $('#contextPath').val() + "/admin/accounts/generateAccountNumber",
		beforeSend: function(xhr) {
			xhr.setRequestHeader('X-CSRF-TOKEN', $('[name="_csrf"]').val());
		},
		success: function(response) {
			$('#accNo').val(response.accNo);
			$('#createdBy').val(response.createdBy);
			$('#createdDate').prop('valueAsDate', new Date(response.createdDate));
			$('#modifiedBy').val(response.modifiedBy);
			$('#modifiedDate').prop('valueAsDate', new Date(response.modifiedDate));
			let accStatus = $('#accStatus');
			accStatus.empty();
			accStatus.append(new Option('Select', ''));
			response.accStatusList.forEach(function(value) {
				accStatus.append(new Option(value, value));
			});
		},
		dataType: "JSON"
	});
	
}

function addAccount(){
	var reqData = JSON.stringify(serializeObject($('#accountForm')));
	if($( "#accountForm" ).valid()){
		$.ajax({
			  type: "post",
			  url: $('#contextPath').val()+"/admin/accounts/saveAccount",
			  beforeSend: function(xhr) {
			        xhr.setRequestHeader('X-CSRF-TOKEN', $('[name="_csrf"]').val());
			    },
			  contentType: "application/json; charset=utf-8",
			  dataType: "HTML",
			  data: reqData,
			  success: closeAccountPopup
		});
	}
}

function closeAccountPopup(data){
	$('#addAccountModel').modal('hide')
	renderData(data);
	$('#containerMain').removeClass('modal-open');
	$('.modal-backdrop').hide();
}

function deleteAccount(val){
	$.ajax({
		  type: "GET",
		  url: $('#contextPath').val()+"/admin/accounts/deleteAccount/"+val,
		  beforeSend: function(xhr) {
		        xhr.setRequestHeader('X-CSRF-TOKEN', $('[name="_csrf"]').val());
		    },
		  success: renderUsers,
		  dataType: "HTML"
	});
}

function showUpdateAccount(id){
	$.ajax({
		  type: "GET",
		  url: $('#contextPath').val()+"/admin/accounts/editAccount/"+id,
		  beforeSend: function(xhr) {
		        xhr.setRequestHeader('X-CSRF-TOKEN', $('[name="_csrf"]').val());
		    },
		  success: renderUpdateAccount,
		  dataType: "JSON"
	});
}

function renderUpdateAccount(data){
	$('#accountId').val(data.accountId);
	$('#accNo').val(data.accNo);
	$('#accName').val(data.accName);
	$('#accStatus').val(data.accStatus);
	$('#accType').val(data.accType);
	
	if(null != data.accCreatedDate && '' != data.accCreatedDate)
        $('#accCreatedDate').prop('valueAsDate', new Date(data.accCreatedDate));

	if(null != data.accStartDate && '' != data.accStartDate)
        $('#accStartDate').prop('valueAsDate', new Date(data.accStartDate));
   
	$('#accClassification').val(data.accClassification);
	$('#accTier').val(data.accTier);
	$('#createdBy').val(data.createdBy);
	$('#createdDate').prop('valueAsDate', new Date(data.createdDate));
	$('#modifiedBy').val(data.modifiedBy);
	$('#modifiedDate').prop('valueAsDate', new Date(data.modifiedDate));
	let accStatus = $('#accStatus');
	accStatus.empty();
	accStatus.append(new Option('Select', ''));
	data.accStatusList.forEach(function(value) {
		accStatus.append(new Option(value, value));
	});
	$('#accStatus').val(data.accStatus);
	$('#addAccountModel').modal('toggle');
}
