function popuniPrijavljenogKorisnika() {
	// traži od servera prijavljenog korisnika
	$.get("Korisnici/PrijavljeniKorisnik", function(odgovor) { // GET zahtev
		console.log(odgovor)

		if (odgovor.status == "ok") {
			var prijavljeniKorisnik = odgovor.prijavljeniKorisnik
			if (prijavljeniKorisnik != null) {
				// prijavljen
				var korisnikLink = $("table.korisnik:first").find("a:first")
				korisnikLink.attr("href", korisnikLink.attr("href") + prijavljeniKorisnik.korisnickoIme) // dodavanje korisničkog imena na href link-a do korisnika
				korisnikLink.text(prijavljeniKorisnik.korisnickoIme)
				
				$("table.korisnik:last").hide() // sakrij tabelu za neprijavljenog korisnika
			} else {
				// neprijavljen
				$("table.korisnik:first").hide() // sakrij tabelu za prijavljenog korisnika
			}
			if (!(prijavljeniKorisnik != null && prijavljeniKorisnik.administrator == true)) {
				// nije (prijavljen i administrator)
				$('a[href="korisnici.html"]').parent().hide() // sakrij list item koji obuhvata link do korisnici.html
			}
		}
	})
	console.log("GET: " + "Korisnici/PrijavljeniKorisnik")
}

$(document).ready(function() {
	popuniBaseURL() // dobavi i ugradi u HTML informaciju o baseURL-u
	popuniPrijavljenogKorisnika() // dobavi i ugradi u HTML informaciju o prijavljenom korisniku

	$("table.korisnik:first a:last").click(odjava) // registracija handler-a za odjavu
	$("body").show() // prikazi stanicu nakon sto se preuzmu podaci
})