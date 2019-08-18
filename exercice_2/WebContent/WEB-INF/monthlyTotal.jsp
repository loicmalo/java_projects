<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Total par mois</title>
</head>
<body>
<h1>Total par mois</h1>
<a href="<c:url value="/liste"/>">Retour</a><br>

<table>
<c:forEach items="${monthlyTotal }" var="month">
<tr><td><c:out value="${month.key }"/></td><td><c:out value="${month.value }"/></td></tr>
</c:forEach>
</table>
</body>
</html>