$(document).ready(function() {
	
	$('#associateType').on('change', function() {
		
		console.log('project id: ' + $('#projectId').val());
		var action = $('#contextPath').val() + '/admin/map-associates/addNewMapping-select/' +
				 $('#projectId').val()+'/'+$('#accountId').val()+'/'+$('#associateType').val();
		console.log('Action URL: ' + action);		 
		$('#addMapLeaderShip').attr('action', action);
		$('#addMapLeaderShip').submit();
	});
});