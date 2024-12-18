var locale = "sr"

$(document).ready(function() {
	popuniBaseURL() // dobavi i ugradi u HTML informaciju o baseURL-u
	//var imgLocation = $("#slikaPismo").src();
	//$("#slikaPismo").src(baseURL+imgLocation);
	
	$("#locales").change(function() {
				var selectedOption = $('#locales').val();
				
				if (selectedOption != '') {
					// parametri zahteva
					var params = {
						locale: selectedOption
					}
					
					$.post("Locale/ChangeLocale", params, function(odgovor) { // POST zahtev
						console.log(odgovor)
				
						if (odgovor.status == "ok" || odgovor.status == "odbijen") {
							$("#locale").text(selectedOption);
						} else if (odgovor.status == "greska") {
							pasusGreska.text(odgovor.poruka)  // ispis poruke o greški
						}
					})
					
					console.log("POST: " + "Locale/ChangeLocale");
				}
			});
	$("#podnozje").draggable();
});