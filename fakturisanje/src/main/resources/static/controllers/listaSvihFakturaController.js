app.controller('listaSvihFakturaController',['$scope', '$location', '$mdDialog', 'companyService', 'businessPartnerService', 'artikalService', 'fakturaService', 'stavkaDokumentaService', 'magacinService', function($scope, $location, $mdDialog, companyService, businessPartnerService, artikalService, fakturaService, stavkaDokumentaService, magacinService){
	
	$scope.showObracunaj = false;
	$scope.showStorniraj = false;
	
	
	$scope.obracunajFakturu = function() {
		
		if ($scope.selected[0].vrstaDokumenta == "PR") {
			alert('Obracunavam primku');
			fakturaService.promeniStatus($scope.selected[0].id, "obracunata").then(function(response){ 
				
				for(var i = 0; i < $scope.stavke.length; i++) {
			
					magacinService.dodajAnalitikuMKPrimka($scope.stavke[i].artikal.id, $scope.kpib, $scope.pib, $scope.stavke[i].id).then(function(response){ 
				
					});
			
				}
			
			});
		}
		else {
			alert('Obracunavam fakturu');
			fakturaService.promeniStatus($scope.selected[0].id, "obracunata").then(function(response){ 
			
				for(var i = 0; i < $scope.stavke.length; i++) {
			
					magacinService.dodajAnalitikuMK($scope.stavke[i].artikal.id, $scope.pib, $scope.kpib, $scope.stavke[i].id).then(function(response){ 
				
					});
			
				}
			
			});
		}
	}
	
	
	$scope.stornirajFakturu = function() {
		
		if ($scope.selected[0].vrstaDokumenta == "PR") {
			alert('Storniram primku');
			fakturaService.promeniStatus($scope.selected[0].id, "stornirana").then(function(response){ 
				
				for(var i = 0; i < $scope.stavke.length; i++) {
			
					magacinService.dodajAnalitikuMKPrimka($scope.stavke[i].artikal.id, $scope.pib, $scope.kpib, $scope.stavke[i].id).then(function(response){ 
				
					});
			
				}
			
			});
		}
		else {
			fakturaService.promeniStatus($scope.selected[0].id, "stornirana").then(function(response){ 
		
				for(var i = 0; i < $scope.stavke.length; i++) {
				
					magacinService.dodajAnalitikuMK($scope.stavke[i].artikal.id, $scope.kpib, $scope.pib, $scope.stavke[i].id).then(function(response){ 
				
					});
			
				}
			
			});
		}
	}
		
		
	$scope.statusDok = "";
	$scope.datumDok = null;
	$scope.datumVal = null;
	$scope.datumKnj = null;
	$scope.brDok = "";
	
	
	$scope.stavke = [];
	$scope.stavkeSize = 0;
	
	$scope.items = [];
	$scope.itemSize = [];
	
	
	
	$scope.pretraziPoBrojuDokumenta = function() {

		if($scope.brojZaPretragu == "" || $scope.brojZaPretragu == null) {
			fakturaService.sveFakture().then(function(response){ 
				 $scope.items = response.data;
				 $scope.itemsSize = $scope.items.length;
			});
		}
		else {	
			fakturaService.nadjiPoBrojuDokumenta($scope.brojZaPretragu).then(function(response){ 
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
			
			if ($scope.selected[0].statusDokumenta == "poslata") {
				$scope.showObracunaj = true;
				$scope.showStorniraj = false;
			}else if ($scope.selected[0].statusDokumenta == "obracunata") {
				$scope.showObracunaj = false;
				$scope.showStorniraj = true;
			}else {
				$scope.showObracunaj = false;
				$scope.showStorniraj = false;
			}
			
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
	
	fakturaService.sveFakture().then(function(response){
		 
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
			
	 
}]);