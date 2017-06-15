app.controller('cenovnikController',['$scope', '$location', '$mdDialog', 'companyService', 'artikalService', 'stavkaCenovnikaService','cenovnikService', function($scope, $location, $mdDialog, companyService, artikalService, stavkaCenovnikaService, cenovnikService){

	$scope.dpChanged = function() {
		var danasnjiDatum = new Date();
		
		//kad nemamo nijedan cenovnik
		if ($scope.selected[0].cenovnici.length == 0){
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
		if ($scope.selected[0].cenovnici.length == 0){
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
		
		$scope.pretraziPoSifriArtikla = function(){
			if($scope.sifraZaPretragu == "") {
				$scope.nazArtikla = "";
				$scope.jmArtikla = "";
				$scope.omogucenaIzmena = false;
				$scope.omogucenoBrisanje = false;
			}
			else {	
				artikalService.findBySifra($scope.sifraZaPretragu).then(function(response){ 
					$scope.nazArtikla = response.data.naziv;
					$scope.jmArtikla = response.data.jedinicaMere.oznakaJedinice;
					if ($scope.nazArtikla !== undefined) {
						
						for(var i = 0; i < $scope.stavke.length; i++) {
							if ($scope.sifraZaPretragu == $scope.stavke[i].sifra) {
								$scope.cena = $scope.stavke[i].cena;
								$scope.omogucenaIzmena = true;
								$scope.omogucenoBrisanje = true;
								return;
							}
						}
						if ($scope.omogucenaIzmena == false) {
							$scope.omogucenoDodavanje = true;
						}
						else {
							$scope.omogucenoDodavanje = false;
						}
					}
					else {
						$scope.omogucenoDodavanje = false;
						$scope.omogucenaIzmena = false;
						$scope.omogucenoBrisanje = false;
					}
				});
				
			}
		}
		
		artikalService.nadjiSveArtikle().then(function(response){
			
			$scope.artikli = response.data;
			$scope.artikliSize = response.data.length;
			
		});
		
		
	
		
		$scope.dodajArtikal = function(artikalSifra){
			
			$scope.omogucenoDodavanje = false;
			$scope.omogucenaIzmena = false;
			$scope.omogucenoBrisanje = false;
			
			$scope.sifraZaPretragu = artikalSifra;
			$scope.pretraziPoSifriArtikla();
			
		
		} 
		
		
		$scope.dodajStavku = function(){
			
			var id = {};
			artikalService.findBySifra($scope.sifraZaPretragu).then(function(response){
				var stavkaZaDodavanje = {	
						naziv : $scope.nazArtikla, 
						cena : $scope.cena, 
						sifra: $scope.sifraZaPretragu,
						jedMere: $scope.jmArtikla,
						idArtikla : response.data.id
					};
				$scope.stavke.push(stavkaZaDodavanje);
				$scope.stavkeSize = $scope.stavke.length;
				$scope.omogucenaIzmena = true;
				$scope.omogucenoBrisanje = true;
				$scope.omogucenoDodavanje = false;

			});
		
		
		}
		
		$scope.obrisiStavku = function() {
			for(var i = 0; i < $scope.stavke.length; i++) {
				if ($scope.sifraZaPretragu == $scope.stavke[i].sifra) {
					var index = $scope.stavke.indexOf($scope.stavke[i]);
					$scope.stavke.splice(index, 1);
					$scope.stavkeSize = $scope.stavke.length;
					$scope.cena = "";
					$scope.sifraZaPretragu = "";
					$scope.nazArtikla = "";
					$scope.jmArtikla = "";
					$scope.omogucenoDodavanje = false;
					$scope.omogucenaIzmena = false;
					$scope.omogucenoBrisanje = false;
				}
			}
		}
		
		$scope.izmeniStavku = function() {
			for(var i = 0; i < $scope.stavke.length; i++) {
				if ($scope.sifraZaPretragu == $scope.stavke[i].sifra) {
					$scope.stavke[i].cena = $scope.cena;
					$scope.omogucenaIzmena = true;
					$scope.omogucenoBrisanje = true;
					$scope.stavkeSize = $scope.stavke.length;
					return;
				}
			}
		}
		
		$scope.kreirajCenovnik = function(){
			
			
			
			cenovnikService.kreirajCenovnik($scope.selected[0].id,$scope.datumPocetakVazenja,$scope.datumKrajVazenja).then(function(response){

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
					stavkaCenovnikaService.sacuvajStavku(response.data.id, $scope.stavke[i].idArtikla, $scope.stavke[i].cena).then(function(response){ 
					});
				}
				
				}
			});
			
			
			
		};
		
		
		$scope.stavke = [];
		$scope.stavkeSize = 0;
		$scope.businessPartnersSize = 0;
		$scope.omogucenoDodavanje = false;
		$scope.omogucenaIzmena = false;
		$scope.omogucenoBrisanje = false;

		
		$scope.selektovaneStavke = [];
		$scope.selected = [];


}]);