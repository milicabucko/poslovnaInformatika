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
	
	
	return grupaArtikalaService;
	
	
	
});