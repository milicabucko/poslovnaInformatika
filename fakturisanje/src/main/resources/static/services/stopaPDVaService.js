app.factory('stopaPDVaService', function stopaPDVaservice($http){
	
	stopaPDVaservice.createStopu = function(stopa, id){
		
		return $http({
			method: 'POST',
			url: 'api/stope/kreirajStopu/',
			data: stopa
		});
	}
	
	stopaPDVaservice.getAllStope = function(){
		
		return $http({
			method: 'GET',
			url: 'api/stope/nadjiSveStope/'
		});
	}
	
	
	return stopaPDVaservice;
	
	
	
});