app.factory('fakturaService', function fakturaService($http){
	
	
	fakturaService.posaljiFakturu = function(izdId, kupId, brojDokumenta, statusDokumenta, datumDokumenta, datumValute){
		return $http({
			method: 'POST',
			url: 'api/faktura/sacuvajFakturu/' + izdId + '/' + kupId,
			data: {
				"brojDokumenta" : brojDokumenta,
				"statusDokumenta" : statusDokumenta,
				"datumDokumenta" : datumDokumenta,
				"datumValute" : datumValute
			}
		});
	}
	
	fakturaService.promeniStatus = function(fakturaId, status){
		return $http({
			method: 'POST',
			url: 'api/faktura/promeniStatusDokumenta/' + fakturaId + '/' + status
		});
	}
	
	fakturaService.nadjiSledeciBrojDokumenta = function(){
		return $http({
			method: 'GET',
			url: 'api/faktura/nadjiSledeciBrojDokumenta'
		});
	}
	
	fakturaService.sveFakture = function(){
		return $http({
			method: 'GET',
			url: 'api/faktura/sveFakture'
		});
	}
	
	
	return fakturaService;
	
});