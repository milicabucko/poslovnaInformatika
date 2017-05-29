app.factory('stavkaDokumentaService', function stavkaDokumentaService($http){
	
	
	stavkaDokumentaService.sacuvajStavku = function(fakturaId, artikalId, kolicina){
		return $http({
			method: 'POST',
			url: 'api/stavkaDokumenta/sacuvajStavku/' + fakturaId + '/' + artikalId,
			data: {
				"kolicina" : kolicina
			}
		});
	}
	
	return stavkaDokumentaService;
	
});