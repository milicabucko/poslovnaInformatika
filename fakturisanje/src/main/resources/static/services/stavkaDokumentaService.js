app.factory('stavkaDokumentaService', function stavkaDokumentaService($http){
	
	
	stavkaDokumentaService.sacuvajStavku = function(fakturaId, artikalId){
		return $http({
			method: 'POST',
			url: 'api/stavkaDokumenta/sacuvajStavku/' + fakturaId + '/' + artikalId,
			data: {
				"id" : null
			}
		});
	}
	
	return stavkaDokumentaService;
	
});