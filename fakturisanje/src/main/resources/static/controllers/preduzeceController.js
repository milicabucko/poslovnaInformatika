app.controller('preduzeceController',['$scope', '$location', '$mdDialog', 'companyService', function($scope, $location, $mdDialog, companyService){

	 $scope.kreirajPreduzece= function(){
		 var naziv = $scope.naziv;
		 var pib = $scope.pib;
		 var adresa = $scope.adresa;
		 var telefon = $scope.telefon;
		 var email = $scope.email;
		 var maticniBroj = $scope.maticniBroj;
		 var sifraDelatnosti = $scope.sifraDelatnosti;
		 var tekuciRacun = $scope.tekuciRacun;
		 var banka = $scope.banka;

		
		companyService.addCompany(naziv, pib, adresa, telefon, email,maticniBroj,sifraDelatnosti,tekuciRacun,banka).then(function(response){
			alert("dodato");
			
		});
	}


}]);