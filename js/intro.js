$(document).ready(function() {
	$('.phyllum').each(function() {
		$('button.open').click(function() {
			$(this).closest('.phyllum').toggleClass('move-up');
			$('button.open span').toggleClass('visually-hidden');
		});
	});
});