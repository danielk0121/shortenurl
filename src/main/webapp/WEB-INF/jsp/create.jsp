<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>shortening url !</title>
</head>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
$(function() {
	$("#btnSaveAndSearch").click(function() {
		var inputData = JSON.stringify({ 
				url : $("#url").val()
			});
        $.ajax({
			type : 'post',
			url : '/save',
			data : inputData,
		    contentType: "application/json; charset=utf-8",
			dataType : "json",
			error: function(xhr, status, error) {
			    alert(error);
			},
			success : function(json) {
			    $("#responseText").text(JSON.stringify(json, null, 4));
			},
        });
	});
});
</script>
 
<body>
   	<input type="text" name="url" id="url" value="http://www.naver.com" />
   	<input type="button" id="btnSaveAndSearch" value="saveAndSearch">
    <pre id="responseText"></pre>
</body>
</html>