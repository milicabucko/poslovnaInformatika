app.controller('homeController',['$scope', '$location', '$mdDialog', 'companyService', function($scope, $location, $mdDialog, companyService){
	
	$scope.goToPregledPreduzeca = function() {
		$location.path("/pregledPreduzeca");
	}
	
	$scope.goToBusinessDocument = function() {
		$location.path("/businessDocument");
	}
	
	//deo za dialog
	$scope.addCompanyDialog = function(ev) {
	    $mdDialog.show({
	      controller: AddCompanyController,
	      templateUrl: '/views/dialogs/addCompanyDialog.html',
	      parent: angular.element(document.body),
	      targetEvent: ev,
	      clickOutsideToClose:true,
	      fullscreen: false // Only for -xs, -sm breakpoints.
	    })
	    .then(function(answer) {
	      
	    }, function() {
	     
	    });
	 };
	 
	 function AddCompanyController($scope, $mdDialog,companyService, $route) {
			
		 $scope.addCompanyDialog= function(){
			 var companyName = $scope.companyName;
			 var companyPib = $scope.companyPib;
			 var companyAddress = $scope.companyAddress;
			 var companyPhoneNumber = $scope.companyPhoneNumber;
			 var companyEmail = $scope.companyEmail;
			 var companyIdNumber = $scope.companyIdNumber;
			 var companyActivityCode = $scope.companyActivityCode;
			 var companyAccount = $scope.companyAccount;
			 var companyBank = $scope.companyBank;
			 
			 companyService.addCompany(companyName, companyPib, companyAddress, companyPhoneNumber, companyEmail, companyIdNumber, companyActivityCode, companyAccount, companyBank).then(function(response){
			
				 $mdDialog.hide();
				 $route.reload();
			 });
		 }
		 
		 $scope.closeDialog = function() {
			 $mdDialog.cancel();
		 }
		 
	 }
	 
	 companyService.getAllCompanies().then(function(response){
		 
		 $scope.companies = response.data;
	 
	 });
	 
	 $scope.addBusinessPartner = function(comId) {
		
		 companyService.id = comId;
		 
		    $mdDialog.show({
			      controller: BusinessPartnerController,
			      templateUrl: '/views/dialogs/addBusinessPartner.html',
			      parent: angular.element(document.body),
			      //scope: $scope,
			      clickOutsideToClose:true,
			      fullscreen: false // Only for -xs, -sm breakpoints.
			    })
			    .then(function(answer) {
			      
			    }, function() {
			     
			    });
			 };

			 function BusinessPartnerController($scope, $mdDialog,companyService, $route) {
				 
				 companyService.getAllCompaniesExceptCurrent(companyService.id).then(function(response){
					 	
					 $scope.companies = response.data;
					/* for (var i = 0; i <  $scope.companies.length; )*/
				 });
					
				 $scope.addBusinessPartners = function(){
					for(var i = 0; i < $scope.companies.length; i++){
						if($scope.companies[i].check == true){
							console.log($scope.companies[i].name);
						}
					}
					 
				 }
				 
				 $scope.closeDialog = function() {
					 $mdDialog.cancel();
				 }
				 
			 }	 
	 
	 
	 
}]);