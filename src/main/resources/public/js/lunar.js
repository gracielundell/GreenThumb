$(document).ready(function() {

function moonPhase(year,month,day) {
	var lp = 2551443; 
	var now = new Date(year,month-1,day,20,35,0);						
	var new_moon = new Date(1970, 0, 7, 20, 35, 0);
	var phase = ((now.getTime() - new_moon.getTime())/1000) % lp;
	return Math.floor(phase /(24*3600)) + 1;
}

var newDate = new Date();

var dayWeek = newDate.getDay();
var dayMonth = newDate.getDate();
var year = newDate.getFullYear();
var month = newDate.getMonth();



var weekArray = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
var monthArray = ['Jan', 'Feb', 'March', 'April', 'May', 'June', 'July', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];

$('.day-of-week').text(weekArray[dayWeek]);
$('.day-of-month').text(dayMonth);
$('.year').text(year);
$('.month').text(monthArray[month]);

var moonPhaseInt = moonPhase(year, month, dayMonth);

if (moonPhaseInt < 4) {
	$('.lunar').addClass('phase-1');
} else if (moonPhaseInt < 8) {
	$('.lunar').addClass('phase-2').removeClass('phase-1');	
} else if (moonPhaseInt < 12) {
	$('.lunar').addClass('phase-3').removeClass('phase-2');	
} else if (moonPhaseInt < 16) {
	$('.lunar').addClass('phase-4').removeClass('phase-3');	
} else if (moonPhaseInt < 20) {
	$('.lunar').addClass('phase-5').removeClass('phase-4');	
} else if (moonPhaseInt < 25) {
	$('.lunar').addClass('phase-6').removeClass('phase-5');	
} else if (moonPhaseInt < 29) {
	$('.lunar').addClass('phase-7').removeClass('phase-6');	
}

});



