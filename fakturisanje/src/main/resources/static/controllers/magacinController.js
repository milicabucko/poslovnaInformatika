app.controller('magacinController', [ '$scope', '$location', '$mdDialog',
		'companyService',
		function($scope, $location, $mdDialog, companyService) {

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
			
			$scope.onSelectEvent = function() {
				if ($scope.selected[0] === undefined) {
					$scope.businessPartners = [];
					
					$scope.naziv = ""; 
					$scope.adresa = ""; 
					$scope.pib = ""; 
					$scope.mbr = ""; 
					$scope.racun = ""; 
					
					$scope.knaziv = ""; 
					$scope.kadresa = ""; 
					$scope.kpib = ""; 
					$scope.kmbr = ""; 
					$scope.kracun = ""; 
				}
				else {
					
					$scope.naziv = $scope.selected[0].name; 
					$scope.adresa = $scope.selected[0].address;
					$scope.pib = $scope.selected[0].pib;
					$scope.mbr = $scope.selected[0].cidnumber;
					$scope.racun = $scope.selected[0].account;
					$scope.businessPartners = $scope.selected[0].businessPartners;
					$scope.businessPartnersSize = $scope.businessPartners.length;
					
					$scope.knaziv = ""; 
					$scope.kadresa = ""; 
					$scope.kpib = ""; 
					$scope.kmbr = ""; 
					$scope.kracun = ""; 
					
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