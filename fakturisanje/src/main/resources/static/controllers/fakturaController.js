app.controller('fakturaController',['$scope', '$location', '$mdDialog', 'companyService', 'businessPartnerService', 'artikalService', 'fakturaService', 'stavkaDokumentaService', 'magacinService', 'cenovnikService', 'stopaPDVaService', '$window','authenticationService', function($scope, $location, $mdDialog, companyService, businessPartnerService, artikalService, fakturaService, stavkaDokumentaService, magacinService, cenovnikService, stopaPDVaService, $window, authenticationService){
	
	$scope.user = authenticationService.getUser();
	$scope.authService = authenticationService;
	
	artikalService.nadjArtikleFirme($scope.user.company.id).then(function(response){
		
		$scope.artikli = response.data;
		$scope.artikliSize = response.data.length;
		
	});
	
	$scope.dodajArtikal = function(artikalSifra, artikalId){
		
		$scope.omogucenoDodavanje = false;
		$scope.omogucenaIzmena = false;
		$scope.omogucenoBrisanje = false;
		
		$scope.sifraZaPretragu = artikalSifra;
		$scope.pretraziPoSifriArtikla();
		//stopaPdva.procenatPDVa ovo da setujem 
		artikalService.getGrupuArtikla(artikalId).then(function(response){
			console.log(response.data.procenatPDVa);
			$scope.stopaPdva.procenatPDVa = response.data.procenatPDVa;
			
		});
		
	} 
	
	
	stopaPDVaService.getAllStope().then(function(response){
		 
		 $scope.stope = response.data;
	 
	});
	
	$scope.posaljiFakturu = function() {
		console.log("posiljalac: " + $scope.user.company.id + " kupac: " + $scope.bpselected[0].company2.id);
		var ukupnoZaPlacanje = 0;
		for(var i = 0; i < $scope.stavke.length; i++) {
			ukupnoZaPlacanje += $scope.stavke[i].ukupno;
		}
		console.log(ukupnoZaPlacanje);
		fakturaService.posaljiFakturu($scope.user.company.id, $scope.bpselected[0].company2.id, $scope.brDok, "poslata", $scope.datumDok, $scope.datumVal, ukupnoZaPlacanje, -1).then(function(response){ 
			if(response.data.statusDokumenta == "nevazeca"){
				$mdDialog.show(
						$mdDialog.alert()
					    .clickOutsideToClose(true)
					    .title('Greska')
					    .textContent('Ne postoji aktivna poslovna godina izdavaoca racuna.')
					    .ok('OK')
				);
			}else{
				for(var i = 0; i < $scope.stavke.length; i++) {
					console.log($scope.stavke[i].cenaPoJed);
					stavkaDokumentaService.sacuvajStavku(response.data.id, $scope.stavke[i].idArtikla, $scope.stavke[i].kolicina, $scope.stavke[i].cenaPoJed, $scope.stavke[i].rabat, $scope.stavke[i].stopaPDV).then(function(response){ 
						/*console.log(response.data);
						magacinService.dodajAnalitikuMK(response.data.artikal.id, $scope.pib, $scope.kpib, response.data.id).then(function(response){ 						
						});*/
					});
				}
				
				$mdDialog.show(
						$mdDialog.alert()
					    .clickOutsideToClose(true)
					    .title('Obavestenje')
					    .textContent('Faktura je poslata.')
					    .ok('OK')
				);
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
				$scope.stavke[i].rabat = $scope.rabat;
				$scope.stavke[i].stopaPDV = $scope.stopaPdva.procenatPDVa;
				$scope.stavke[i].iznosPDV = ($scope.stopaPdva.procenatPDVa/100) * $scope.cena * $scope.kolicina;
				$scope.stavke[i].ukupno = (($scope.cena * $scope.kolicina) + (($scope.stopaPdva.procenatPDVa/100) * $scope.cena * $scope.kolicina)) - (($scope.rabat/100) * (($scope.cena * $scope.kolicina) + (($scope.stopaPdva.procenatPDVa/100) * $scope.cena * $scope.kolicina)))
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
					cenaPoJed : $scope.cena,
					rabat : $scope.rabat,	//vrednost = kol * cena         vrednost *                  procenat / 100			
					porOsnovica : $scope.cena * $scope.kolicina -  ($scope.cena * $scope.kolicina) * $scope.rabat / 100,
					stopaPDV : $scope.stopaPdva.procenatPDVa,
					iznosPDV : ($scope.stopaPdva.procenatPDVa/100) * (($scope.cena * $scope.kolicina) -  ($scope.cena * $scope.kolicina) * $scope.rabat / 100),
					ukupno : (($scope.cena * $scope.kolicina) + (($scope.stopaPdva.procenatPDVa/100) * (($scope.cena * $scope.kolicina) -  ($scope.cena * $scope.kolicina) * $scope.rabat / 100)) - (($scope.cena * $scope.kolicina) * $scope.rabat / 100))
								//vrednost                         // iznos pdv																																//iznos rabata													
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
				
				if (response.data != "") {
					$scope.jmArtikla = response.data.jedinicaMere.oznakaJedinice;
				}
				else {
					$scope.jmArtikla = "";
					$scope.cena = "";
				}
				if ($scope.nazArtikla !== undefined) {
					
					//za cenu artikla
					cenovnikService.posaljiDatum($scope.pib, $scope.datumDok).then(function(response){ 
						var cenovnik = response.data;
						console.log(cenovnik);
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
	
	//PROMENI OVO ZA PARTNERA!!!
	
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
	
	$scope.naziv = $scope.user.company.name; 
	$scope.adresa = $scope.user.company.address;
	$scope.pib = $scope.user.company.pib;
	$scope.mbr = $scope.user.company.cidnumber;
	$scope.racun = $scope.user.company.account;
	
	businessPartnerService.getAllBusinessPartners($scope.user.company.id).then(function(response){
		console.log(response.data[0].company2);
		$scope.businessPartners = response.data;
		
			
	});
	
	$scope.knaziv = ""; 
	$scope.kadresa = ""; 
	$scope.kpib = ""; 
	$scope.kmbr = ""; 
	$scope.kracun = ""; 
	
	
//	$scope.onSelectEvent = function() {
//		
//		
//		if ($scope.selected[0] === undefined) {
//			$scope.businessPartners = [];
//			
//			$scope.naziv = ""; 
//			$scope.adresa = ""; 
//			$scope.pib = ""; 
//			$scope.mbr = ""; 
//			$scope.racun = ""; 
//			
//			$scope.knaziv = ""; 
//			$scope.kadresa = ""; 
//			$scope.kpib = ""; 
//			$scope.kmbr = ""; 
//			$scope.kracun = ""; 
//		}
//		else {
//			
//			$scope.naziv = $scope.selected[0].name; 
//			$scope.adresa = $scope.selected[0].address;
//			$scope.pib = $scope.selected[0].pib;
//			$scope.mbr = $scope.selected[0].cidnumber;
//			$scope.racun = $scope.selected[0].account;
//			
//			businessPartnerService.getAllBusinessPartners($scope.selected[0].id).then(function(response){
//				console.log(response.data[0].company2);
//				$scope.businessPartners = response.data;
//					
//			});
//			
//			$scope.knaziv = ""; 
//			$scope.kadresa = ""; 
//			$scope.kpib = ""; 
//			$scope.kmbr = ""; 
//			$scope.kracun = ""; 
//			
//		}
//	}
	
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
		$scope.selectedArtikal = [];
}]);