/*Gestisce eccezioni dovute alla mancata creazione delle tabelle nel db*/
//
//function findGetParameter(parameterName) {
//    var result = null,
//        tmp = [];
//    location.search
//        .substr(1)
//        .split("&")
//        .forEach(function (item) {
//          tmp = item.split("=");
//          if (tmp[0] === parameterName) result = decodeURIComponent(tmp[1]);
//        });
//    return result;
//}
//
//$(document).ready(function(){
//	codiceErrore = findGetParameter("codError");
//	if (codiceErrore == "1") {
//		alert("Le risorse richieste non sono ancora disponibili");
//	}
//});


	$(document).ready(function(){
		codiceErrore =window.location.search.substr(1);
		if (codiceErrore == "codErr=1") {
			alert("Le risorse richieste non sono ancora disponibili");
		}else if (codiceErrore == "codErr=3"){
			alert("Errore! Nella galleria sono presenti opere senza autore");
		}
	});