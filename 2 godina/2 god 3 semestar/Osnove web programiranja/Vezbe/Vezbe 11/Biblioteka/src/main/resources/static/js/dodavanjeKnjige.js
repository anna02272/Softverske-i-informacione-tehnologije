$(document).ready(function() {

	// keširanje referenci na elemente
	var nazivInput = $("input[name=naziv]")
	var registarskiBrojPrimerkaInput = $("input[name=registarskiBrojPrimerka]")
	var jezikInput = $("input[name=jezik]")
	var brojStranicaInput = $("input[name=brojStranica]")
	var datumInput = $("input[name=datum]")
	
	function dodaj() {
		// čitanje parametara forme za dodavanje
		var naziv = nazivInput.val()
		var registarskiBrojPrimerka = registarskiBrojPrimerkaInput.val()
		var jezik = jezikInput.val()
		var brojStranica = brojStranicaInput.val()
		var datum = datumInput.val()

		// parametri zahteva
		var params = {
			naziv: naziv,
			registarskiBrojPrimerka : registarskiBrojPrimerka,
			jezik: jezik,
			brojStranica : brojStranica,
			datum : datum
		}
		console.log(params)
		$.post("knjige/add",
				params,
				function(){
					window.location.href = 'knjige'
				}
		)
		console.log("POST: " + "knjige/add")
		
		return false // sprečiti da submit forme promeni stranicu
	}
	
	$("form").submit(dodaj) // registracija handler-a za dodavanje
})