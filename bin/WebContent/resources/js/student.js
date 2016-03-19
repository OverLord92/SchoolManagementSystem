$(document).ready(function(){

	$('.requestLink').click(function(event){
		event.preventDefault();

		var url = $(this).attr('href');

		$.ajax({
			type: 'POST',
			url: url,
			accept: 'application/json',
			success: courseRequestSuccess($(this)),
			error: courseRequestError,
			contentType: 'application/json',
			dataType: 'json'
		});
	});

	function courseRequestSuccess(item) {
		$(item).closest('div').append("successfully requested");
		$(item).closest('div').fadeOut(2000);
	}

	function courseRequestError() {
		var errorDiv = document.createElement("div");
		errorDiv.appendChild(document.createTextNode("request was not successful"));
		
		$(item).closest('div')
			.append(errorDiv)
			.fadeOut(2000);
	}
});
