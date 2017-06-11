app.factory('poslovnaGodinaService', function poslovnaGodinaService($http){
	
	poslovnaGodinaService.sacuvajGodinu = function(firmaId, datumPV, datumKV, brojGodine) {
		return $http({
			method: 'POST',
			url: 'api/poslovnaGodina/sacuvajGodinu/' + firmaId,
			data: {
				"datumPocetka" : datumPV,
				"datumZavrsetka" : datumKV,
				"brojGodine" : brojGodine
			}
		});
	}
	
	return poslovnaGodinaService;
});