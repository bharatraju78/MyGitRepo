
$(function() {
	$('.nav-link').on('click', function() {
		if ($(this).attr('href') != '#empDetails') {
			$('#empDetails').removeClass('show');
		}
	});
	
});

$(document).ready(function() {
	if ($('#endDateStr').val() != null && $('#endDateStr').val() != '') {
		$('#endDate').prop('valueAsDate', new Date($('#endDateStr').val()));
	}
	var activeTab = $('#activeTab').val();
	if(activeTab != null && activeTab != '') {
        if('Designation' == activeTab) {
			$('#designationTab').click();
		} else if('Grade' == activeTab) {
            $('#gradeTab').click();
        } else if('Grade' == activeTab) {
            $('#gradeTab').click();
        } else if('Compensation' == activeTab) {
			$('#salaryTab').click();
        } else if('OffBoarding' == activeTab) {
			$('#offBoardTab').click();
        }
	}
});


