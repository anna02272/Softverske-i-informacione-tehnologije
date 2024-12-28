<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>File upload</title>
		<script type="text/javascript">
			function checkStatus(){
				param = window.location.search;
				if(param.indexOf("?") != -1){
					message = param.split("?");
					if(message == "success"){
						document.getElementById("message").innerHTML = "File upload successful";
					}else{
						document.getElementById("message").innerHTML = "File upload failed";
					}
				}
			}
		</script>
	</head>
	<body>
		<center>
		<img alt="logo" src="ftn.jpg" width="150px" height="150px">
		<br/>
		<br><span id="message" style="color:red;font-size:20px">&nbsp;</span>
		<form action="Upload" method="post" accept-charset="UTF-8" enctype="multipart/form-data">
			<table>
				<tr><td>Document</td><td><input type="file" name="doc"/></td></tr>
				<tr><td colspan="2"><input type="submit" value="Confirm"/></td></tr>
			</table>
		</form>
		</center>
		<script type="text/javascript">
			checkStatus();
		</script>
	</body>
</html>