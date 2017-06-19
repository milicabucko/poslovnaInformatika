app.controller('pregledCenovnikaController',['$scope', '$location', '$mdDialog', 'companyService', '$timeout','businessPartnerService','stavkaCenovnikaService','cenovnikService','$parse', '$window','authenticationService', function($scope, $location, $mdDialog, companyService, $timeout, businessPartnerService, stavkaCenovnikaService, cenovnikService, $parse, $window, authenticationService){
	$scope.user = authenticationService.getUser();
	$scope.authService = authenticationService;
	
	cenovnikService.nadjiPoKompaniji($scope.user.company.id).then(function(response){
		$scope.cenovnici = response.data;
		$scope.cenovniciSize = $scope.cenovnici.length;
	});
	
	
	$scope.dpChanged = function() {
		var danasnjiDatum = new Date();
		
		//kad nemamo nijedan cenovnik
		if ($scope.user.company.cenovnici.length == 0){
			if ($scope.datumPocetakVazenja < danasnjiDatum) {
				$scope.datumPocetakVazenja = null;
				$mdDialog.show(
					      $mdDialog.alert()
					        .clickOutsideToClose(true)
					        .title('Greska!')
					        .textContent('Datum ne moze biti manji od danasnjeg!')
					        .ok('Ok!')
				);
			}else if ($scope.datumKrajVazenja < $scope.datumPocetakVazenja ) {
						$scope.datumPocetakVazenja = null;
							$mdDialog.show(
									$mdDialog.alert()
									.clickOutsideToClose(true)
									.title('Greska!')
									.textContent('Datum ne moze biti veci od krajnjeg!')
									.ok('Ok!')
							);
			}
		}else {
			if ($scope.datumKrajVazenja < $scope.datumPocetakVazenja ) {
				$scope.datumPocetakVazenja = null;
					$mdDialog.show(
							$mdDialog.alert()
							.clickOutsideToClose(true)
							.title('Greska!')
							.textContent('Datum ne moze biti veci od krajnjeg!')
							.ok('Ok!')
					);
			}
		}
	}
	$scope.dkChanged = function() {
		var danasnjiDatum = new Date();
		
		//kad nemamo nijedan cenovnik
		if ($scope.user.company.cenovnici.length == 0){
			if ($scope.datumKrajVazenja < danasnjiDatum) {
				$scope.datumKrajVazenja = null;
				$mdDialog.show(
					      $mdDialog.alert()
					        .clickOutsideToClose(true)
					        .title('Greska!')
					        .textContent('Datum ne moze biti manji od danasnjeg!')
					        .ok('Ok!')
				);
			}
			else if ($scope.datumKrajVazenja < $scope.datumPocetakVazenja) {
				$scope.datumKrajVazenja = null;
				$mdDialog.show(
					      $mdDialog.alert()
					        .clickOutsideToClose(true)
					        .title('Greska!')
					        .textContent('Datum ne moze biti manji od pocetnog!')
					        .ok('Ok!')
				);
			}
		}else{
			if ($scope.datumKrajVazenja < $scope.datumPocetakVazenja) {
				$scope.datumKrajVazenja = null;
				$mdDialog.show(
					      $mdDialog.alert()
					        .clickOutsideToClose(true)
					        .title('Greska!')
					        .textContent('Datum ne moze biti manji od pocetnog!')
					        .ok('Ok!')
				);
			}
			
		}
			
			
	}
	
	companyService.getAllCompanies().then(function(response){
		 
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
		
		$scope.queryStavke = {
			    order: 'naziv',
			    limit: 5,
			    page: 1
		};
		
		$scope.dodajCenovnik = function(){
			
			$location.path("/cenovnik");
		}
		
		$scope.kopirajCenovnik = function(){
			if($scope.selectedCenovnik[0] === undefined){
				$mdDialog.show(
					      $mdDialog.alert()
					        .clickOutsideToClose(true)
					        .title('Greska!')
					        .textContent('Morate izabrati cenovnik!')
					        .ok('Ok!')
					    );
			}else{
				
				    var confirm = $mdDialog.prompt()
				      .title('Kopiranje cenovnika')
				      .textContent('Unesite za koliko procenata uvecavate cene')
				      .placeholder('%')
				      .ok('Ok!')
				      .cancel('Odustani');

				    $mdDialog.show(confirm).then(function(result) {
				    	
						cenovnikService.kreirajCenovnik($scope.user.company.id,$scope.datumPocetakVazenja,$scope.datumKrajVazenja).then(function(response){

							console.log(response.data);
							if(response.data.id == null){
								$mdDialog.show(
									      $mdDialog.alert()
									        .clickOutsideToClose(true)
									        .title('Greska!')
									        .textContent('Cenovnik sa tim datumom vec postoji!')
									        .ok('Ok!')
								);
							}else{
							
							for(var i = 0; i < $scope.stavke.length; i++) {
								console.log("Result: " + result);
								stavkaCenovnikaService.sacuvajStavku(response.data.id, $scope.stavke[i].artikal.id, $scope.stavke[i].cena+(parseInt(result)/100)*$scope.stavke[i].cena).then(function(response){ 
								});
								$mdDialog.show(
										$mdDialog.alert()
									    .clickOutsideToClose(true)
									    .title('Obavestenje')
									    .textContent('Cenovnik je kopiran.')
									    .ok('OK')
								);
							}
							cenovnikService.nadjiPoKompaniji($scope.user.company.id).then(function(response){
								$scope.cenovnici = response.data;
								$scope.cenovniciSize = $scope.cenovnici.length;
							});
							
							}
						});
				    }, function() {
				      alert("odustani")
				    });
				  
				
			}
			
		}
		
		
		$scope.onSelectEventCenovnik = function() {
			if ($scope.selectedCenovnik[0] === undefined) {
				$scope.stavke = [];
				$scope.stavkeSize = 0;
			}
			else{
				console.log($scope.selectedCenovnik[0].stavkecenovnika[0]);
				$scope.stavke = $scope.selectedCenovnik[0].stavkecenovnika;
				$scope.stavkeSize = $scope.stavke.length;
			}
		}
		
		$scope.selected = [];
		$scope.selectedCenovnik = [];
		$scope.cenovniciSize = 0;
		$scope.selectedStavka = [];
		$scope.stavkeSize = 0;
}]);