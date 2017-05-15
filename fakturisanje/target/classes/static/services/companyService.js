app.factory('companyService', function companyService($http){
	
	companyService.addCompany = function(companyName, companyPib, companyAddress, companyPhoneNumber, companyEmail, companyIdNumber, companyActivityCode, companyAccount, companyBank){
		return $http({
			method: 'POST',
			url: 'api/company/addCompany',
			data: {
				"id" : null,
				"name" : companyName,
				"pib" : companyPib,
				"address" : companyAddress,
				"phonenumber" : companyPhoneNumber,
				"email" : companyEmail,
				"cidnumber" : companyIdNumber,
				"activitycode":companyActivityCode,
				"account":companyAccount,
				"bank":companyBank
				
			}
			
		});
	}
	
	return companyService;
});