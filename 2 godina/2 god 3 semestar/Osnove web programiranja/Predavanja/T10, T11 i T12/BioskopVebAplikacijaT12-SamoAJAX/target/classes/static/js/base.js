var baseURL = ""

function popuniBaseURL() {
	// traži od servera baseURL
	$.get("baseURL", function(odgovor) { // GET zahtev
		console.log(odgovor)

		if (odgovor.status == "ok") {
			baseURL = odgovor.baseURL // inicjalizuj globalnu promenljivu baseURL
			$("base").attr("href", baseURL) // postavi href atribut base elementa
		}
	})
	console.log("GET: " + "baseURL")
}

function odjava() {
	// pošalji server-u zahtev za odjavu
	$.get("Korisnici/Logout", function(odgovor) { // GET zahtev
		console.log(odgovor)

		if (odgovor.status == "ok") {
			// odjava uspela
			window.location.replace(baseURL) // klijent-side redirekcija na početnu stranicu
		}
	})
	console.log("GET: " + "Korisnici/Logout")

	return false // sprečiti da link za odjavu promeni stranicu
}