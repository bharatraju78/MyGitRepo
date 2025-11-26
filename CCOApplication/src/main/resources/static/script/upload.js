$(document).ready(function() {
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
				url: 'http://localhost:9090/pmsapp/admin/upload/uploadFile', 
				type: 'POST',
				beforeSend: function(xhr) {
					xhr.setRequestHeader('X-CSRF-TOKEN', $('[name="_csrf"]').val());
				},
				data: formData,
				contentType: false,
				processData: false,
				success: function(response) {
					$('#uploadStatus').html('<div class="alert alert-success">' + response + '</div>');
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