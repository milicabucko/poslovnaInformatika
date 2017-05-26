app.factory('artikalService', function artikalService($http){
	
	artikalService.findBySifra = function(sifra){
		return $http({
			method: 'GET',
			url: 'api/artikal/findBySifra/' + sifra
		});
	}
	
	return artikalService;
	
});