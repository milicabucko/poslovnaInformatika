app.controller('pregledMagacinaController',['$scope', '$location', '$mdDialog', 'magacinService','authenticationService', function($scope, $location, $mdDialog, magacinService, authenticationService){

	$scope.vidiAnalitike = false;
	$scope.vidiKartice = false;
	
	$scope.user = authenticationService.getUser();
	$scope.authService = authenticationService;
	
	magacinService.findByPreduzecePib($scope.user.company.pib).then(function(response){
		$scope.items = response.data;
		$scope.itemsSize = $scope.items.length;
	});
	
	$scope.pretraziPoSifri = function() {
		if($scope.sifraZaPretragu == ""){
			magacinService.findByPreduzecePib($scope.user.company.pib).then(function(response){
				$scope.items = response.data;
				$scope.itemsSize = $scope.items.length;
				console.log($scope.items)
			});
		} else {
			magacinService.findBySifraAndCompany($scope.sifraZaPretragu, $scope.user.company.id).then(function(response){ 
				$scope.items = response.data;
				$scope.itemsSize = $scope.items.length;
			});
		}
	}
	
	$scope.obrisiMagacin = function() {
		if($scope.selected[0] === undefined){
			$mdDialog.show(
					$mdDialog.alert()
				    .clickOutsideToClose(true)
				    .title('Greska')
				    .textContent('Morate izabrati magacin.')
				    .ok('OK')
			);
		}
		for(var i = 0; i < $scope.items.length; i++){
			if(($scope.items[i].sifra == $scope.selected[0].sifra) && ($scope.items[i].preduzece.id == $scope.selected[0].preduzece.id)){
				magacinService.obrisiMagacin($scope.items[i].sifra, $scope.items[i].naziv, $scope.items[i].preduzece.id);
				var index = $scope.items.indexOf($scope.items[i]);
				$scope.items.splice(index, 1);
				$scope.itemsSize = $scope.items.length;
				$scope.vidiKartice = false;
				$scope.vidiAnalitike = false;
				$scope.kartice = [];
				$scope.analitike = [];
			}
		}
	}
	
	$scope.izmeniMagacin = function() {
		
	}
	
	$scope.lager = function(sifraM, firmaId){
		magacinService.lagerMagacina(sifraM, firmaId).then(function(response){
			
		});
		$mdDialog.show(
				$mdDialog.alert()
			    .clickOutsideToClose(true)
			    .title('Potvrda')
			    .textContent('Izvestaj sa lager listom se nalazi u "lager.pdf".')
			    .ok('OK')
		);
	}
	
	$scope.magacinskaKartica = function(karticaId){
		magacinService.magacinskaKartica(karticaId).then(function(response){
			
		});
		$mdDialog.show(
				$mdDialog.alert()
			    .clickOutsideToClose(true)
			    .title('Potvrda')
			    .textContent('Izvestaj sa magacinskom karticom se nalazi u "magacinskaKartica.pdf".')
			    .ok('OK')
		);
	}
	
	$scope.onSelectEvent = function() {
		
		if ($scope.selected[0] === undefined) {
			$scope.kartice = [];
			$scope.analitike = [];
			$scope.sifraMagacina = "";
			$scope.nazivMagacina = "";
			$scope.vidiKartice = false;
			$scope.vidiAnalitike = false;
		}
		else {
			$scope.kartice = $scope.selected[0].kartice;
			$scope.sifraMagacina = $scope.selected[0].sifra;
			$scope.nazivMagacina = $scope.selected[0].naziv;
			$scope.vidiKartice = true;
			$scope.vidiAnalitike = false;
		}
	}
	
	$scope.onSelectKartica = function() {
		
		if ($scope.selectedMK[0] === undefined) {
			$scope.analitike = [];
			$scope.vidiAnalitike = false;
		}
		else {
			$scope.analitike = $scope.selectedMK[0].analitike;
			$scope.vidiAnalitike = true;
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