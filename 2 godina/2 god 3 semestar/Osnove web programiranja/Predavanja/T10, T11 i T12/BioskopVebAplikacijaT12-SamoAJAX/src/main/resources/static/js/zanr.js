var id = window.location.search.slice(1).split('&')[0].split('=')[1];

$(document).ready(function() {
	popuniBaseURL() // dobavi i ugradi u HTML informaciju o baseURL-u

	$("table.korisnik:first a:last").click(odjava) // registracija handler-a za odjavu

	var formaIzmena = $("form:first")
	var formaBrisanje = $("form:last")
	var tabelaObicanPrikaz = $("table.forma").eq(2)
	
	var nazivInput = formaIzmena.find("input[name=naziv]")

	var params = {
		id: id
	}
	console.log(params)
	$.get("Zanrovi/Details", params, function(odgovor) {
		console.log(odgovor)

		if (odgovor.status == "ok") {
			var zanr = odgovor.zanr

			nazivInput.val(zanr.naziv)
			var linkFilmovi = formaIzmena.find("a")
			linkFilmovi.attr("href", linkFilmovi.attr("href") + zanr.id)
			
			tabelaObicanPrikaz.find("tr").eq(0).find("td").text(zanr.naziv)
			var linkFilmovi = tabelaObicanPrikaz.find("a")
			linkFilmovi.attr("href", linkFilmovi.attr("href") + zanr.id)
		}
	})
	
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
					
					$("table.korisnik:first").show() // prikazi tabelu za prijavljenog korisnika
					$("table.korisnik:last").hide() // sakrij tabelu za neprijavljenog korisnika
				} else {
					// neprijavljen
					$("table.korisnik:first").hide() // sakrij tabelu za prijavljenog korisnika
					$("table.korisnik:last").show() // prikazi tabelu za neprijavljenog korisnika
				}
				if (!(prijavljeniKorisnik != null && prijavljeniKorisnik.administrator == true)) {
					// nije (prijavljen i administrator)
					$('a[href="korisnici.html"]').parent().hide() // sakrij list item koji obuhvata link do korisnici.html
				}

				if (prijavljeniKorisnik != null && prijavljeniKorisnik.administrator == true) {
					formaIzmena.show()
					formaBrisanje.show()
					tabelaObicanPrikaz.hide()
				} else {
					formaIzmena.hide()
					formaBrisanje.hide()
					tabelaObicanPrikaz.show()
				}
			}
		})
		console.log("GET: " + "Korisnici/PrijavljeniKorisnik")
	}

	formaIzmena.submit(function() {
		var naziv = nazivInput.val()
			
		var params = {
			id: id, 
			naziv: naziv
		}
		console.log(params)
		$.post("Zanrovi/Edit", params, function(odgovor) {
			console.log(odgovor)

			if (odgovor.status == "ok" || odgovor.status == "odbijen") {
				window.location.replace("zanrovi.html")
			} else if (odgovor.status == "greska") {
				pasusGreska.text(odgovor.poruka)
			}
		})
		console.log("POST: Zanrovi/Create")

		return false
	})
	formaBrisanje.submit(function() {		
		var params = {
			id: id
		}
		console.log(params)
		$.post("Zanrovi/Delete", params, function(odgovor) {
			console.log(odgovor)

			if (odgovor.status == "ok" || odgovor.status == "odbijen") {
				window.location.replace("zanrovi.html")
			}
		})
		console.log("POST: Zanrovi/Create")

		return false
	})

	popuniPrijavljenogKorisnika() // dobavi i ugradi u HTML informaciju o prijavljenom korisniku
	$("body").show() // prikazi stanicu nakon sto se preuzmu podaci
})