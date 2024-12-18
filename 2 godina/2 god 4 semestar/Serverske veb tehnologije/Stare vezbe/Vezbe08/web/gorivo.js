$(document).ready(function() {
	
	// Kada se ucita stranica, pokupimo listu kategorija sa servera i popunimo tabelu
	$.ajax({
		url: "http://localhost:8080/Vezbe08/api/goriva",
		dataType: "json",
		success: function(response) {
			for(var i=0; i<response.length; i++) {
				gorivo = response[i];

				// Za svaki video kreiramo po jedan <tr> element u tebeli
				var gorivoTr = $("<tr></tr>");
				// <td>  sa klasom 'orderNumber' za redni broj kategorije
				var idTd = $("<td></td>");
				idTd.addClass("orderNumber");
				idTd.text(gorivo.id);
				gorivoTr.append(idTd);
				// <td> sa klasom 'categoryName' za ime kategorije
				var titleTd = $("<td></td>");
				titleTd.addClass("gorivoTip");
				titleTd.text(gorivo.tipGoriva);
				gorivoTr.append(titleTd);
				$("#gorivoTable").append(gorivoTr);
			}
		},
		error: function(request, options, error) {
			alert(error);
		}
	});


});