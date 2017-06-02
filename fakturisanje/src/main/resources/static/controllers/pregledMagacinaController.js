app.controller('pregledMagacinaController',['$scope', '$location', '$mdDialog', 'magacinService', function($scope, $location, $mdDialog, magacinService){

	magacinService.sviMagacini().then(function(response){
		$scope.items = response.data;
		$scope.itemsSize = $scope.items.length;
		console.log($scope.items)
	});
	
	$scope.onSelectEvent = function() {
		
		if ($scope.selected[0] === undefined) {
			$scope.kartice = [];
			$scope.analitike = [];

		}
		else {
			$scope.kartice = $scope.selected[0].kartice;
		}
	}
	
	$scope.onSelectKartica = function() {
		
		if ($scope.selectedMK[0] === undefined) {
			$scope.analitike = [];

		}
		else {
			$scope.analitike = $scope.selectedMK[0].analitike;
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
	$scope.selectedMK = [];
	$scope.selectedAnalitika = [];
	
	
	
	
}]);