<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta content="text/html; charset=ISO-8859-1">
<title>Traitement</title>
</head>
<body>
	<p> Nom : ${paramValues.nom}</p>
	<c:set var="test" value ="${paramValues.key}"/>
	${test.key }
	<p> Prénom : ${param.prenom }</p>
	<p> Pays : </p>
	<ul>
	<c:forEach items="${paramValues.pays}" var="pays">
		<li>${pays}</li>
	</c:forEach>
	</ul>
	
	<p> Autre pays :</p>
	<ul>
		<c:forTokens var="pays" items="${param.autre }" delims=",">
			 <li>${pays }</li>
		 </c:forTokens>
		
	</ul>

	</body>
</html>