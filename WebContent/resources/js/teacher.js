$(document).ready(function(){
	
	$('.chooseCourseLink').click(function(event) {
		event.preventDefault();
		
		$('.chooseCourseLink').css('color', 'blue');
		$(this).css('color', 'red');
		$('.studentsOfCourse').empty();
		
		var methodLink = $(this).attr('href');
		
		$.get(methodLink, function(data) {
			$.each(data, function() {
				$.each(this, function(k, v) {
					
					$('.studentsOfCourse').append(
							'<div class="student">' +
							v.username
							+ '<div>'
					);
				});
			});
		});
		
		$('.studentsOfCourse').on('click', '.student', function() {
			alert('opalilo');
		})
		
	});
	
});