app.factory('fakturaService', function fakturaService($http){
	
	fakturaService.nadjiSledeciBrojDokumenta = function(){
		return $http({
			method: 'GET',
			url: 'api/faktura/nadjiSledeciBrojDokumenta'
		});
	}
	
	return fakturaService;
	
});