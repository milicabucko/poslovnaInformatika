app.controller('pregledPreduzecaController',['$scope', '$location', '$mdDialog', 'companyService', '$timeout','businessPartnerService', function($scope, $location, $mdDialog, companyService, $timeout, businessPartnerService){

	$scope.onSelectEvent = function() {
		if ($scope.selected[0] === undefined) {
			$scope.businessPartners = [];
		}
		else {
			$scope.businessPartners = $scope.selected[0].businessPartners;
		}
	}
	
	$scope.options = {
		//autoSelect: true,
		boundaryLinks: true,
		//largeEditDialog: true,
		//pageSelector: true,
		rowSelection: true
	};
	
	$scope.query = {
			    order: 'name',
			    limit: 5,
			    page: 1
	};
	
	$scope.selected = [];
	$scope.bpselected = [];
	

	
	companyService.getAllCompanies().then(function(response){
		 
		 $scope.items = response.data;
	 
	});
	
	
	
	



}]);

