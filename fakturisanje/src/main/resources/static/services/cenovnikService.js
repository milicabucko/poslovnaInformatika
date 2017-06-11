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
	
	cenovnikService.nadjiPoslednjiAktivan = function(companyPib, datum){
		return $http({
			method: 'GET',
			url: 'api/cenovnik/nadjiPoslednjiAktivan/' + companyPib + '/' + datum
		});
	}

	return cenovnikService;

});