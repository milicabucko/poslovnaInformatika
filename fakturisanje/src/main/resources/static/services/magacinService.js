app.factory('magacinService', function magacinService($http){
	
	magacinService.findBySifra = function(sifra) {
		return $http({
			method: "GET",
			url: 'api/magacin/findBySifra/' + sifra
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
	
	return magacinService;
});