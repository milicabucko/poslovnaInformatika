app.controller('fakturaController',['$scope', '$location', '$mdDialog', 'companyService', 'businessPartnerService', 'artikalService', 'fakturaService', 'stavkaDokumentaService', 'magacinService', function($scope, $location, $mdDialog, companyService, businessPartnerService, artikalService, fakturaService, stavkaDokumentaService, magacinService){
	
	$scope.posaljiFakturu = function() {
		console.log("posiljalac: " + $scope.selected[0].id + " kupac: " + $scope.bpselected[0].id);
		fakturaService.posaljiFakturu($scope.selected[0].id, $scope.bpselected[0].id, $scope.brDok, "poslata", $scope.datumDok, $scope.datumVal).then(function(response){ 
			for(var i = 0; i < $scope.stavke.length; i++) {
				stavkaDokumentaService.sacuvajStavku(response.data.id, $scope.stavke[i].idArtikla, $scope.stavke[i].kolicina).then(function(response){ 
					console.log(response.data);
					magacinService.dodajAnalitikuMK(response.data.artikal.id, $scope.pib, response.data.id).then(function(response){ 
						
					});
					
				});
			}
			
		});
	}
	
	$scope.statusDok = "U izdradi";
	
	$scope.promenaDatumaDokumenta = function() {
		var now = $scope.datumDok;
		now.setDate(now.getDate() + 30);
		$scope.datumVal = now;
	}
	
	$scope.datumDok = new Date();
	
	var now = new Date();
	now.setDate(now.getDate() + 30);
	
	$scope.datumVal = now;
	
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
					idArtikla : response.data.id
				};
			$scope.stavke.push(stavkaZaDodavanje);
			$scope.stavkeSize = $scope.stavke.length;
			$scope.omogucenaIzmena = true;
			$scope.omogucenoBrisanje = true;
			$scope.omogucenoDodavanje = false;

		});
	}
	
	
	$scope.unesiKolicinu = function() {
			magacinService.findByPreduzecePib($scope.pib).then(function(response){
				magacinService.nadjiMagacinskuKarticuArtikla(response.data.id, $scope.sifraZaPretragu).then(function(response){
					if((response.data.pocStanjeKol + response.data.prometUlKol - response.data.prometIzKol) >= $scope.kolicina) {
						
					}
					else {
						
					}
				});
			});	
	}
	
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
				$scope.jmArtikla = response.data.jedMere;
				if ($scope.nazArtikla !== undefined) {
					
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
		if ($scope.bpselected[0] === undefined) {
			$scope.knaziv = ""; 
			$scope.kadresa = ""; 
			$scope.kpib = ""; 
			$scope.kmbr = ""; 
			$scope.kracun = ""; 
		}
		else {
			
			$scope.knaziv = $scope.bpselected[0].company2.name; 
			$scope.kadresa = $scope.bpselected[0].company2.address;
			$scope.kpib = $scope.bpselected[0].company2.pib;
			$scope.kmbr = $scope.bpselected[0].company2.cidnumber;
			$scope.kracun = $scope.bpselected[0].company2.account;
			
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
			
			businessPartnerService.getAllBusinessPartners($scope.selected[0].id).then(function(response){
				console.log(response.data[0].company2);
				$scope.businessPartners = response.data;
					
			});
			
			$scope.knaziv = ""; 
			$scope.kadresa = ""; 
			$scope.kpib = ""; 
			$scope.kmbr = ""; 
			$scope.kracun = ""; 
			
		}
	}
	
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