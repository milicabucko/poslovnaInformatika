app.controller('poslovnaGodinaController',['$scope', '$route', '$location', '$mdDialog', 'companyService', 'poslovnaGodinaService', function($scope, $route, $location, $mdDialog, companyService, poslovnaGodinaService){
	
	$scope.dodajPoslovnuGodinu = function() {
		if ($scope.selected[0] === undefined) {
			$mdDialog.show($mdDialog.alert()
				.clickOutsideToClose(true)
				.title('Greska')
				.textContent('Morate izabrati preduzece.')
				.ok('OK')
			);
		}else{
			if($scope.listaGodina.$valid){
				poslovnaGodinaService.sacuvajGodinu($scope.selected[0].id, $scope.datumPocetakVazenja, $scope.datumKrajVazenja, $scope.brojGodine).then(function(response){
					if(response.data.brojGodine == 0000){
						$mdDialog.show($mdDialog.alert()
							.clickOutsideToClose(true)
							.title('Greska')
							.textContent('Poslovna godina sa tim brojem godine vec postoji u okviru preduzeca.')
							.ok('OK')
						);
					}else{
						$scope.godine.push(response.data);
						$mdDialog.show($mdDialog.alert()
							.clickOutsideToClose(true)
							.title('Potvrda')
							.textContent('Uspesno uneta poslovna godina.')
							.ok('OK')
						);
					}
				});
			}else{
				$mdDialog.show($mdDialog.alert()
					.clickOutsideToClose(true)
					.title('Greska')
					.textContent('Morate uneti broj godine.')
					.ok('OK')
				);
			}
		}
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
	
	poslovnaGodinaService.sveGodine().then(function(response){
		$scope.godine = response.data;
		$scope.godineSize = $scope.godine.length;
	});
	
	$scope.promeniStatus = function(brojGodine, firmaId){
		poslovnaGodinaService.promenaStatusa(firmaId, brojGodine).then(function(response){
			if(response.data.brojGodine == 0000){
				$mdDialog.show($mdDialog.alert()
					.clickOutsideToClose(true)
					.title('Greska')
					.textContent('Samo jedna poslovna godina moze biti aktivna u okviru preduzeca.')
					.ok('OK')
				);
			}else{
				$route.reload();
			}
		});
		
	}
	
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
			order: 'godina',
			limit: 5,
			page: 1
		};
		
		$scope.selected = [];
		$scope.selectedGodine = [];
}]);

