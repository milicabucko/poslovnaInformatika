app.factory('artikalService', function artikalService($http){
	
	artikalService.findByNazivContaining = function(naziv){
		return $http({
			method: 'GET',
			url: 'api/artikal/findByNazivContaining/' + naziv
		});
	}
	
	return artikalService;
	
});