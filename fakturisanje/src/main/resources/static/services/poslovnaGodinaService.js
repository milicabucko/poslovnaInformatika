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
	
	poslovnaGodinaService.sveGodine = function() {
		return $http({
			method: 'GET',
			url: 'api/poslovnaGodina/sveGodine'
		});
	}
	
	poslovnaGodinaService.promenaStatusa = function(firmaId, brojGodine) {
		return $http({
			method: 'POST',
			url: 'api/poslovnaGodina/promenaStatusa/' + firmaId + '/' + brojGodine
		});
	}
	
	poslovnaGodinaService.pronadjiPoFirmi = function(firmaId){
		return $http({
			method: 'GET',
			url: 'api/poslovnaGodina/nadjiPoFirmi/' + firmaId
		});
	}
	
	return poslovnaGodinaService;
});