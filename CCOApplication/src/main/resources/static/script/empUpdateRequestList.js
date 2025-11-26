$(document).ready(function() {

	$('.nav-link').on('click', function() {
		if ($(this).attr('href') != '#created') {
			$('#created').removeClass('show');
		}
	});
	
	$('#created-tab').trigger('click');
});