app.factory('businessPartnerService', function businessPartnerService($http){
	
	businessPartnerService.getAllBusinessPartners = function(id){
		return $http({
			method: 'GET',
			url: 'api/company/findByCompany/' + id
		});
	}
	
	businessPartnerService.kreirajPartnera = function(idCompany1,idCompany2){
		return $http({
			method: 'POST',
			url: 'api/businessPartner/poslovniPartner/' + idCompany1 + "/" + idCompany2,
			data: {
				
			}
			
		});
	}
	
	var aktivnaFirma = {};
	
	
	return businessPartnerService;
});