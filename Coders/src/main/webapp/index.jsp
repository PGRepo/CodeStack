<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>
    		function uploadFormData()
    		{
					
					alert('inside uploadform');
	    			
	                var email = $('#email').val();
	         		var password = $('#password').val();
	              	
					$.ajax({
								
			    				url : "rest/file/signup",
			    			    type: "POST",
			    			    data : "email=" + email + "&password=" + password, 
			    			   
			    			    success:function(data, textStatus, jqXHR){
			    			    	alert('success');
			    			    	window.location.href="home.jsp";
			    			    },
			    			    error:function(jqXHR, textStatus, errorThrown){
			    			    	alert('Could not process request.. ' + e);
			
			    			    }
			    			});
					
			}
    		
    		</script>


</head>

<body>
		<p align = "center">
			<button type="button" class="btn btn-default" onClick="displayForm()">Create Tack</button>
			<button type="button" class="btn btn-default">Create Board</button>
		</p>
		<form method="post" align="center">
			<p>Username:<input type="email" placeholder="Enter your email id" id="email" size="25" /></p>
			<p></p>
			<p>Password:<input type="password" placeholder="Enter your password" id="password" size="25" /></p>
			<p></p>
			<input type="button" Value="ok" onclick="uploadFormData();"/>
		</form>
		
		<!--  window.location.href='home.jsp'  action="rest/file/signup" -->
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    	<!-- Include all compiled plugins (below), or include individual files as needed -->
    	<script src="/js/bootstrap.min.js"></script>

</body>
</html>