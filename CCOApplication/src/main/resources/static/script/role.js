$(document).ready(function() {
	$("#addRoleForm").validate({
		rules: {
			name: {
				required: true,
				minlength: 2
			}
		},
		messages: {
			roleNameMP: {
				required: "Please enter a role name",
				minlength: "Role name must be at least 2 characters long"
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
	
});	

function searchRole(){
	$.ajax({
		  type: "GET",
		  url: $('#contextPath').val()+"/admin/roles/searchRolesByName/"+$('#roleName').val(),
		  success: renderRoles,
		  dataType: "HTML"
	});
}

function showAddRole(){
	$('#addRoleModel').show();
}

function addRole(){
	if($('#addRoleForm').valid()){
		var roleId = $('#id').val();
		if(null == roleId || '' == roleId){
			roleId = 0;
		}
		$.ajax({
			  type: "GET",
			  url: $('#contextPath').val()+"/admin/roles/addRole/"+$('#name').val()+"/"+roleId,
			  success: closePopup,
			  dataType: "HTML"
		});
	}
	return false;
}

function closePopup(data){
	$('.modal-backdrop').remove();
	renderRoles(data);
	$('#containerMain').removeClass('modal-open');
	$('.modal-backdrop').hide();
}

function deleteRole(val){
	$.ajax({
		  type: "GET",
		  url: $('#contextPath').val()+"/admin/roles/deleteRole/"+val,
		  success: renderRoles,
		  dataType: "HTML"
	});
}

function showUpdateRole(id){
    $.ajax({
          type: "GET",
          url: $('#contextPath').val()+"/admin/roles/getRole/"+id,
          success: renderModifyRole,
          dataType: "JSON"
    });
}

function renderModifyRole(data){
	if(null != data){
	    $('#id').val(data.id);
	    $('#name').val(data.name);
		$('#addBtn').click();
	}
	
}
