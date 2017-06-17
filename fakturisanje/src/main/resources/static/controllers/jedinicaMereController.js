app.controller('jedinicaMereController',['$scope', '$location', '$mdDialog', 'companyService', '$timeout', 'jediniceMereService','authenticationService', function($scope, $location, $mdDialog, companyService, $timeout, jediniceMereService, authenticationService){
	$scope.user = authenticationService.getUser();
	$scope.authService = authenticationService;
	
	var urlParams = $location.search();
	console.log(urlParams.companyId + " url param");
	var idToSend = $scope.user.company.id;
	var idJedinice = urlParams.jedinicaID;
	
	if(idJedinice != null){
		jediniceMereService.findJedinicu(idJedinice).then(function(response){
			$scope.jedinicaa = response.data;
		});
	}
	
	$scope.kreirajJedinicuMere = function(){
		if(!("undefined" === typeof $scope.jedinicaa)){
			$scope.jedinica.id = $scope.jedinicaa.id;
			jediniceMereService.updateJedinicu($scope.jedinica).then(function(response){
				$location.path('/jediniceMere');
			});
		}else{
		jediniceMereService.createJedinicu($scope.jedinica, idToSend).then(function(response){
			$location.path('/jediniceMere');
		 
		});
	}
	}
	
	
}]);