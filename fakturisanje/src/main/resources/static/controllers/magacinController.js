app.controller('magacinController', [ '$scope', '$location', '$window', '$mdDialog', 'companyService', 'magacinService', function($scope, $location, $window, $mdDialog, companyService, magacinService) {

			$scope.omogucenoDodavanje = false;
	
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
			
			$scope.pretragaPoSifri = function() {
				
				if($scope.sifraMagacina == "" || $scope.nazivMagacina == ""){
					$scope.omogucenoDodavanje = false;
					//toastr.options.timeOut = 1500;
					//toastr.info("Potrebno je izabrati barem jedan sto.");
				}else{
					magacinService.findBySifra($scope.sifraMagacina).then(function(response){
						if($scope.sifraMagacina == response.data.sifra){
							$scope.omogucenoDodavanje = false;
						}else{
							$scope.omogucenoDodavanje = true;
						}
					});	
				}	
			}
			
			$scope.dodajMagacin = function() {
				magacinService.dodajMagacin($scope.sifraMagacina, $scope.nazivMagacina, $scope.selected[0].id);
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