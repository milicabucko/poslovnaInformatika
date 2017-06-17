app.factory('vrstaPDVaService', function vrstaPDVaService($http){
	
	vrstaPDVaService.createVrstu = function(vrsta, id){
		
		return $http({
			method: 'POST',
			url: 'api/vrste/kreirajVrstu/'+id,
			data: vrsta
		});
	}
	
	vrstaPDVaService.getAllVrste = function(){
		
		return $http({
			method: 'GET',
			url: 'api/vrste/nadjiSveVrste/'
		});
	}
	
	vrstaPDVaService.findVrstu = function(id){
		return $http({
			method: 'GET',
			url: 'api/vrste/nadjiVrstu/'+id
		});
	}
	
	vrstaPDVaService.removeVrstu = function(id) {
		return $http({
			method : 'DELETE',
			url : 'api/vrste/obrisiVrstu' + id
		})
	}

	vrstaPDVaService.updateVrstu = function(vrsta, id) {

		return $http({
			method : 'POST',
			url : 'api/vrste/izmeniVrstu/'+id,
			data : vrsta
		});
	}
	
	return vrstaPDVaService;
	
	
	
});