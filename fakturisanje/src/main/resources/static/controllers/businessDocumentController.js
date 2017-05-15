app.controller('businessDocumentController',['$scope', '$location', '$mdDialog', 'companyService', function($scope, $location, $mdDialog, companyService){

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
		  $scope.logPagination = function (page, limit) {
			    console.log('page: ', page);
			    console.log('limit: ', limit);
			  }
	
	$scope.selected = [];
	
	$scope.desserts = [
	 {
		 name: "Cheesecake",
		 calories : 150
	 },
	 {		 
		 name: "Apple",
		 calories: 200
	 },
	 {
		 name: "Sarma",
		 calories: 175
	 }
	 ,
	 {		 
		 name: "Apple",
		 calories: 200
	 },
	 {
		 name: "Sarma",
		 calories: 175
	 }
	 ,
	 {		 
		 name: "Apple",
		 calories: 200
	 },
	 {
		 name: "Sarma",
		 calories: 175
	 }
	 ,
	 {		 
		 name: "Apple",
		 calories: 200
	 },
	 {
		 name: "Sarma",
		 calories: 175
	 }
	         
	];
	

	

}]);