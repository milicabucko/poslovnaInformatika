app.controller('listaSvihNarudzbenicaController',['$scope', '$location', '$mdDialog', 'companyService', 'businessPartnerService', 'artikalService', 'fakturaService', 'stavkaDokumentaService', 'magacinService', 'stopaPDVaService', function($scope, $location, $mdDialog, companyService, businessPartnerService, artikalService, fakturaService, stavkaDokumentaService, magacinService, stopaPDVaService){
	
	
		
	$scope.statusDok = "";
	$scope.datumDok = null;
	$scope.datumVal = null;
	$scope.datumKnj = null;
	$scope.brDok = "";
	
	
	$scope.stavke = [];
	$scope.stavkeSize = 0;
	
	$scope.items = [];
	$scope.itemSize = [];
	
	
	
	$scope.pretraziPoBrojuNarudzbenice = function() {

		if($scope.brojZaPretragu == "" || $scope.brojZaPretragu == null) {
			fakturaService.sveNaruzbenice().then(function(response){ 
				 $scope.items = response.data;
				 $scope.itemsSize = $scope.items.length;
			});
		}
		else {	
			fakturaService.nadjiPoBrojuNaruzbenice($scope.brojZaPretragu).then(function(response){ 
				$scope.items = response.data;
				$scope.itemsSize = $scope.items.length;
			});
		}
	}
	
	
	$scope.onSelectEvent = function() {
		if ($scope.selected[0] === undefined) {
			
			$scope.stavke = [];
			$scope.stavkeSize = 0;
			
			$scope.brDok = "";
			$scope.statusDok = "";
			$scope.datumDok = null;
			$scope.datumVal = null;
			$scope.datumKnj = null;
			
			$scope.showObracunaj = false;
			$scope.showStorniraj = false;
			
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
			
			$scope.brDok = $scope.selected[0].brojDokumenta;
			$scope.statusDok = $scope.selected[0].statusDokumenta;
			$scope.datumDok = $scope.selected[0].datumDokumenta;
			$scope.datumVal = $scope.selected[0].datumValute;
			
			$scope.naziv = $scope.selected[0].izdavaocRacuna.name; 
			$scope.adresa = $scope.selected[0].izdavaocRacuna.address;
			$scope.pib = $scope.selected[0].izdavaocRacuna.pib;
			$scope.mbr = $scope.selected[0].izdavaocRacuna.cidnumber;
			$scope.racun = $scope.selected[0].izdavaocRacuna.account;
			
			$scope.knaziv = $scope.selected[0].kupac.name; 
			$scope.kadresa = $scope.selected[0].kupac.address;
			$scope.kpib = $scope.selected[0].kupac.pib;
			$scope.kmbr = $scope.selected[0].kupac.cidnumber;
			$scope.kracun = $scope.selected[0].kupac.account; 
			
			$scope.stavke = $scope.selected[0].stavkeDokumenta;
			$scope.stavkeSize = $scope.selected[0].stavkeDokumenta.length;
			
			
		}
	}
	
	
	$scope.onSelectEventStavka = function() {
		if ($scope.selektovaneStavke[0] === undefined) {
			$scope.nazArtikla = "";
			$scope.sifraArtikla = "";
		}
		else {
			
			$scope.nazArtikla = $scope.selektovaneStavke[0].artikal.naziv;
			$scope.sifraArtikla = $scope.selektovaneStavke[0].artikal.sifra;
		}
	}
	
	$scope.omogucenaIzmena = true;
	
	$scope.izmeniStavku = function() {
		for(var i = 0; i < $scope.stavke.length; i++) {
			if ($scope.sifraArtikla == $scope.stavke[i].artikal.sifra) {
				$scope.stavke[i].rabat = $scope.rabat;
				$scope.stavke[i].stopaPDV = $scope.stopaPdva.procenatPDVa;
				$scope.stavke[i].iznosPDV = ($scope.stopaPdva.procenatPDVa/100) * $scope.stavke[i].cena * $scope.stavke[i].kolicina;
				$scope.stavke[i].ukupno = (($scope.stavke[i].cena * $scope.stavke[i].kolicina) + (($scope.stopaPdva.procenatPDVa/100) * $scope.stavke[i].cena * $scope.stavke[i].kolicina)) - (($scope.rabat/100) * (($scope.stavke[i].cena * $scope.stavke[i].kolicina) + (($scope.stopaPdva.procenatPDVa/100) * $scope.stavke[i].cena * $scope.stavke[i].kolicina)))
				$scope.omogucenaIzmena = true;
				$scope.stavkeSize = $scope.stavke.length;
				return;
			}
		}
	}
	
	$scope.posaljiFakturu = function() {

		var ukupnoZaPlacanje = 0;
		for(var i = 0; i < $scope.stavke.length; i++) {
			ukupnoZaPlacanje += $scope.stavke[i].ukupno;
		}
		
		
		fakturaService.posaljiFakturu($scope.selected[0].kupac.id, $scope.selected[0].izdavaocRacuna.id, $scope.brDok, "poslata", $scope.datumDok, $scope.datumVal, ukupnoZaPlacanje, $scope.selected[0].id).then(function(response){ 
			
			$mdDialog.show(
					$mdDialog.alert()
				    .clickOutsideToClose(true)
				    .title('Obavestenje')
				    .textContent('Faktura je generisana na osnovu narudzbenice.')
				    .ok('OK')
			);
			
			for(var i = 0; i < $scope.stavke.length; i++) {
				
				stavkaDokumentaService.sacuvajStavku(response.data.id, $scope.stavke[i].artikal.id, $scope.stavke[i].kolicina, $scope.stavke[i].cena, $scope.stavke[i].rabat, $scope.stavke[i].stopaPDV).then(function(response){ 
				
				});
				
			}
			
		});
	}
	
	
	fakturaService.sveNaruzbenice().then(function(response){
		 
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
		
		stopaPDVaService.getAllStope().then(function(response){
			 
			 $scope.stope = response.data;
		 
		});
			
	 
}]);