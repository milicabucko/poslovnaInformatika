app.controller('artikalController',['$scope', '$location', '$mdDialog', 'companyService', '$timeout','businessPartnerService', 'artikalService','jediniceMereService', function($scope, $location, $mdDialog, companyService, $timeout, businessPartnerService, artikalService, jediniceMereService){
	var urlParams = $location.search();
	var id = urlParams.companyID;
	
	jediniceMereService.getAllJedinice(id).then(function(response){
		$scope.jedinice = response.data;
	});
	
	
	$scope.kreirajArtikal = function(){
		
		console.log(urlParams.companyId + " url param");
		var idToSend = urlParams.grupaID;
		var idJedinice = $scope.artikal.jedinicaMere.id;
		artikalService.createArtikal($scope.artikal, idToSend, idJedinice).then(function(response){
			alert("dodato");
			
		});
	}
	
}]);