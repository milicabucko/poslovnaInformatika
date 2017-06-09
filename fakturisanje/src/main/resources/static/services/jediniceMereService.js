app.factory('jediniceMereService', function jediniceMereService($http){
	
	jediniceMereService.createJedinicu = function(jedinica){
		
		return $http({
			method: 'POST',
			url: 'api/jedinice/kreirajJedinicu/',
			data: jedinica
		});
	}
	
	jediniceMereService.getAllJedinice = function(id){
		
		return $http({
			method: 'GET',
			url: 'api/jedinice/nadjiSveJedinice/'+id
		});
	}
	
	
	return jediniceMereService;
	
	
	
});