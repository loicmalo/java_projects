<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des clients</title>
<link type="text/css" rel="stylesheet" href="inc/style.css" />
</head>
<body>
<c:import url="/inc/menu.jsp"></c:import>

<c:choose>
<c:when test="${empty listClients }">
	<p class="erreur">Aucun client enregistré</p>
</c:when>

<c:otherwise>
<table>
	 <tr>
	     <th>Nom</th>
	     <th>Prénom</th>
	     <th>Adresse</th>
	     <th>Téléphone</th>
	     <th>Email</th>
	     <th class="action">Action</th>                    
	 </tr>
	
<c:forEach items="${listClients }" var="client" varStatus="boucle">
	<tr class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
	<td><c:out value="${client.value.nom }"></c:out></td>
		<td><c:out value="${client.value.prenom }"></c:out></td>
		<td><c:out value="${client.value.adresse }"></c:out></td>
		<td><c:out value="${client.value.telephone }"></c:out></td>
		<td><c:out value="${client.value.email }"></c:out></td>
		<td><a href="<c:url value="/suppressionClient">
				<c:param name="nom" value="${client.value.nom }"/>
			</c:url>">Supprimer</a></td>
	</tr>
</c:forEach>
</table>
</c:otherwise>
</c:choose>
</body>
</html>