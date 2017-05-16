app.controller('businessDocumentController',['$scope', '$location', '$mdDialog', 'companyService', '$timeout', function($scope, $location, $mdDialog, companyService, $timeout){

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
	
	$scope.removeItems = function() {
		
		for (var i = 0; i < $scope.selected.length; i++ ) {
			for (var j = 0; j < $scope.items.length; j++) {
				if ($scope.selected[i].id == $scope.items[j].id) {
					var index = $scope.items.indexOf($scope.items[j]);
					$scope.items.splice(index, 1); 
				}
			}
		}
		
	}
	
	$scope.addNewItem = function() {
		
		$scope.items.push({
			 id: 2000,
			 name: "Mouse321321",
			 unitOfMeasure: "unit", 
			 amount: 3,
			 pricePerUnit: 300,
			 rebate: 0, 
			 taxBase: 100,
		     taxRate : 10,
		     amountOfTax : 200
		 });
		
	}
	
	$scope.user = null;
	$scope.users = null;
	
	$scope.loadUsers = function() {

	    // Use timeout to simulate a 650ms request.
	    return $timeout(function() {

	      $scope.users =  $scope.users  || [
	        { id: 1, name: 'Scooby Doo' },
	        { id: 2, name: 'Shaggy Rodgers' },
	        { id: 3, name: 'Fred Jones' },
	        { id: 4, name: 'Daphne Blake' },
	        { id: 5, name: 'Velma Dinkley' }
	      ];

	    }, 650);
	  };
	
	$scope.items = [
	 {
		 id: 123,
		 name: "Mouse",
		 unitOfMeasure: "unit", 
		 amount: 3,
		 pricePerUnit: 300,
		 rebate: 0, 
		 taxBase: 100,
	     taxRate : 10,
	     amountOfTax : 200
	 },
	 {
		 id: 124,
		 name: "Mouse 1",
		 unitOfMeasure: "unit", 
		 amount: 3,
		 pricePerUnit: 300,
		 rebate: 0, 
		 taxBase: 100,
	     taxRate : 10,
	     amountOfTax : 200
	 },
	 {
		 id: 125,
		 name: "Mouse 3",
		 unitOfMeasure: "unit", 
		 amount: 3,
		 pricePerUnit: 300,
		 rebate: 0, 
		 taxBase: 100,
	     taxRate : 10,
	     amountOfTax : 200
	 },
	 {
		 id: 413,
		 name: "Mouse 321",
		 unitOfMeasure: "unit", 
		 amount: 3,
		 pricePerUnit: 300,
		 rebate: 0, 
		 taxBase: 100,
	     taxRate : 10,
	     amountOfTax : 200
	 },
	 {
		 id: 1,
		 name: "Mouse 312",
		 unitOfMeasure: "unit", 
		 amount: 3,
		 pricePerUnit: 300,
		 rebate: 0, 
		 taxBase: 100,
	     taxRate : 10,
	     amountOfTax : 200
	 },
	 {
		 id: 1233213,
		 name: "Mouse 321312",
		 unitOfMeasure: "unit", 
		 amount: 3,
		 pricePerUnit: 300,
		 rebate: 0, 
		 taxBase: 100,
	     taxRate : 10,
	     amountOfTax : 200
	 },
	 {
		 id: 31,
		 name: "Mouse ds",
		 unitOfMeasure: "unit", 
		 amount: 3,
		 pricePerUnit: 300,
		 rebate: 0, 
		 taxBase: 100,
	     taxRate : 10,
	     amountOfTax : 200
	 },
	 {
		 id: 2342,
		 name: "Mouse dsadas231",
		 unitOfMeasure: "unit", 
		 amount: 3,
		 pricePerUnit: 300,
		 rebate: 0, 
		 taxBase: 100,
	     taxRate : 10,
	     amountOfTax : 200
	 }
	         
	];
	

	

}]);