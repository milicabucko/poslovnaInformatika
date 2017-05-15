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