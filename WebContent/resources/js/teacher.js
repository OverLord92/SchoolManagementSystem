$(document).ready(function(){
	
	var choosenCourse;
	var choosenStudent;
	
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
			$(this).after('<div class="addGrade"><br>add grade<input /><br><button>add grade</button><hr></div>');
			$(this).after('<div class="addAbsence"><br><button class="addAbsenceBtn">add absence</button>');
		})
		
	});
	
	$('.studentsOfCourse').on('click', '.addAbsenceBtn', function() {
		
		alert('student ' + choosenStudent + '\ncourse ' + choosenCourse);
		
		$.ajax({
			type: 'POST',
			url: 'addAbsenceToUser', 
			data : JSON.stringify({
				studentId: choosenStudent,
				courseId: choosenCourse
			}),
			accept: 'application/json',
			success: addAbsenceSuccesencess,
			error: addAbsenceError,
			contentType: 'application/json',
			dataType: 'json'
		});
		
	});
	
	function addAbsenceSuccesencess() {
		alert('absence success');
	}
	
	function addAbsenceError() {
		alert('absence eror');
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
});