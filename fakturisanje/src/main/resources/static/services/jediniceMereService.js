app.factory('jediniceMereService', function jediniceMereService($http){
	
	jediniceMereService.createJedinicu = function(jedinica, idCompany){
		
		return $http({
			method: 'POST',
			url: 'api/jedinice/kreirajJedinicu/'+ idCompany,
			data: jedinica
		});
	}
	
	jediniceMereService.findJedinicu = function(id){
		return $http({
			method: 'GET',
			url: 'api/jedinice/nadjiJednu/' + id
		});	
		}
	
	jediniceMereService.getAllJedinice = function(id){
		
		return $http({
			method: 'GET',
			url: 'api/jedinice/nadjiSveJedinice/'+id
		});
	}
	
	jediniceMereService.deleteJedinica = function(id){
		return $http({
			method:'DELETE',
			url: 'api/jedinice/obrisiJedinicu/'+id
		});
	}
	
	jediniceMereService.updateJedinicu = function(jedinica){
		return $http({
			method: 'POST',
			url: 'api/jedinice/promeniJedinicu/',
			data : jedinica
		});
	}
	
	
	return jediniceMereService;
	
	
	
});