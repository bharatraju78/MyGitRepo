function addRAL(){
	var reqData = [];
	$('input:checkbox:checked').each(function() {
		var value = $(this).val();
        console.log('=------->'+value);
		reqData.push(value);
    });
	console.log('reqData: '+JSON.stringify(reqData));
	if(reqData.length != 0){
		$.ajax({
			type: "post",
			url: $('#contextPath').val() + "/admin/mapRolesLabels/saveMapRolesHeaders",
			beforeSend: function(xhr) {
				xhr.setRequestHeader('X-CSRF-TOKEN', $('[name="_csrf"]').val());
			},
			contentType: "application/json; charset=utf-8",
			dataType: "HTML",
			data: JSON.stringify(reqData),
			success: renderData
		});
	}
		
}