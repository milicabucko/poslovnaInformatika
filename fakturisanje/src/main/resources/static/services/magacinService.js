app.factory('magacinService', function magacinService($http){
	
	magacinService.findBySifra = function(sifra) {
		return $http({
			method: "GET",
			url: 'api/magacin/findBySifra/' + sifra
		});
	}
	
	magacinService.findByPreduzecePib = function(preduzecePib) {
		return $http({
			method: "GET",
			url: 'api/magacin/findByPreduzece/' + preduzecePib
		});
	}
	
	magacinService.findBySifraContaining = function(sifra){
		return $http({
			method: 'GET',
			url: 'api/magacin/findBySifraContaining/' + sifra
		});
	}
	
	magacinService.dodajMagacin = function(sifraMagacina, nazivMagacina, firmaId) {
		return $http({
			method: "POST",
			url: 'api/magacin/dodajMagacin/' + firmaId,
			data: {
				"sifra" : sifraMagacina,
				"naziv" : nazivMagacina
			}
		});
	}
	
	magacinService.obrisiMagacin = function(sifraMagacina, nazivMagacina, firmaId) {
		return $http({
			method: "POST",
			url: 'api/magacin/obrisiMagacin/' + firmaId,
			data: {
				"sifra" : sifraMagacina,
				"naziv" : nazivMagacina
			}
		});
	}
	
	magacinService.lagerMagacina = function(sifraMagacina, firmaId){
		return $http({
			method: "GET",
			url: 'api/magacin/lager/' + sifraMagacina + '/' + firmaId
		});
	}
	
	magacinService.magacinskaKartica = function(karticaId){
		return $http({
			method: "GET",
			url: 'api/magacin/kartica/' + karticaId
		});
	}
	
	magacinService.sviMagacini = function() {
		return $http({
			method: "GET",
			url: 'api/magacin/sviMagacini'
		});
	}
	
	magacinService.nadjiMagacinskuKarticuArtikla = function(magacinId, artikalSifra) {
		return $http({
			method: "GET",
			url: 'api/magacinskaKartica/nadjiKarticuArtikla/' + magacinId + '/' + artikalSifra
		});
	}
	
	magacinService.dodajAnalitikuMK = function(artikalId, pib , pib2, stavkaId) {
		return $http({
			method: "POST",
			url: 'api/amk/addAMK/' + artikalId + '/' + pib + '/' + pib2 + '/' + stavkaId,
			data: {
				"smer" : "I",
				"vrstaPrometa" : "FO"
			}
		});
	}
	
	magacinService.dodajAnalitikuMKPrimka = function(artikalId, pib , pib2, stavkaId) {
		return $http({
			method: "POST",
			url: 'api/amk/addAMK/' + artikalId + '/' + pib + '/' + pib2 + '/' + stavkaId,
			data: {
				"smer" : "I",
				"vrstaPrometa" : "PR"
			}
		});
	}
	
	
	return magacinService;
});