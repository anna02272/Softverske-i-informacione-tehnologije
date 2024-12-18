function popuniKorisnike() {
	// traži od server-a sve filmove
	$.get("korisnici/listaKorisnika", function(odgovor) { // GET zahtev
		console.log(odgovor)

		if (odgovor.status == "ok") {
			var celija = $("form").find("select[name=idKorisnika]")
			celija.empty() // ukloni sve checkbox-ove

			var korisnici = odgovor.korisnici
			for (var itKorisnik in korisnici) { // za svakog korisnika
				celija.append( // kreiraj, popuni checkbox i dodaj ga u celiju
					'<option value="' + korisnici[itKorisnik].id + '">' + 
							korisnici[itKorisnik].ime + ' ' + korisnici[itKorisnik].prezime + ' (' + korisnici[itKorisnik].email + ')' +
					'</option>'
				)
			}
		}
	})
}


$(document).ready(function() {

	popuniKorisnike()
	
	// keširanje referenci na elemente
	var registarskiBrojInput = $("input[name=registarskiBroj]")
	var idKorisnikaCelija = $("select[name=idKorisnika]")
	
	function dodaj() {
		// čitanje parametara forme za dodavanje
		var registarskiBroj = registarskiBrojInput.val()
		var idKorisnika = idKorisnikaCelija.find("option:selected").val()
	
		// parametri zahteva
		var params = {
			registarskiBroj : registarskiBroj,
			idKorisnika: idKorisnika
		}
		console.log(params)
		$.post("clanskeKarte/add", 
				params,
				function(){
				window.location.href = 'clanskeKarte'
				}
		)
		console.log("POST: " + "clanskeKarte/add")
		
		return false // sprečiti da submit forme promeni stranicu
	}
	
	$("form").submit(dodaj) // registracija handler-a za dodavanje
})