$(document).ready(function(){

	var choosenTeacher;
	var choosenCourse;

	// choose teacher and get all available courses
	$('.teacher').click(function() {
		$('.availableCourses').empty();
		$('.teacher').css('color', 'grey');
		$(this).css('color', 'red');

		var id = $(this).attr('id');
		choosenTeacher = id;

		$.ajax({
			type: 'GET',
			url: 'getCoursesWithoutTeachers',
			accept: 'application/json',
			success: getCoursesSuccess,
			error: getCoursesError,
			contentType: 'application/json',
			dataType: 'json'
		});
	});

	function getCoursesSuccess(data) {

		if(data.length === 0) {
			$('.availableCourses').append('no available courses');
		} else {
			$('.availableCourses').append('click on course name');
			$.each(data, function(index, currentCourse) {
				
				var courseNode = document.createElement("div");
				courseNode.setAttribute("class", "availableCourse");
				courseNode.setAttribute("id", currentCourse.id);
				courseNode.appendChild(document.createTextNode(currentCourse.name));
				$('.availableCourses').append(courseNode);
			});
		}	
	}

	function getCoursesError() {
		$('.availableCourses').append("an error occurred");
		
	}

	// choose course and show assign button
	$('.availableCourses').on('click', '.availableCourse', function() {
		var id = $(this).attr('id');
		choosenCourse = id;

		$('.availableCourses').css('color', 'grey');
		$(this).css('color', 'red');

		var assignCourseBtn = document.createElement("button");
		assignCourseBtn.setAttribute("class", "btn btn-default");
		assignCourseBtn.setAttribute("id", "assignCourseButton");
		assignCourseBtn.appendChild(document.createTextNode("Assign Course"));
		
		$('.availableCourses').append(assignCourseBtn);
		
	});

	// assign course to teacher
	$('.availableCourses').on('click', '#assignCourseButton', function(){
		
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		
		$.ajax({
			type: 'POST',
			url: 'assignCourseToTeacher',
			data : JSON.stringify({
				teacherId: choosenTeacher,
				courseId: choosenCourse
			}),
			beforeSend: function(xhr) {
	            xhr.setRequestHeader(header, token);
	        },
			accept: 'application/json',
			success: assignSuccess,
			error: assignError,
			contentType: 'application/json',
			dataType: 'json'
		});
	});


	function assignSuccess() {
		$('#responseStatus')
			.show()
			.text("Course assigned successfuly.")
			.fadeOut(2000);
		
		$('.availableCourses').empty();
	}

	function assignError() {
		$('#responseStatus')
			.show()
			.text("Course was not assigned successfuly.")
			.fadeOut(2000);
	}
});

$(document).ready(function() {
	
	// approve course request
	$('a.courseRequest').click(function(event){
		event.preventDefault();

		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		
		$.ajax({
			type: 'POST',
			url: 'approveCourserequest',
			data: JSON.stringify({
				courseRequestId: $(this).attr('id')
			}),
			beforeSend: function(xhr) {
	            xhr.setRequestHeader(header, token);
	        },
			accept: 'application/json',
			success: approvalSuccess(this),
			error: approvalError,
			contentType: 'application/json',
			dataType: 'json'
		});


	});

	function approvalSuccess(item) {
		$('#responseStatus')
			.show()
			.text("Course Request successfuly approved.")
			.fadeOut(2000);
			
		$(item).closest('div').remove();
	}

	function approvalError() {
		$('#responseStatus')
			.show()
			.text("Course Request was not successfuly approved. Try again.")
			.fadeOut(2000);
	}
	
});