$(document).ready(function(){
	
	var choosenCourse;
	var choosenStudent;
	
	// get students who attend clicked course
	$('.course').click(function(event) {
		event.preventDefault();
		
		$('.course').css('color', 'blue');
		$(this).css('color', 'red');
		$('.studentsOfCourse').empty();
		
		choosenCourse = $(this).attr('id');
		var methodLink = 'getStudentsCourse/' + choosenCourse;
		
		$.get(methodLink, function(data) {
			$.each(data, function() {
				$.each(this, function(k, v) {
					
					$('.studentsOfCourse').append(
							'<div class="student" id="'+ v.id + '">' +
							v.username
							+ '<div>'
					);
				});
			});
		});
		
		$('.studentsOfCourse').on('click', '.student', function() {
			
			choosenStudent = $(this).attr('id');
					
			$('.addAbsence').remove();
			$('.addGrade').remove();
			$('.student').css('color', 'black');
			$(this).css('color', 'red');
			$(this).after('<div class="addGrade"><br>add grade<input id="grade"><br><button class="addGradeBtn">add grade</button><hr></div>');
			$(this).after('<div class="addAbsence">' + 
								'<input type="radio" name="justified" value="justified" id="absenceState">justified<br>' +
								'<input type="radio" name="justified" value="notJustified" id="absenceState">not justified<br>' +
								'<br>comment<input id="absenceComment">' +
								'<br><button class="addAbsenceBtn">add absence</button>');
		});
		
	});
	
	// add absence
	$('.studentsOfCourse').on('click', '.addAbsenceBtn', function() {
		
		var justified = $('input:radio[name=justified]:checked').val();
		var comment = $('#absenceComment').val();
		
		
		if(justified == 'justified') {
			justified = true;
		} else {
			justified = false;
		}
		
		$.ajax({
			type: 'POST',
			url: 'addAbsenceToUser', 
			data : JSON.stringify({
				studentId: choosenStudent,
				courseId: choosenCourse,
				justified: justified,
				comment: comment
			}), 
			accept: 'application/json',
			success: addAbsenceSuccesencess,
			error: addAbsenceError,
			contentType: 'application/json',
			dataType: 'json'
		});
		
	});
	
	function addAbsenceSuccesencess() {
		$('.addAbsence').remove();
		$('.addGrade').remove();
		alert('absence success');
	}
	
	function addAbsenceError() {
		$('.addAbsence').remove();
		$('.addGrade').remove();
		alert('absence eror');
	}
	
	
	// add grade
	$('.studentsOfCourse').on('click', '.addGradeBtn', function() {
		
		var grade = $('#grade').val()
		
		$.ajax({
			type: 'POST',
			url: 'addGradeToUser', 
			data : JSON.stringify({
				studentId: choosenStudent,
				courseId: choosenCourse,
				grade: grade
			}), 
			accept: 'application/json',
			success: addAbsenceSuccesencess,
			error: addAbsenceError,
			contentType: 'application/json',
			dataType: 'json'
		});
	});
	
	
	
	
	
	
	
	
	
	
	
	
	
	
});