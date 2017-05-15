app.controller('homeController',['$scope', '$location', '$mdDialog', 'companyService', function($scope, $location, $mdDialog, companyService){
	
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
	 
	 
}]);