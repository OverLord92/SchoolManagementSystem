$(document).ready(function(){

	$('.username').blur(function() {
		
		var username = $(this).val();

		if(username) {
			$.ajax({
				type: 'GET',
				url: 'checkUsernameAvailability',
				data: {
					username: username
				},
				accept: 'application/json',
				success: $.proxy(usernameAvailabilitySuccess, this),
				error: $.proxy(usernameAvailabilityError, this),
				contentType: 'application/json',
				dataType: 'json'
			});
		}
		
		function usernameAvailabilitySuccess(resultData) {
	
			var status;
			if(resultData === false) {
				status = "username is not available";
				$('input[type=submit]').prop('disabled', true);
			} else {
				status = "username is available";
				$('input[type=submit]').prop('disabled', false);
			}
			
			$('.usernameAvailability').text("");
			
			$(this).next('.usernameAvailability').text(status);

		}

		function usernameAvailabilityError() {
			$(this).next('.usernameAvailability').text("something went wrond, try again later");
		}

	});

});