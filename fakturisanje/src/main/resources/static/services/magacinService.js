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
	
	return magacinService;
});