app.controller('preduzeceController',['$scope', '$location', '$mdDialog', 'companyService','$mdToast', function($scope, $location, $mdDialog, companyService, $mdToast){

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

		 
		if ($scope.preduzeceForm.$valid) {
			
			companyService.addCompany(naziv, pib, adresa, telefon, email,maticniBroj,sifraDelatnosti,tekuciRacun,banka).then(function(response){ 
				
				if(response.data.id == null){
					if (response.data.pib == -1) {
						$mdDialog.show(
							      $mdDialog.alert()
							        .clickOutsideToClose(false)
							        .title('Greska!')
							        .textContent('Pib vec postoji!')
							        .ok('Ok!')
							    );
					}
					if (response.data.cidnumber == -1) {
						$mdDialog.show(
							      $mdDialog.alert()
							        .clickOutsideToClose(false)
							        .title('Greska!')
							        .textContent('Maticni broj vec postoji!')
							        .ok('Ok!')
							    );
					}
					console.log(response.data.account);
					if (response.data.account == "-1") {
						$mdDialog.show(
							      $mdDialog.alert()
							        .clickOutsideToClose(false)
							        .title('Greska!')
							        .textContent('Tekuci racun vec postoji!')
							        .ok('Ok!')
							    );
					}
						    
				 }
				 
				 
			});
		}
		else {
			$mdDialog.show(
					$mdDialog.alert()
				        .clickOutsideToClose(true)
				        .title('Greska!')
				        .textContent('Odredjena polja u formi nisu validna!')
				        .ok('Ok!')
				    );
		}
	}
	 
	 var last = {
		      bottom: false,
		      top: true,
		      left: false,
		      right: true
		    };
	 
	 $scope.toastPosition = angular.extend({},last);
	 //{myForm.input.$valid}
	 $scope.proveriPib = function(){
		 
		 if ($scope.preduzeceForm.pib.$error.pattern) {
			  $mdToast.show(
				      $mdToast.simple()
				        .textContent('Dozvoljen unos za samo 10 brojeva!')
				        .hideDelay(2000)
				        .position('top right')
				        .toastClass('error')
			  );
		 }
	
	
		
	 }
	

}]);