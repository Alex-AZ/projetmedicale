function randomPass() {
    let generatedPass = "";

    let longPass = Math.round(Math.random() * 10) + 10;

    let characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789?*@=#";

    for (let i = 0; i < longPass; i++) {
        let generatedCharacterIndex = Math.round(Math.random() * characters.length);
        let generatedCharacter = characters.charAt(generatedCharacterIndex);
        generatedPass += generatedCharacter;
    }

    return generatedPass;
}

function createRandomUsername(){
	let valeurNbre = Math.round(Math.random() * 900) + 100;
	let nom = document.getElementById('nom').value; 
	document.getElementById('username').value = nom.toLowerCase()+ '.'+valeurNbre;
}

function calculProgress(){
	let list = document.getElementsByTagName("input");
    let percent = 0;
    if (document.getElementById('nom').value.length > 0) {
		percent += 20; 
    }

	if (document.getElementById('username').value.length > 0) {
		percent += 20; 
    }

	if (document.getElementById('photouser').value.length > 0) {
		percent += 20; 
    }

	if (document.getElementById('email').value.length > 0) {
		percent += 20; 
    }

	if (document.getElementById('password').value.length > 0) {
		percent += 20; 
    }

	document.getElementById('progressbar-inner').style.width = percent + '%'; 
	document.getElementById('progressbar-inner').innerHTML = percent + '%';

	//console.log( percent );
	return false;
}

function checkMyForm(){
	let myPass = document.getElementById('password').value; // Abcde
	let containNumber = false;
	let containUppercaseLetter = false;
	let characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	if( myPass.length < 5 ){
		alert("Le mot de passe doit contenir au moins 5 caractères");
		return false;
	}
	
	for( let i = 0; i < 10; i++ ){
		console.log( "je cherche " + i + " dans " + myPass );
		if( myPass.indexOf(i) != -1 ){
			console.log( i + " est bien trouvé " + myPass );
			containNumber = true;
			break;
		} 
	}
	
	if( !containNumber ){
		alert("Le mot de passe doit contenir un nombre");
		return false;
	}
	
	for( let i = 0; i < characters.length; i++ ){
		let valRecherchee = characters.charAt(i);
		console.log( "je cherche " + valRecherchee + " dans " + myPass );
		if( myPass.indexOf( valRecherchee ) != -1 ){
			console.log( valRecherchee + " est bien trouvé " + myPass );
			containUppercaseLetter = true;
			break;
		} 
	}
	
	if( !containUppercaseLetter ){
		alert("Le mot de passe doit contenir une lettre majuscule");
		return false;
	}
	
	return true;
}


function checkPatient() {
	let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {
        // instructions of anonymous function
        if (xhr.readyState == 4 && xhr.status == 200) {
            if( xhr.responseText == "true" ){ // cas d'erreur
            	document.getElementById('message').innerHTML = '<div class="alert alert-danger">Patient existe déjà, veuillez vérifier votre saisie..</div>';
            	document.getElementById('submitbtn').disabled = true;
            }else{ // sans erreur 
            	document.getElementById('message').innerHTML = '';
            	document.getElementById('submitbtn').disabled = false;
            }
        }
    };

    let emailValue = document.getElementById('email').value;
    xhr.open("GET", "/patient/check?email="+emailValue, true);
    xhr.send();
}


