$(document).ready(function() {
	var repeatRemind = $('#select-all');
	repeatRemind.change(function() {
		if ($(this).is(':checked') ) {

			$('.day').not('.monday').css('display', 'none');
			var hour = $('.monday .select-group .hours').val();
			var mins = $('.monday .select-group .minutes').val();
			var meridian = $('.monday .select-group .meridian').val();
			
			$('.hours').not('.monday .select-group .hours').each(function() {
				$(this).val(hour);
			});

			$('.minutes').not('.monday .select-group .minutes').each(function() {
				$(this).val(mins);
			});

			$('.meridian').not('.monday .select-group .meridian').each(function() {
				$(this).val(meridian);
			});

		}
	});
		
});