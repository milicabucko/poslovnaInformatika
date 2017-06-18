app.factory('artikalService', function artikalService($http){
	
	artikalService.findBySifra = function(sifra){
		return $http({
			method: 'GET',
			url: 'api/artikal/findBySifra/' + sifra
		});
	}
	
	artikalService.nadjArtikleFirme = function(companyId){
		return $http({
			method: 'GET',
			url: 'api/artikal/findByCompanyId/' + companyId
		});
	}
	
	artikalService.getAllArtikle = function(id){
		return $http({
			method: 'GET',
			url: 'api/artikal/findByGrupaId/' + id
		});
	}
	
	artikalService.getGrupuArtikla = function(artikalId){
		return $http({
			method: 'GET',
			url: 'api/artikal/findByArtikal/' + artikalId
		});
	}
	
	artikalService.createArtikal = function(artikal, idGrupe, idJedinice){
		return $http({
			method: 'POST',
			url: 'api/artikal/kreirajArtikal/?idGrupe='+idGrupe+'&idJedinice='+idJedinice,
			data : artikal
		});
	}
	
	artikalService.nadjiSveArtikle = function(){
		return $http({
			method: 'GET',
			url: 'api/artikal/nadjiSve/' 
		});
	}
	
	artikalService.updateArtikal = function(artikal, idGrupe, idJedinice){
		return $http({
			method: 'POST',
			url: 'api/artikal/promeniArtikal/',
			data : artikal
		});
	}
	
	artikalService.deleteArtikal = function(id){
		return $http({
			method: 'DELETE',
			url: 'api/artikal/obrisiArtikal/'+id
		});
	}
	
	
	
	
	return artikalService;
	
});