app.controller('poslovnaGodinaController',['$scope', '$location', '$mdDialog', 'companyService', 'poslovnaGodinaService', function($scope, $location, $mdDialog, companyService, poslovnaGodinaService){
	
	
	$scope.dodajPoslovnuGodinu = function() {
		if ($scope.selected[0] === undefined) {
			$mdDialog.show($mdDialog.alert()
				.clickOutsideToClose(true)
				.title('Greska')
				.textContent('Morate izabrati preduzece.')
				.ok('OK')
			);
		}else{
			poslovnaGodinaService.sacuvajGodinu($scope.selected[0].id, $scope.datumPocetakVazenja, $scope.datumKrajVazenja, $scope.brojGodine);
		}
	}
	
	$scope.provera = function(){
		/*if($scope.brojGodine.length > 4){
			$mdDialog.show($mdDialog.alert()
				.clickOutsideToClose(true)
				.title('Greska')
				.textContent('Broj godine mora biti cetvorocifreni broj.')
				.ok('OK')
			);
		}*/
	}
	
	$scope.dpChanged = function() {
		var danasnjiDatum = new Date();
		
		if ($scope.datumPocetakVazenja < danasnjiDatum) {
			$scope.datumPocetakVazenja = null;
			$mdDialog.show($mdDialog.alert()
				.clickOutsideToClose(true)
				.title('Greska')
				.textContent('Datum ne moze biti manji od danasnjeg.')
				.ok('OK')
			);
		}else if ($scope.datumKrajVazenja < $scope.datumPocetakVazenja ) {
			$scope.datumPocetakVazenja = null;
			$mdDialog.show($mdDialog.alert()
				.clickOutsideToClose(true)
				.title('Greska')
				.textContent('Datum ne moze biti veci od krajnjeg.')
				.ok('OK')
			);
		}
	}
	
	$scope.dkChanged = function() {
		var danasnjiDatum = new Date();
		
		if ($scope.datumKrajVazenja < danasnjiDatum) {
			$scope.datumKrajVazenja = null;
			$mdDialog.show($mdDialog.alert()
				.clickOutsideToClose(true)
				.title('Greska')
				.textContent('Datum ne moze biti manji od danasnjeg.')
				.ok('OK')
			);
		}
		else if ($scope.datumKrajVazenja < $scope.datumPocetakVazenja) {
			$scope.datumKrajVazenja = null;
			$mdDialog.show($mdDialog.alert()
				.clickOutsideToClose(true)
				.title('Greska!')
				.textContent('Datum ne moze biti manji od pocetnog.')
				.ok('OK')
			);
		}
	}
	
	$scope.pretraziPoImenu = function() {
		if($scope.imeZaPretragu == "") {
			companyService.getAllCompanies().then(function(response){ 
				 $scope.items = response.data;
				 $scope.itemsSize = $scope.items.length;
			});
		}
		else {	
			companyService.findByNameContaining($scope.imeZaPretragu).then(function(response){ 
				$scope.items = response.data;
				$scope.itemsSize = $scope.items.length;
			});
		}
	}
	
	companyService.getAllCompanies().then(function(response){ 
		 $scope.items = response.data;
		 $scope.itemsSize = $scope.items.length; 
	});
	
	$scope.options = {
			boundaryLinks: true,
			rowSelection: true
		};
		
		$scope.query = {
			order: 'name',
			limit: 5,
			page: 1
		};
		
		$scope.queryStavke = {
			order: 'naziv',
			limit: 5,
			page: 1
		};
		
		$scope.selected = [];
}]);

