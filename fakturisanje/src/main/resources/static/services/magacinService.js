app.factory('magacinService', function magacinService($http){
	
	magacinService.getAllCompanies = function(){
		return $http({
			method: 'GET',
			url: 'api/company/getAllCompanies'
		});
	}
	
	return magacinService;
});