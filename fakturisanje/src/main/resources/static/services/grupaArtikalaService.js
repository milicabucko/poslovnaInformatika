app.factory('grupaArtikalaService', function grupaArtikalaService($http){
	
	grupaArtikalaService.createGrupu = function(grupa, id, vrstaId){
		
		return $http({
			method: 'POST',
			url: 'api/grupe/kreirajGrupu/?id='+id+'&vrstaId='+vrstaId,
			data: grupa
		});
	}
	
	grupaArtikalaService.getAllGrupe = function(id){
		
		return $http({
			method: 'GET',
			url: 'api/grupe/nadjiSveGrupe/'+id
		});
	}
	
	grupaArtikalaService.findGrupu = function(id){
		return $http({
			method: 'GET',
			url: 'api/grupe/nadjiGrupu/'+id
		});
	}
	
	grupaArtikalaService.deleteGrupu = function(id){
		return $http({
			method: 'DELETE',
			url: 'api/grupe/obrisiGrupu/'+id
		});
	}
	
grupaArtikalaService.updateGrupu =  function(grupa, vrstaId){
		
		return $http({
			method: 'POST',
			url: 'api/grupe/promeniGrupu/'+vrstaId,
			data: grupa
		});
	}
	
	
	return grupaArtikalaService;
	
	
	
});