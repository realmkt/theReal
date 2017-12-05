$(function () {
	var $activate_scrollator4 = $('#activate_scrollator4');


	$activate_scrollator4.bind('click', function () {
		var $document_body = $('body');
		if ($document_body.data('scrollator') === undefined) {
			$document_body.scrollator({
				custom_class: 'body_scroller'
			});
			$activate_scrollator4.val('destroy scrollator')
		} else {
			$document_body.scrollator('destroy');
			$activate_scrollator4.val('activate scrollator')
		}
	});

	$activate_scrollator4.trigger('click');
});



 
$(document).ready(function(){
  $("#allCheck").on("click",function(){

   var _value = $(this).is(":checked");
   $('input:checkbox[name="subCheck"]').each(function () { 
    this.checked = _value; 
   });
  });
});
 
 
