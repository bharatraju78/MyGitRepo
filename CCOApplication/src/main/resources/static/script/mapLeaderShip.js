$(document).ready(function() {

	$('#newRow').bind('click', function() {
		var count = $('#mapLeaderShip tbody').find('tr').length;
		$.ajax({
			type: "GET",
			url: $('#contextPath').val() + "/admin/map-associates/addNewRow/" + count,
			beforeSend: function(xhr) {
				xhr.setRequestHeader('X-CSRF-TOKEN', $('[name="_csrf"]').val());
			},
			success: function(data) {
				$('#mapLeaderShip .tbody').append(data);
			},
			dataType: "HTML"
		});
	});
	
	
	$("#mapAssociatesModel").submit(function(e){
		console.log('Form submitted');
		if(validateMapAssociates()) {
			return true; 
		}
	    return false;
	});
	
	/*$('#save').bind('click', function() {
		var isValid = validateMapAssociates();
        if (isValid) {
            // If validation passes, submit the form
			if($('#mapAssociatesModel').validate()) {
            	$('#mapAssociatesModel').submit();
			}
        }
	});*/
	
});

function validateMapAssociates(){
    var isValid = true;
    var errorMsg = '';
    // Validate all select fields in each row
    $('#mapLeaderShip tbody tr').each(function(index, row) {
        // Leadership
		console.log('Validating row: ' + index);
		if(index != 0) {
	        var leader = $(row).find('select[name^="leaderShipList"][name$=".empId"]');
	        var leaderRole = $(row).find('select[name^="leaderShipList"][name$=".roleId"]');
	        var director = $(row).find('select[name^="directorList"][name$=".empId"]');
	        var directorRole = $(row).find('select[name^="directorList"][name$=".roleId"]');
	        var manager = $(row).find('select[name^="managerList"][name$=".empId"]');
	        var managerRole = $(row).find('select[name^="managerList"][name$=".roleId"]');
	        var count = 0;
			if(leader.val().length == 0 || leader.val() == '') {
	           count++;
	        } 
			if(leaderRole.val().length == 0 || leaderRole.val() == '') {
				count++;
				if(leader.val().length != 0 || leader.val() != ''){
					errorMsg += 'Row ' + (index + 1) + ': Please select a Role for Leadership.\n';
				}
				
			} 
		    if(director.val().length == 0 || director.val() == '') {
                count++;
            } 
		    if(directorRole.val().length == 0 || directorRole.val() == '') {
                count++;
				if(director.val().length != 0 || director.val() != ''){
					errorMsg += 'Row ' + (index + 1) + ': Please select a Role for Director.\n';
				}
            }		        
		    if(manager.val().length == 0 || manager.val() == '') {
                count++;
            }		        
		    if(managerRole.val().length == 0 || managerRole.val() == '') {
                count++;
				if(manager.val().length != 0 || manager.val() != ''){
				    errorMsg += 'Row ' + (index + 1) + ': Please select a Role for Manager.\n';
				}
            }		        
			}
        if(count > 0) {
            isValid = false;
			if(count == 6 && errorMsg == '') {
            	errorMsg = 'Row ' + (index + 1) + ': Please select one Leadership, Director, and Manager and roles.';
			}
        }
	    });
	
    if(!isValid) {
        alert(errorMsg);
    }
    return isValid;
}

function deleteRow(url) {
	window.location.href = url;
}

function showEditRow(url) {
	window.location.href = url;
}