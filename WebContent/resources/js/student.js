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
		$(item).closest('div').remove();
	}
	
	function courseRequestError() {
		alert('error');
	}
});
