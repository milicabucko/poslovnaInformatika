app.factory('stavkaCenovnikaService', function stavkaCenovnikaService($http){
	
	
	stavkaCenovnikaService.sacuvajStavku = function(cenovnikId, artikalId, cena){
		return $http({
			method: 'POST',
			url: 'api/stavkaCenovnika/sacuvajStavku/' + cenovnikId + '/' + artikalId,
			data: {
				"cena" : cena
			}
		});
	}
	
	return stavkaCenovnikaService;
	
});