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
		var getStudentsUrl = 'getStudentsCourse/' + choosenCourse;
		
		$.get(getStudentsUrl, function(data) {
			$.each(data, function(index, currentStudent) {
				
				var studentNode = document.createElement("div");
				studentNode.setAttribute("class", "student");
				studentNode.setAttribute("id", currentStudent.id);
				studentNode.appendChild(document.createTextNode(currentStudent.username));
				$('.studentsOfCourse').append(studentNode);

			});
		});

		$('.studentsOfCourse').on('click', '.student', function() {
			choosenStudent = $(this).attr('id');
			$('.addAbsence').remove();
			$('.addGrade').remove();
			$('.student').css('color', 'black');
			$(this).css('color', 'red');
			
			$(this).after(createGradeForm());
			$(this).after(createAbsenceForm());
		});
	});

	$('.studentsOfCourse').on('click', '.addAbsenceBtn', function() {

		var justified = $('input:radio[name=justified]:checked').val();
		var comment = $('#absenceComment').val();

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
		
		$('#responseStatus')
			.show()
			.text("Absence addded successfuly.")
			.fadeOut(2000);
	}
	function addAbsenceError() {
		$('.addAbsence').remove();
		$('.addGrade').remove();

		$('#responseStatus')
			.show()
			.text("Absence wasnt't added.")
			.fadeOut(2000);
	}
	// add grade
	$('.studentsOfCourse').on('click', '.addGradeBtn', function() {

		var grade = $('#grade').val();
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

function createAbsenceForm() {
	var absenceDiv = document.createElement("div");
	absenceDiv.setAttribute("class", "addAbsence");
	
	var absenceLabel = document.createElement("label");
	absenceLabel.appendChild(document.createTextNode("add absence"));
	
	var absenceJustificationLabel = document.createElement("label");
	
	var absenceJustification = document.createElement("input");
	absenceJustification.setAttribute("type", "radio");
	absenceJustification.setAttribute("name", "justified");
	absenceJustification.setAttribute("value", true);
	absenceJustification.setAttribute("id", "absence");
	
	absenceJustificationLabel.appendChild(absenceJustification);
	absenceJustificationLabel.appendChild(document.createTextNode("justified"));

	var absenceCommentLabel = document.createElement("label");
	absenceCommentLabel.appendChild(document.createTextNode("absence comment"));
	
	var absenceCommentInput = document.createElement("input");
	absenceCommentInput.setAttribute("class", "form-control");
	absenceCommentInput.setAttribute("id", "absenceComment");
	
	var absenceAddButton = document.createElement("button");
	absenceAddButton.setAttribute("class", "addAbsenceBtn btn btn-default form-control");
	absenceAddButton.appendChild(document.createTextNode("add absence"));
	
	appendBreak(absenceDiv);
	appendBreak(absenceDiv);
	absenceDiv.appendChild(absenceLabel);
	appendBreak(absenceDiv);
	absenceDiv.appendChild(absenceJustificationLabel);
	appendBreak(absenceDiv);
	absenceDiv.appendChild(absenceCommentLabel);
	absenceDiv.appendChild(absenceCommentInput);
	appendBreak(absenceDiv);
	absenceDiv.appendChild(absenceAddButton);
	absenceDiv.appendChild(document.createElement("hr"));
	
	return absenceDiv;
}

function createGradeForm() {
	var gradeDiv = document.createElement("div");
	gradeDiv.setAttribute("class", "addGrade");
	
	var gradeLabel = document.createElement("label");
	gradeLabel.appendChild(document.createTextNode("add grade"));
	
	var gradeInput = document.createElement("input");
	gradeInput.setAttribute("class", "form-control");
	gradeInput.setAttribute("id", "grade");
	
	var gradeAddButton = document.createElement("button");
	gradeAddButton.setAttribute("class", "addGradeBtn btn btn-default form-control");
	gradeAddButton.appendChild(document.createTextNode("add grade"));
	
	gradeDiv.appendChild(gradeLabel);
	gradeDiv.appendChild(gradeInput);
	appendBreak(gradeDiv);
	gradeDiv.appendChild(gradeAddButton);
	gradeDiv.appendChild(document.createElement("hr"));
	
	return gradeDiv;
}

function appendBreak(element) {
	element.appendChild(document.createElement("br"));
}