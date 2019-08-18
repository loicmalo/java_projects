<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Création d'un client</title>
<link type="text/css" rel="stylesheet" href="inc/style.css" />
</head>
<body>
	<c:import url="/inc/menu.jsp"></c:import>
	<div>
		<fieldset>
			<legend>Informations client</legend>
			<form method="post" action="creationClient" enctype="multipart/form-data">
				<c:import url="/inc/inc_client_form.jsp"></c:import>
				<input type="submit" value="Valider" />
				 <input type="reset"
					value="Remettre à zéro" /> <br />
					<p class="error">${form.message }</p>
			</form>
		</fieldset>
	</div>
</body>
</html>