<c:set var="client" value="${commande.client }" scope="request"/>
<label for="nom">Nom <span class="requis">*</span></label>
 <input
	type="text" id="nom" name="nom" value="<c:out value="${client.nom }"/>" size="20"
	maxlength="20" /> 
	<span class="error">${form.errors['nom'] }</span><br />

	 <label for="prenom">Prénom </label>
	  <input
	type="text" id="prenom" name="prenom" value="<c:out value="${client.prenom }"/>" size="20"
	maxlength="20" /> 
	<span class="error">${form.errors['prenom']}</span><br />
	
	 <label for="adresse">Adresse
	de livraison <span class="requis">*</span>
</label>
 <input type="text" id="adresse" name="adresse" value="<c:out value="${client.adresse }"/>" maxlength="80"/> 
	<span class="error">${form.errors['adresse'] }</span><br />
	
	<label for="telephone">Numéro
	de téléphone <span class="requis">*</span>
</label>
<input type="text" id="telephone" name="telephone"
	value="<c:out value="${client.telephone }"/>" size="20" maxlength="20" /> 
	<span class="error">${form.errors['telephone'] }</span><br />
	
	<label for="email">Adresse
	email</label> <input type="email" id="email" name="email" value="<c:out value="${client.email }"/>"
	size="20" maxlength="60" /> 
	<span class="error">${form.errors['email'] }</span><br />
	
	<label for="image">Image</label>
	<div>
		<input type="file" id="image" name="image" value="<c:out value="${client.image }"/>"/>

	</div>
	<span class="error">${form.errors['image']}</span><br/>

