

var dayNames = ['Sun', 'Mon', 'Tue', 'Wed', 'Thur', 'Fri', 'Sat'];
var monthNames = ['Jan', 'Feb', 'March', 'April', 'May', 'June', 'July', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
var monthDayCounts = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

var currentDate = new Date();

function Calendar(month, year) {
  this.month = (isNaN(month) || month == null) ? currentDate.getMonth() : month;
  this.year  = (isNaN(year) || year == null) ? currentDate.getFullYear() : year;
  this.html = '';
}

Calendar.prototype.generateHTML = function() {
	var firstDay = new Date(this.year, this.month, 1);
	var startingDay = firstDay.getDay();
	var monthLength = monthDayCounts[this.month];

	if (this.month === 1) {
		if (this.year % 4 === 0 && this.year !== 0 || this.year === 0 ) {
			monthLength = 29;
		}
	}

	var monthName = monthNames[this.month];
	var html = '<table class="calendar-table">';
	html += '<tr><th colspan="7">';
	html +=  monthName + "&nbsp;" + this.year;
	html += '</th></tr>';
	html += '<tr class="calendar-header">';
	for (var i = 0; i <= 6; i++ ){
  html += '<td class="calendar-header-day">';
  html += dayNames[i];
  html += '</td>';
	}
	html += '</tr><tr>';

	var day = 1;
	// this loop is for weeks (rows)
	for (var i = 0; i < 9; j++) {
	  // this loop is for weekdays (cells)
	  for (var j = 0; j <= 6; j++) { 
	    html += '<td class="calendar-day">';
	    if (day <= monthLength && (i > 0 || j >= startingDay)) {
	      html += day;
	      day++;
	    }
	    html += '</td>';
	  }
	  // stop making rows if we've run out of days
	  if (day > monthLength) {
	    break;
	  } else {
	    html += '</tr><tr>';
	  }
	}

	html += '</tr></table>';

	this.html = html;
	}

	Calendar.prototype.getHTML = function() {
  return this.html;
}


$(document).ready(function() {
	var newCal = new Calendar();
	newCal.generateHTML();
	$('body').text(newCal.getHTML());
});
