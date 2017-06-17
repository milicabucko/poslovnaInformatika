app.factory('cenovnikService', function cenovnikService($http){
	
	cenovnikService.kreirajCenovnik = function(idFirma,datumPV, datumKV){
		return $http({
			method: 'POST',
			url: 'api/cenovnik/kreirajCenovnik/' + idFirma,
			data: {
				"datumVazenjaPocetak" : datumPV,
				"datumVazenjaKraj" : null,
			}
		});
	}
	
	cenovnikService.nadjiPoslednjiAktivan = function(companyPib, datum){
		return $http({
			method: 'GET',
			url: 'api/cenovnik/nadjiPoslednjiAktivan/' + companyPib + '/' + datum
		});
	}
	
	cenovnikService.posaljiDatum = function(companyPib,datumDokumenta){
		return $http({
			method: 'GET',
			url: 'api/cenovnik/posaljiDatum/' + companyPib + '/'+ datumDokumenta
		});
		
	}
	
	cenovnikService.nadjiPoKompaniji = function(companyID){
		return $http({
			method: 'GET',
			url: '/api/cenovnik/findByCompany/' + companyID
		});
	}

	return cenovnikService;

});