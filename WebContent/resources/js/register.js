$(document).ready(function(){
	
	$('.username').blur(function() {
		
		var username = $(this).val();
		
		$.ajax({
			type: 'GET',
			url: 'checkUsernameAvailability',
			data: {
				username: username
			},
			accept: 'application/json',
			success: $.proxy(courseRequestSuccess, this),
			error: courseRequestError,
			contentType: 'application/json',
			dataType: 'json'
		});
		
		function courseRequestSuccess(resultData) {
			
			var status;
			if(resultData == false) {
				status = "username is not available";
				$('input[type=submit]').prop('disabled', true);
			} else {
				status = "username is available";
				$('input[type=submit]').prop('disabled', false);
			}
			$('.usernameAvailability').text("");
			$(this).next('.usernameAvailability').text(status);
			
		}
		
		function courseRequestError() {
			alert('something went wrong');
		}
		
	});
	
});