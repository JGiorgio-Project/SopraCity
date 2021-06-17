<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/style.css" rel="stylesheet"/>
<title>SopraCity - Configuration</title>
</head>
<body>
<nav>
	<a href="Deconnexion">Déconnexion</a>
</nav>
<section>
	<h1>Configuration du site SopraCity</h1>
	<fieldset id="fieldConfig">
		<c:if test="${ !empty message }"><p style="color:red;"><c:out value="${ message }" /></p></c:if>
		<form method="post" action="/SopraCity/Configuration">
			<p>
	            <label for="titre">Titre : </label>
	            <input type="text" name="titre" id="titre" value="<c:out value="${ page.titre }" />"/>
	        </p>
	        <p>
	            <label for="habitants">Habitants : </label>
	            <input type="number" name="habitants" id="habitants" value="<c:out value="${ page.nbHabitants }" />" />
	        </p>
	        <p>
	            <label for="texte">Texte : </label>
	            <textarea name="texte" id="texte" cols="50" rows="10" ><c:out value="${ page.contenu }" /></textarea>
	        </p>
	        <p>
	            <label for="date">Date : </label>
	            <input type="date" name="date" id="date" value="<c:out value="${ page.date }" />" />
	        </p>
	        <input id="valForm" name="valForm" type="hidden" value="1">
	        <p>
				<input type="submit" value="Enregistrer"/>
			</p>
		</form>
	</fieldset>
</section>
</body>
</html>