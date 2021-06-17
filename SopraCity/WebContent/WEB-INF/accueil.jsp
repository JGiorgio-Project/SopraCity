<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sopra City</title>
<!-- CSS -->
    <link href="css/style.css" rel="stylesheet"/>
</head>
<body>
<nav>
	<a href="Administration">Administration</a>
</nav>

<section>
	<h1><c:out value="${ page.titre }" /></h1> 
	<div id="accueilContent">
		<h2>Nombre d'Habitants : <c:out value="${ page.nbHabitants }" /></h2>
		<p><c:out value="${ page.contenu }" /></p>
		<p>Prochain conseil municipal : <fmt:formatDate pattern = "dd/MM/yyyy" value = "${page.date}" /></p>
	</div>
	
</section>
	
</body>
</html>