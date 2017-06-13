app.controller('pregledPreduzecaController',['$scope', '$location', '$mdDialog', 'companyService', '$timeout','businessPartnerService', function($scope, $location, $mdDialog, companyService, $timeout, businessPartnerService){
	
	$scope.onSelectEvent = function() {
		if ($scope.selected[0] === undefined) {
			$scope.businessPartners = [];
		}
		else {
			businessPartnerService.getAllBusinessPartners($scope.selected[0].id).then(function(response){
				$scope.businessPartners = response.data;
			});
		}
	}
	
	$scope.izvestajIzlazneFakture = function(firmaId, firmaNaziv) {
		//console.log(firmaId + " " + firmaNaziv);
		if($scope.datumPocetakVazenja == null || $scope.datumKrajVazenja == null){
			$mdDialog.show($mdDialog.alert()
				.clickOutsideToClose(true)
				.title('Greska')
				.textContent('Morate izabrati vremenski interval za koji zelite izvestaj izlaznih faktura.')
				.ok('OK')
			);
		}else{
			companyService.izvestajIzlaznihFaktura(firmaId, firmaNaziv, $scope.datumPocetakVazenja, $scope.datumKrajVazenja);
			$mdDialog.show($mdDialog.alert()
				.clickOutsideToClose(true)
				.title('Potvrda')
				.textContent('Izvestaj sa dnevnikom izlaznih faktura se nalazi u "dnevnikIzlaznihFaktura.pdf".')
				.ok('OK')
			);
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
	
	$scope.options = {
		boundaryLinks: true,
		rowSelection: true
	};
	
	$scope.query = {
			    order: 'name',
			    limit: 5,
			    page: 1
	};
	
	$scope.selected = [];
	$scope.bpselected = [];
	
	companyService.getAllCompanies().then(function(response){
		 $scope.items = response.data; 
	});
	
	$scope.kreirajPartnera = function(preduzeceId){
		businessPartnerService.aktivnaFirma = preduzeceId;
		$location.path("/listaSvihPreduzecaPP");
	}
}]);

