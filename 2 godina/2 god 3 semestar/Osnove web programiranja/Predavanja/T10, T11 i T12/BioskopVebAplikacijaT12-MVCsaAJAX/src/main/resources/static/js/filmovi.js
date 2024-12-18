$(document).ready(function() {
	popuniBaseURL() // dobavi i ugradi u HTML informaciju o baseURL-u
	
	
	// keširajne referenci na elemente
	var tabela = $("table.tabela")
	var nazivInput = tabela.find("input[name=naziv]")
	var zanrIdSelect = tabela.find("select[name=zanrId]")
	var trajanjeOdInput = tabela.find("input[name=trajanjeOd]")
	var trajanjeDoInput = tabela.find("input[name=trajanjeDo]")

	function popuniFilmove() {
		// čitanje parametara forme za pretragu
		var naziv = nazivInput.val()
		var zanrId = zanrIdSelect.find("option:selected").val()
		var trajanjeOd = trajanjeOdInput.val()
		var trajanjeDo = trajanjeDoInput.val()

		// parametri zahteva
		var params = {
			naziv: naziv, 
			zanrId: zanrId,
			trajanjeOd: trajanjeOd,
			trajanjeDo: trajanjeDo
		}
		console.log(params)
		$.get(baseURL+"Filmovi/Search", params, function(odgovor) {
			console.log(odgovor)
			
			if (odgovor.status == "ok") {
				tabela.find("tr:gt(1)").remove() // ukloni sve redove u tabeli iza forme za pretragu
				
				var filmovi = odgovor.filmovi 
				for (var itFilm in filmovi) {
					tabela.append( // kreiraj, popuni red i dodaj ga u tabelu
						'<tr>' + 
							'<td class="broj">' + (parseInt(itFilm) + 1) + '</td>' + 
							'<td><a href="film.html?id=' + filmovi[itFilm].id + '">' + filmovi[itFilm].naziv + '</a></td>' + 
							'<td><ul></ul></td>' + // lista u redu
							'<td class="broj">' + filmovi[itFilm].trajanje + '</td>' + 
							'<td>' + 
								'<a href="projekcije.html?filmId=' + filmovi[itFilm].id + '">projekcije</a>' + 
							'</td>' + 
						'</tr>'
					)
					var lista = tabela.find("tr").eq(parseInt(itFilm) + 2).find("ul") // pronađi listu u redu

					var zanroviFilma = filmovi[itFilm].zanrovi
					for (var itZanr in zanroviFilma) { // kreiraj stavke u listi i dodaj ih u listu
						lista.append(
								'<li><a href="zanr.html?id=' + zanroviFilma[itZanr].id + '">' + zanroviFilma[itZanr].naziv + '</a></li>'
						)
					}
				}
			}
		})
		console.log("GET: " + "Filmovi")
	}

	
	$("form").submit(function() { // registracija handler-a za pretragu
		popuniFilmove()

		return false // sprečiti da submit forme promeni stranicu
	})
})