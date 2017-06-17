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
	
	
	stopaPDVaservice.findStopu = function(id) {

		return $http({
			method : 'GET',
			url : 'api/stope/nadjiStopu/'+id
		});
	}

	stopaPDVaservice.removeStopu = function(id) {
		return $http({
			method : 'DELETE',
			url : 'api/stope/obrisiStopu/' + id
		})
	}

	stopaPDVaservice.updateStopu = function(stopa) {

		return $http({
			method : 'POST',
			url : 'api/stope/izmeniStopu/',
			data : stopa
		});
	}
	
	
	return stopaPDVaservice;
	
	
	
});