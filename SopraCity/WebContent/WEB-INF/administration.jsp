<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/style.css" rel="stylesheet"/>
<title>SopraCity - Administration</title>
</head>
<body>
<section>
	<h1>Administration du site SopraCity</h1>
	
	<fieldset>
		<c:if test="${ !empty message }"><p style="color:red;"><c:out value="${ message }" /></p></c:if>
		<form method="post" action="">
			<p>
	            <label for="login">Login : </label>
	            <input type="text" name="login" id="login" />
	        </p>
	        <p>
	            <label for="password">Password : </label>
	            <input type="password" name="password" id="password" />
	        </p>
	        <p>
				<input type="submit" value="Connexion"/>
			</p>
		</form>
	</fieldset>
	
</section>
	
</body>
</html>