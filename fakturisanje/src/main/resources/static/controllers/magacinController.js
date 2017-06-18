app.controller('magacinController', [ '$scope', '$location', '$window', '$mdDialog', 'companyService', 'magacinService','authenticationService', function($scope, $location, $window, $mdDialog, companyService, magacinService, authenticationService) {
	$scope.user = authenticationService.getUser();
	$scope.authService = authenticationService;
	
			companyService.getAllCompanies().then(function(response) {

				$scope.items = response.data;
				$scope.itemsSize = $scope.items.length;

			});

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
					
			$scope.dodajMagacin = function() {
				
				if($scope.magacinForm.$valid){
						magacinService.dodajMagacin($scope.sifraMagacina, $scope.nazivMagacina, $scope.user.company.id).then(function(response){
							if(response.data.sifra == "greska"){
								$mdDialog.show(
									$mdDialog.alert()
									.clickOutsideToClose(true)
									.title('Greska')
									.textContent('Postoji magacin sa takvom sifrom u okviru preduzeca.')
									.ok('OK')
								);
								$scope.sifraMagacina = "";
							}else{
								$mdDialog.show(
									$mdDialog.alert()
									.clickOutsideToClose(true)
									.title('Potvrda')
									.textContent('Dodat magacin.')
									.ok('OK')
								);
								$scope.sifraMagacina = "";
								$scope.nazivMagacina = "";
							}
						});
					
				}else {
					$mdDialog.show(
						$mdDialog.alert()
						.clickOutsideToClose(true)
						.title('Greska')
						.textContent('Niste uneli sifru ili naziv magacina.')
						.ok('OK')
					);
				}
				
			}
			
			$scope.onSelectEvent = function() {
				if ($scope.selected[0] === undefined) {
					
				}
				else {
					
				}
			}
			
			$scope.options = {
				// autoSelect: true,
				boundaryLinks : true,
				// largeEditDialog: true,
				// pageSelector: true,
				rowSelection : true
			};

			$scope.query = {
				order : 'name',
				limit : 5,
				page : 1
			};

			$scope.selected = [];

} ]);