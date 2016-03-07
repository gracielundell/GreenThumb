$(document).ready(function() {
	$('.phyllum').each(function() {
		$(this).click(function() {
			$(this).toggleClass('move-up');
		});
	});
});