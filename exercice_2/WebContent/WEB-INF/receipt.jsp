<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Créer un ticket</title>
</head>
<body>
<h1>Créer un ticket</h1>
<a href="<c:url value="/liste"/>">Retour</a><br>

<form method="post" action="<c:url value="/ticket"/>">
	<c:if test="${receipt != null }">
		<input type="hidden" name="id" value="<c:out value="${receipt.id }"/>">
	</c:if>
	<span class="error">${form.errors['id']}</span>
	Date : <input type="date" name="date" value="<c:out value="${receipt.date }"/>">
	<span class="error">${form.errors['date'] }</span>
	<br><br>
	Intitulé : <input type="text" name="name" value="<c:out value="${receipt.name}"/>"> 
	<span class="error">${form.errors['name'] }</span>
	<br><br>
	Montant : <input type="number" name="amount" step="0.01" max="1000" value="<c:out value="${receipt.amount }"/>"> € 
	<span class="error">${form.errors['amount'] }</span>
	<br><br>
	<input type="submit" value="Valider"><br>
	<p class="error">${form.message }</p>
</form>
</body>
</html>