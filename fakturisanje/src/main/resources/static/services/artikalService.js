app.factory('artikalService', function artikalService($http){
	
	artikalService.findBySifra = function(sifra){
		return $http({
			method: 'GET',
			url: 'api/artikal/findBySifra/' + sifra
		});
	}
	
	artikalService.getAllArtikle = function(id){
		return $http({
			method: 'GET',
			url: 'api/artikal/findByGrupaId/' + id
		});
	}
	
	artikalService.createArtikal = function(artikal, idGrupe, idJedinice){
		return $http({
			method: 'POST',
			url: 'api/artikal/kreirajArtikal/?idGrupe='+idGrupe+'&idJedinice='+idJedinice,
			data : artikal
		});
	}
	
	return artikalService;
	
});