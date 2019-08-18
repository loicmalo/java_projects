<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Test</title>
	</head>
	<body>
		<c:import url="test.xsl" varReader="xslFile" >
		<c:import url="monDocument.xml" varReader="xmlFile">
			<x:transform doc="${xmlFile }" xslt="${xslFile }">
				<x:param name="couleur" value="orange"></x:param>
			</x:transform>
			
			
		</c:import>
		</c:import>

	</body>
</html>