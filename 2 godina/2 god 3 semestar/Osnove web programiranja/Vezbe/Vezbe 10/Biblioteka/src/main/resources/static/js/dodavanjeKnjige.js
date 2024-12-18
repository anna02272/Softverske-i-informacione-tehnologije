
$(document).ready(function(){

	var nazivInput = $("input[name = naziv]");
	var registarskiBrojInput = $("input[name = registarskiBrojPrimerka]");
	var jezikInput = $("input[name = jezik]");
	var brStranicaInput = $("input[name = brojStranica]");
	var datumInput = $("input[name = datum]");

	function dodaj(){
		var naziv = nazivInput.val();
		var registarskiBrojPrimerka = registarskiBrojInput.val();
		var jezik = jezikInput.val();
		var brojStranica = brStranicaInput.val();
		var datum = datumInput.val();
		
		var params = {
			naziv: naziv,
			registarskiBrojPrimerka: registarskiBroj,
			jezik: jezik,
			brojStranica: brojStranica,
			datum: datum
		};
		
		$.post("knjige/add", params, function(){
			window.location.href = "knjige";
		});
		
		return false;
	}
	
	$("form").submit(dodaj)
});



