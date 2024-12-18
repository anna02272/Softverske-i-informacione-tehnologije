$(document).ready(function() {
	popuniBaseURL() // dobavi i ugradi u HTML informaciju o baseURL-u

	// keširanje referenci na elemente
	var forma = $(this)
	var korisnickoImeInput = forma.find("input[name=korisnickoIme]")
	var lozinkaInput = forma.find("input[name=lozinka]")

	var pasusGreska = $("p.greska")

	forma.submit(function() { // registracija handler-a za prijavu
		// čitanje parametara forme za prijavu
		var korisnickoIme = korisnickoImeInput.val()
		var lozinka = lozinkaInput.val()

		// parametri zahteva
		var params = {
			korisnickoIme: korisnickoIme, 
			lozinka: lozinka
		}
		console.log(params)
		$.post("Korisnici/Login", params, function(odgovor) { // POST zahtev
			console.log(odgovor)
	
			if (odgovor.status == "ok") {
				// prijava uspela
				window.location.replace(baseURL) // client-side redirekcija na početnu stranicu
			} else if (odgovor.status == "greska") {
				pasusGreska.text(odgovor.poruka) // ispis poruke o greški
			}
		})
		console.log("POST: " + "Korisnici/Login")
	
		return false // sprečiti da submit forme promeni stranicu
	})
})