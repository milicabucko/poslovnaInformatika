app.controller('narudzbenicaController',['$scope', '$location', '$mdDialog', 'companyService', 'businessPartnerService', 'fakturaService', 'artikalService', 'cenovnikService', 'magacinService', 'stavkaDokumentaService','authenticationService', function($scope, $location, $mdDialog, companyService, businessPartnerService, fakturaService, artikalService, cenovnikService, magacinService, stavkaDokumentaService, authenticationService){

	$scope.user = authenticationService.getUser();
	$scope.authService = authenticationService;
	
	$scope.selectedArtikal = [];
	
	
	
	
	
	$scope.dodajArtikal = function(artikalSifra){
		
		$scope.omogucenoDodavanje = false;
		$scope.omogucenaIzmena = false;
		$scope.omogucenoBrisanje = false;
		
		$scope.sifraZaPretragu = artikalSifra;
		$scope.pretraziPoSifriArtikla();
	}
	
	
	$scope.posaljiFakturu = function() {

		fakturaService.posaljiNarudzbenicu($scope.user.company.id, $scope.bpselected[0].company2.id, $scope.brDok, "poslata", $scope.datumDok, $scope.datumVal, -1).then(function(response){ 
			
			$mdDialog.show(
					$mdDialog.alert()
				    .clickOutsideToClose(true)
				    .title('Obavestenje')
				    .textContent('Narudzbenica je poslata.')
				    .ok('OK')
			);
			
			for(var i = 0; i < $scope.stavke.length; i++) {
				
				stavkaDokumentaService.sacuvajStavku(response.data.id, $scope.stavke[i].idArtikla, $scope.stavke[i].kolicina, $scope.stavke[i].cenaPoJed).then(function(response){ 
					
					
					
				});
				
			}
			
		});
		
	}
	
	$scope.statusDok = "U izdradi";
	
	
	$scope.datumDok = new Date();
	

	fakturaService.nadjiSledeciBrojDokumenta().then(function(response){ 
		$scope.brDok = response.data + 1;
	});
	
	$scope.stavke = [];
	$scope.stavkeSize = 0;
	$scope.businessPartnersSize = 0;
	$scope.omogucenoDodavanje = false;
	$scope.omogucenaIzmena = false;
	$scope.omogucenoBrisanje = false;
	
	$scope.obrisiStavku = function() {
		for(var i = 0; i < $scope.stavke.length; i++) {
			if ($scope.sifraZaPretragu == $scope.stavke[i].sifra) {
				var index = $scope.stavke.indexOf($scope.stavke[i]);
				$scope.stavke.splice(index, 1);
				$scope.stavkeSize = $scope.stavke.length;
				$scope.kolicina = "";
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
				$scope.stavke[i].kolicina = $scope.kolicina;
				$scope.omogucenaIzmena = true;
				$scope.omogucenoBrisanje = true;
				$scope.stavkeSize = $scope.stavke.length;
				return;
			}
		}
	}
	
	$scope.dodajStavku = function(){
		
		var id = {};
		artikalService.findBySifra($scope.sifraZaPretragu).then(function(response){
			var stavkaZaDodavanje = {	
					naziv : $scope.nazArtikla, 
					kolicina : $scope.kolicina, 
					sifra: $scope.sifraZaPretragu,
					jedMere: $scope.jmArtikla,
					idArtikla : response.data.id,
					cenaPoJed : $scope.cena
				};
			$scope.stavke.push(stavkaZaDodavanje);
			$scope.stavkeSize = $scope.stavke.length;
			$scope.omogucenaIzmena = true;
			$scope.omogucenoBrisanje = true;
			$scope.omogucenoDodavanje = false;

		});
	}
	
	
	$scope.unesiKolicinu = function() {
				
			magacinService.findByPreduzecePib($scope.dpib).then(function(response){
				//response je lista magacina,pa uzmem samo karticu od prvog magacina
				magacinService.nadjiMagacinskuKarticuArtikla(response.data[0].id, $scope.sifraZaPretragu).then(function(response){
					if((response.data.pocStanjeKol + response.data.prometUlKol - response.data.prometIzKol) >= $scope.kolicina) {
						
					}
					else {
						alert('Nema dovoljno artikala u magacinu');
						$scope.kolicina = $scope.kolicina + "";
						$scope.kolicina = $scope.kolicina.substring(0, $scope.kolicina.length - 1);
						$scope.kolicina = parseInt($scope.kolicina);
					}
				});
			});	
			
			
	}
	
	$scope.pretraziPoSifriArtikla = function(){
		if($scope.sifraZaPretragu == "") {
			$scope.nazArtikla = "";
			$scope.jmArtikla = "";
			$scope.cena = "";
			$scope.omogucenaIzmena = false;
			$scope.omogucenoBrisanje = false;
		}
		else {	
			
			
			artikalService.findBySifra($scope.sifraZaPretragu).then(function(response){ 
				$scope.nazArtikla = response.data.naziv;
				$scope.jmArtikla = response.data.jedMere;
				
				if (response.data != "") {
					$scope.jmArtikla = response.data.jedinicaMere.oznakaJedinice;
				}
				else {
					$scope.jmArtikla = "";
					$scope.cena = "";
				}
				
				if ($scope.nazArtikla !== undefined) {
					
					//za cenu artikla
					cenovnikService.posaljiDatum($scope.dpib, $scope.datumDok).then(function(response){ 
						var cenovnik = response.data;
						for(var i = 0; i < cenovnik.stavkecenovnika.length; i++) {
							console.log(cenovnik.stavkecenovnika[i]);
								if ($scope.sifraZaPretragu == cenovnik.stavkecenovnika[i].artikal.sifra) {
									$scope.cena = cenovnik.stavkecenovnika[i].cena;
									return;
								}
						}
						
					});
					//
					
					for(var i = 0; i < $scope.stavke.length; i++) {
						if ($scope.sifraZaPretragu == $scope.stavke[i].sifra) {
							$scope.kolicina = $scope.stavke[i].kolicina;
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
	
	
	
	$scope.onSelectBPEvent = function() {
			
			$scope.dnaziv = $scope.bpselected[0].company2.name; 
			$scope.dadresa = $scope.bpselected[0].company2.address;
			$scope.dpib = $scope.bpselected[0].company2.pib;
			$scope.dmbr = $scope.bpselected[0].company2.cidnumber;
			$scope.dracun = $scope.bpselected[0].company2.account;
			
			artikalService.nadjArtikleFirme($scope.bpselected[0].id).then(function(response){
				
				$scope.artikli = response.data;
				$scope.artikliSize = response.data.length;
				
			});
		
	}
	
	
			
			$scope.naziv = $scope.user.company.name; 
			$scope.adresa = $scope.user.company.address;
			$scope.pib = $scope.user.company.pib;
			$scope.mbr = $scope.user.company.cidnumber;
			$scope.racun = $scope.user.company.account;
			
			businessPartnerService.getAllBusinessPartners($scope.user.company.id).then(function(response){
				console.log(response.data[0].company2);
				$scope.businessPartners = response.data;
					
			});
			
			$scope.dnaziv = ""; 
			$scope.dadresa = ""; 
			$scope.dpib = ""; 
			$scope.dmbr = ""; 
			$scope.dracun = ""; 
	
	
	companyService.getAllCompanies().then(function(response){
		 
		 $scope.items = response.data;
		 $scope.itemsSize = $scope.items.length;
	 
	});
	
	
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
		
		$scope.selektovaneStavke = [];
		$scope.selected = [];
		$scope.bpselected = [];
		
	 
}]);