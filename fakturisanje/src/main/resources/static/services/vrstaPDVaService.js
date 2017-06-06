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
	
	
	return vrstaPDVaService;
	
	
	
});