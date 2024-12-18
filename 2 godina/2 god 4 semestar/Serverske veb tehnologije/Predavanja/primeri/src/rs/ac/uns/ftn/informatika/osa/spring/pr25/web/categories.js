$(document).ready(function() {
	
	// Kada se ucita stranica, pokupimo listu kategorija sa servera i popunimo tabelu
	$.ajax({
		url: "http://localhost:8080/pr25/api/categories",
		dataType: "json",
		success: function(response) {
			for(var i=0; i<response.length; i++) {
				category = response[i];

				// Za svaki video kreiramo po jedan <tr> element u tebeli
				var categoryTr = $("<tr></tr>");
				// <td>  sa klasom 'orderNumber' za redni broj kategorije
				var idTd = $("<td></td>");
				idTd.addClass("orderNumber");
				idTd.text(category.id);
				categoryTr.append(idTd);
				// <td> sa klasom 'categoryName' za ime kategorije
				var titleTd = $("<td></td>");
				titleTd.addClass("categoryName");
				titleTd.text(category.name);
				categoryTr.append(titleTd);
				$("#categoryTable").append(categoryTr);
			}
		},
		error: function(request, options, error) {
			alert(error);
		}
	});


});