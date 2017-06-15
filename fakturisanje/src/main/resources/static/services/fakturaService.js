app.factory('fakturaService', function fakturaService($http){
	
	
	fakturaService.posaljiPrimku = function(izdId, kupId, brojDokumenta, statusDokumenta, datumDokumenta, datumValute){
		return $http({
			method: 'POST',
			url: 'api/faktura/sacuvajFakturu/' + izdId + '/' + kupId,
			data: {
				"brojDokumenta" : brojDokumenta,
				"statusDokumenta" : statusDokumenta,
				"datumDokumenta" : datumDokumenta,
				"datumValute" : datumValute,
				"vrstaDokumenta" : "PR"
			}
		});
	}
	
	fakturaService.posaljiFakturu = function(izdId, kupId, brojDokumenta, statusDokumenta, datumDokumenta, datumValute, ukupnoZaPlacanje){
		return $http({
			method: 'POST',
			url: 'api/faktura/sacuvajFakturu/' + izdId + '/' + kupId,
			data: {
				"brojDokumenta" : brojDokumenta,
				"statusDokumenta" : statusDokumenta,
				"datumDokumenta" : datumDokumenta,
				"datumValute" : datumValute,
				"vrstaDokumenta" : "FO",
				"ukupnoZaPlacanje" : ukupnoZaPlacanje
			}
		});
	}
	
	fakturaService.izvestajFaktura = function(fakturaId, izdavaocId, kupacId) {
		return $http({
			method: 'GET',
			url: 'api/faktura/izvestaj/' + fakturaId + '/' + izdavaocId + '/' + kupacId
		});
	}
	
	fakturaService.exportToXML = function(fakturaId) {
		return $http({
			method: 'GET',
			url: 'api/faktura/exportToXML/' + fakturaId
		});
	}
	
	fakturaService.posaljiNarudzbenicu = function(izdId, kupId, brojDokumenta, statusDokumenta, datumDokumenta, datumValute){
		return $http({
			method: 'POST',
			url: 'api/faktura/sacuvajFakturu/' + izdId + '/' + kupId,
			data: {
				"brojDokumenta" : brojDokumenta,
				"statusDokumenta" : statusDokumenta,
				"datumDokumenta" : datumDokumenta,
				"datumValute" : datumValute,
				"vrstaDokumenta" : "NAR"
			}
		});
	}
	
	fakturaService.promeniStatus = function(fakturaId, status, datumKnjizenja){
		return $http({
			method: 'POST',
			url: 'api/faktura/promeniStatusDokumenta/' + fakturaId,
			data: {
				"statusDokumenta" : status,
				"datumKnjizenja" : datumKnjizenja
			}
		});
	}
	
	fakturaService.obrisiNarudzbenicu = function(brojDok){
		return $http({
			method: 'POST',
			url: 'api/faktura/obrisiNarudzbenicu/' + brojDok
		});
	}
	
	
	fakturaService.nadjiSledeciBrojDokumenta = function(){
		return $http({
			method: 'GET',
			url: 'api/faktura/nadjiSledeciBrojDokumenta'
		});
	}
	
	fakturaService.nadjiPoBrojuDokumenta = function(brojDokumenta){
		return $http({
			method: 'GET',
			url: 'api/faktura/nadjiPoBrojuDokumenta/' + brojDokumenta
		});
	}
	
	fakturaService.nadjiPoBrojuNaruzbenice = function(brojDokumenta){
		return $http({
			method: 'GET',
			url: 'api/faktura/nadjiPoBrojuNaruzbenice/' + brojDokumenta
		});
	}

	
	fakturaService.sveFakture = function(){
		return $http({
			method: 'GET',
			url: 'api/faktura/sveFakture'
		});
	}
	
	fakturaService.sveNaruzbenice = function() {
		return $http({
			method: 'GET',
			url: 'api/faktura/sveNarudzbenice'
		})
	}
	
	
	
	
	return fakturaService;
	
});