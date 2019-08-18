<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des tickets</title>

</head>
<body>
<h1>Liste des tickets de caisse</h1>
<a href="<c:url value="/ticket"/>">Ajouter un ticket de caisse</a><br>
<a href="<c:url value="/total"/>">Voir le total par mois</a>

<c:forEach items="${listReceipts}" var="receipts">
	<p> <c:out value="${receipts.value.name }"/> - <c:out value="${receipts.value.amount }"/> - <c:out value="${receipts.value.date }"/><br>
		<a href="<c:url value="/ticket">
				<c:param name="id" value="${receipts.value.id }"/>
				
			</c:url>">Modifier</a>
		 - 
		 <a href="<c:url value="/supprimer">
		 		<c:param name="id" value="${receipts.value.id}"/>
	 		</c:url>">
		 Supprimer</a> 
	</p>
</c:forEach>


<p>Total : <c:out value="${total}"/> €</p>

</body>
</html>