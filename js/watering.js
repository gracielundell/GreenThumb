$(document).ready(function() {
	var repeatRemind = $('#select-all');
	repeatRemind.change(function() {
		if ($(this).is(':checked') ) {

			$(this).addClass('visually-hidden');
			$('label[for="select-all"').addClass('hidden');
			$('.day').not('.monday').addClass('hidden visually-hidden');
			$('.monday').addClass('prioritize');
			$('.time-select').addClass('reorder');
			$('input[type="text"]').addClass('priority-two');
			$('button[type="submit"').addClass('priority-three');

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