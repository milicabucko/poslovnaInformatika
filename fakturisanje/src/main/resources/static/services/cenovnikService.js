app.factory('cenovnikService', function cenovnikService($http){
	
	cenovnikService.kreirajCenovnik = function(idFirma,datumPV, datumKV){
		
		return $http({
			method: 'POST',
			url: 'api/cenovnik/kreirajCenovnik/' + idFirma,
			data: {
				"datumVazenjaPocetak" : datumPV,
				"datumVazenjaKraj" : datumKV,
			}
		});
	}
	
	
	return cenovnikService;
	
	
	
});