app.factory('businessPartnerService', function businessPartnerService($http){
	
	businessPartnerService.getAllBusinessPartners = function(id){
		return $http({
			method: 'GET',
			url: 'api/company/findByCompany/' + id
		});
	}
	
	return businessPartnerService;
});