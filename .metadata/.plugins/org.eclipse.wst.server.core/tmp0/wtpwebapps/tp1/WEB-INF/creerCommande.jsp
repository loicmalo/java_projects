<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Création d'une commande</title>
<link type="text/css" rel="stylesheet" href="inc/style.css" />
</head>
<body>
	<c:import url="/inc/menu.jsp"></c:import>
	<div>
		<form method="post" action="creationCommande" enctype="multipart/form-data">
			<fieldset>
			<legend>Informations client</legend>
			<c:if test="${ !empty sessionScope.listClients}">
				<label for="nouveauClient">Nouveau client ? <span class="requis">*</span></label>
				<input type="radio" id="oui" name="nouveauClient" value="oui" checked>Oui
				<input type="radio" id="non" name="nouveauClient" value="non">Non<br><br>
			</c:if>
			
			<c:set var="client" value="${commande.client }" scope="request"/>
			
			<div id="client">
				<div id="nouveauClient">
					<c:import url="/inc/inc_client_form.jsp"></c:import>
				</div>
			</div>
			
			</fieldset>
			<fieldset>
				<legend>Informations commande</legend>

				<label for="date">Date <span class="requis">*</span></label>
				<input type="text" id="date" name="date" value="<c:out value="${commande.date }"/>"
					size="20" maxlength="20" disabled /> 
					<span class="error">${form.errors['date'] }</span><br />
					<label
					for="montant">Montant <span class="requis">*</span></label>
				<input type="text" id="montant" name="montant"
					value="<c:out value="${commande.montant }" />" size="20" maxlength="20" /> 
					<span class="error">${form.errors['montant'] }</span><br />
					 <label
					for="modePaiement">Mode de paiement <span
					class="requis">*</span></label> <input type="text"
					id="modePaiement" name="modePaiement" value="<c:out value="${commande.modePaiement }"/>"
					size="20" maxlength="20" /><span class="error">${form.errors['modePaiement'] }</span> <br />
					 <label
					for="statutPaiement">Statut du paiement</label> <input
					type="text" id="statutPaiement"
					name="statutPaiement" value="<c:out value="${commande.statutPaiement }"/>" size="20" maxlength="20" /><span class="error">${form.errors['statutPaiement'] }</span>
				<br />
				 <label for="modeLivraison">Mode de livraison
					<span class="requis">*</span>
				</label> <input type="text" id="modeLivraison"
					name="modeLivraison" value="<c:out value="${commande.modeLivraison }"/>" size="20" maxlength="20" /><span class="error">${form.errors['modeLivraison'] }</span>
				<br />
				<label for="statutLivraison">Statut de la
					livraison</label> <input type="text" id="statutLivraison"
					name="statutLivraison" value="<c:out value="${commande.statutLivraison }"/>" size="20" maxlength="20" /><span class="error">${form.errors['statutLivraison'] }</span>
				<br />

			</fieldset>
			<input type="submit" value="Valider" /> <input type="reset"
				value="Remettre à zéro" /> <br />
				<p class="error">${form.message }</p>
				<c:out value="${form.errors }"/> 
		</form>
	</div>
	<script type="text/javascript">
		const customerElt = document.getElementById("client");
		const newCustomerElt = document.getElementById("nouveauClient");
		const yesElt = document.getElementById("oui");
		const noElt = document.getElementById("non");
		const customers = new Array();
		<c:forEach items="${listClients}" var="client">
			clientDetails = new Object();
			clientDetails.firstName = "${client.value.nom}";
			clientDetails.lastName = "${client.value.prenom}";
			customers.push(clientDetails);
		</c:forEach>
		if(noElt !== null) {
			
			const selectElt = document.createElement("select");	
			selectElt.name = "client";
			const infoOptionElt = document.createElement("option");
			infoOptionElt.value = "";
			infoOptionElt.textContent = "Choisissez un client...";
			selectElt.appendChild(infoOptionElt);
			
			noElt.addEventListener("change", (e) => {
					customerElt.replaceChild(selectElt, newCustomerElt);
			});
			
			yesElt.addEventListener("change", (e) => {
				customerElt.replaceChild(newCustomerElt, selectElt);
			});
			
			const createOptionElt = (firstName, lastName) => {
				const optionElt = document.createElement("option");
				optionElt.value = firstName;
				optionElt.textContent = firstName + " " + lastName;
				
				return optionElt;
			}
			
			customers.forEach((customer) => {
				let optionElt = createOptionElt(customer.firstName, customer.lastName);
				selectElt.appendChild(optionElt);
			});
		}

		
	</script>
</body>
</html>