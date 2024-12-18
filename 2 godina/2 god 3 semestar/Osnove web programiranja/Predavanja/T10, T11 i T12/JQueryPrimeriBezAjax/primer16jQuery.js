$(document).ready(function(){

	$("#zona").change(function(){
		$("#dodataStavka").remove();
		if($(this).val()=='2'){
			// Create element with HTML code 
			var reddodataStavka = 
				$("<tr id='dodataStavka'> " +
					"<td> <label for='emailKlijenta'>Email klijenta</label></td>"+
					"<td> <input type='email' id='emailKlijenta' name='emailKlijenta'/></td>" +
				"</tr>");
			$("table#prva tr").last().before(reddodataStavka);
			$("#emailKlijenta").focus();
		}
	});

	$("#zona").change(function(){
		$("#dodataStavka2").remove();
		if($(this).val()=='3'){
			// Create element with jQuery code
			var labelBrojtelefona = $("<label></label>");
			labelBrojtelefona.attr('for', 'brTelefona');
			labelBrojtelefona.text('Broj telefona');
			var td1 = $("<td></td>");
			td1.append(labelBrojtelefona);

			var inputBrojtelefona = $("<input></input>");
			inputBrojtelefona.attr('id', 'brTelefona');
			inputBrojtelefona.attr('type', 'text');
			inputBrojtelefona.attr('name', 'brTelefona');
			var td2 = $("<td></td>");
			td2.append(inputBrojtelefona);


			var reddodataStavka2 = $("<tr id='dodataStavka2'></tr>");
			reddodataStavka2.append(td1);
			reddodataStavka2.append(td2);
			          
			$("table#prva tr").last().before(reddodataStavka2);
			$("#brTelefona").focus();
		}
    });
});