/**
 * Gestisce errori legati alla violazione dei vincoli
 */

	$(document).ready(function(){
		codiceErrore =window.location.search.substr(1);
		if (codiceErrore == "codErr=2") {
			alert("Errore: inserzione annullata causa violazione vincoli modello!");
		}
	});