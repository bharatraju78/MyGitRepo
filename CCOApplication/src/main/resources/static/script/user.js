$(document).ready(function() {
	$("#userForm").validate({
		rules: {
			username: {
				required: true,
				minlength: 2
			},
			password: {
				required: true,
				minlength: 2
			},
			enabled: {
				required: true
			},
			role: {
				required: true
			}
		},
		messages: {
			username: {
				required: "Please enter a role name",
				minlength: "Role name must be at least 2 characters long"
			},
			password: {
				required: "Please enter a password",
				minlength: "Password must be at least 2 characters long"
			},
			enabled: {
				required: "Please enter true or false"
			},
			role: {
				required: "Please enter a role"
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

function searchUser(){
	$.ajax({
		  type: "GET",
		  url: $('#contextPath').val()+"/admin/users/searchUsersByName/"+$('#userNameSch').val(),
		  success: renderUsers,
		  dataType: "HTML"
	});
}

function showAddUser(){
	$('#addUserModel').show();
}

function addUser(){
	var reqData = JSON.stringify(serializeObject($('#userForm')));
	if($('#userForm').valid()){
		$.ajax({
			  type: "post",
			  url: $('#contextPath').val()+"/admin/users/addUser",
			  beforeSend: function(xhr) {
			        xhr.setRequestHeader('X-CSRF-TOKEN', $('[name="_csrf"]').val());
			    },
			  contentType: "application/json; charset=utf-8",
			  dataType: "HTML",
			  data: reqData,
			  success: closeUserPopup
		});
	}
}

function ferchRoles(){
	$.ajax({
		  type: "GET",
		  url: $('#contextPath').val()+"/admin/roles/fetchRoles",
		  success: setRoles,
		  dataType: "JSON"
	});
}

function setRoles(data){
	
}
function closeUserPopup(data){
	console.log("=----->"+data)
	$('.modal-backdrop').remove();
	renderUsers(data);
}

function deleteUser(val){
	$.ajax({
		  type: "GET",
		  url: $('#contextPath').val()+"/admin/users/deleteUser/"+val,
		  beforeSend: function(xhr) {
		        xhr.setRequestHeader('X-CSRF-TOKEN', $('[name="_csrf"]').val());
		    },
		  success: renderUsers,
		  dataType: "HTML"
	});
}

function showUpdateUser(id){
	$.ajax({
		  type: "GET",
		  url: $('#contextPath').val()+"/admin/users/fetchUser/"+id,
		  beforeSend: function(xhr) {
		        xhr.setRequestHeader('X-CSRF-TOKEN', $('[name="_csrf"]').val());
		    },
		  success: renderUpdateUsers,
		  dataType: "JSON"
	});
}

function renderUpdateUsers(data){
	$('#id').val(data.id);
	$('#username').val(data.username);
	$('#password').val(data.password);
	$('#enabled').val(data.enabled);
	$('#role').val(data.role);
	$('#addUserBtn').click();
}