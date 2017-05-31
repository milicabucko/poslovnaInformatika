app.config(function($routeProvider){
	$routeProvider
	.when("/",{
		templateUrl : "views/login.html",
		controller : 'loginController'
	})
	.when("/businessDocument",{
		templateUrl : "views/businessDocument.html",
		controller : 'businessDocumentController'
	})
	.when("/pregledPreduzeca",{
		templateUrl : "views/pregledPreduzeca.html",
		controller : 'pregledPreduzecaController'
	})
	.when("/faktura",{
		templateUrl : "views/faktura.html",
		controller : 'fakturaController'
	})
	.when("/listaSvihFaktura",{
		templateUrl : "views/listaSvihFaktura.html",
		controller : 'listaSvihFakturaController'
	})
	.when("/narudzbenica",{
		templateUrl : "views/narudzbenica.html",
		controller : 'narudzbenicaController'
	})
	.when("/cenovnik",{
		templateUrl : "views/cenovnik.html",
		controller : 'cenovnikController'
	})
	.when("/preduzece",{
		templateUrl : "views/preduzece.html",
		controller : 'preduzeceController'
	})
	.when("/home",{
		templateUrl : "views/home.html",
		controller : 'homeController'
	});
	
});