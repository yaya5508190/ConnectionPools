<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value="/resources/JSLIB/jquery-2.1.0.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/JSLIB/json2.js"/>"></script>
<title>Insert title here</title>
</head>
<body>
	${result}
	<form id="form" action="<c:url value="/search"/>" method="post">
		<input type="text" name="sql" width="100px">
		<input type="submit">
	</form>
	<span id="show"></span>
	<script type="text/javascript">
		$( "#form" ).on(
			"submit",
			function( event ) {
				event.preventDefault();
				var form = $( this ),
				url = form.attr( "action" );
				$.ajax({type: "POST", 
						url: url, 
						data: form.serialize(), 
						contentType: "application/x-www-form-urlencoded", 
						dataType: "text", 
						success: function(date) { console.log("success"+date); }, 
						error: function(date) { console.log("error"+date); }
						});
			}
		);
		
		 var task = function(){$.ajax({type: "POST", 
									  url: "<c:url value="/getInfo"/>",
									  success: function(date) {$("#show").html(JSON.stringify(date));}, 
									  error: function(date) { console.log(date); }
									});
							};
		var timer = setInterval(task,1000);
	</script>
</body>
</html>