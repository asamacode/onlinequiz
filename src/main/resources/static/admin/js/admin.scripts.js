$(document).ready(function() {
	
	$(".btn-open-dialog-sc").click(function() {
		var id = $(this).closest("tr").attr("data-id");
		$("#mModal #schoolid").val(id);
	});
	
	$("#btn-delete-sc").click(function() {
		
		var id = $("#mModal #schoolid").val();
		
		$.ajax({
			url: '/manager/schoolyear/delete/'+id,
			success: function(mess) {
				$("[data-dismiss]").click();
				location.reload();
				alert(mess);
			}
		});
	});
	
});