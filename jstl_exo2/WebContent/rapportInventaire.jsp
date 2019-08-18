<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Test</title>
	</head>
	<body>
	
		<c:import url="inventaire.xml" varReader="xmlFile">
			<x:parse var="doc" doc="${xmlFile }"></x:parse>
			
			<x:forEach var="livre" select="$doc/inventaire/livre">
				<x:out select="$livre/titre"/><br>
				<x:if select="$livre[stock < minimum]">
					<p>Alerte stock !</p>
				</x:if>
			</x:forEach>
		</c:import>

	</body>
</html>