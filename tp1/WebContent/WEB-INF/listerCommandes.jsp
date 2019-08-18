<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des commandes</title>
<link type="text/css" rel="stylesheet" href="inc/style.css" />
</head>
<body>
<c:import url="/inc/menu.jsp"></c:import>

<c:choose>
<c:when test="${empty sessionScope.listCommandes }">
	<p class="erreur">Aucune commande enregistrée</p>
</c:when>
<c:otherwise>
<table>
    <tr>
        <th>Client</th>
        <th>Date</th>
        <th>Montant</th>
        <th>Mode de paiement</th>
        <th>Statut de paiement</th>
        <th>Mode de livraison</th>
        <th>Statut de livraison</th>
        <th class="action">Action</th>                    
    </tr>
<c:forEach items="${listCommandes }" var="commande" varStatus="boucle">
	<tr class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
		<td><c:out value="${commande.value.client.prenom }"></c:out> <c:out value="${commande.value.client.nom }"></c:out></td>
		<td><c:out value="${commande.value.date }"></c:out></td>
		<td><c:out value="${commande.value.montant }"></c:out></td>
		<td><c:out value="${commande.value.modePaiement }"></c:out></td>
		<td><c:out value="${commande.value.statutPaiement }"></c:out></td>
		<td><c:out value="${commande.value.modeLivraison }"></c:out></td>
		<td><c:out value="${commande.value.statutLivraison }"></c:out></td>
		<td><a href="<c:url value="/suppressionCommande">
				<c:param name="date" value="${commande.value.date }"/>
			</c:url>">Supprimer</a></td>
	</tr>
</c:forEach>
</table>
</c:otherwise>
</c:choose>
</body>
</html>