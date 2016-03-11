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
			$.each(data, function(index, currentCourse) {	
				$('.availableCourses').append(
						'click on the course name' +
						'<div class="availableCourse" id="'  + currentCourse.id + '">' +		
						currentCourse.name
						+ '</div><br>'
				);
			});
		}
		
	}
	
	function getCoursesError() {
		alert('get courses error');
	}
	
	// choose course and show assign button
	$('.availableCourses').on('click', '.availableCourse', function() {
		var id = $(this).attr('id');
		choosenCourse = id;
		
		$('.availableCourses').css('color', 'grey');
		$(this).css('color', 'red');
		
		$('.availableCourses').append(
		    	'<button id="assignCourseButton">Assign Course' + '</button><br>'
		);
	});
	
	
	// assign course to teacher
	$('.availableCourses').on('click', '#assignCourseButton', function(){
		$.ajax({
			type: 'POST',
			url: 'assignCourseToTeacher', 
			data : JSON.stringify({
				teacherId: choosenTeacher,
				courseId: choosenCourse
			}),
			accept: 'application/json',
			success: assignSuccess,
			error: assignError,
			contentType: 'application/json',
			dataType: 'json'
		});
	});
	
	
	function assignSuccess() {
		alert('Successfully assigned course.');
		$('.availableCourses').empty();
	}
	
	function assignError() {
		alert('error');
	}
	
	// approve course request
	$('a.courseRequest').click(function(event){
		event.preventDefault();

		$.ajax({
			type: 'POST',
			url: 'approveCourserequest',
			data: JSON.stringify({
				courseRequestId: $(this).attr('id')
			}),
			accept: 'application/json',
			success: approvalSuccess(this),
			error: approvalError,
			contentType: 'application/json',
			dataType: 'json'
		});
		
		
	});
	
	function approvalSuccess(item) {
		alert('successfuly approved course request');
		$(item).closest('div').remove();
	}
	
	function approvalError() {
		alert('error');
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
});