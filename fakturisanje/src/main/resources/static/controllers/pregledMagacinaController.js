app.controller('pregledMagacinaController',['$scope', '$location', '$mdDialog', 'magacinService', function($scope, $location, $mdDialog, magacinService){

	magacinService.sviMagacini().then(function(response){
		$scope.items = response.data;
		$scope.itemsSize = $scope.items.length;
		console.log($scope.items)
	});
	
	$scope.onSelectEvent = function() {
		if ($scope.selected[0] === undefined) {
			
		}
		else {
			
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
}]);