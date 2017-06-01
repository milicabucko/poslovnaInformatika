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
<<<<<<< HEAD
		.when("/listaSvihPreduzecaPP",{
		templateUrl : "views/listaSvihPreduzecaPP.html",
		controller : 'listaSvihPreduzecaPPController'
=======
	.when("/magacin", {
		templateUrl : "views/magacin.html",
		controller : 'magacinController'
>>>>>>> c1a3924dcdb82b54c0b19ca200a7b2054e222954
	})
	.when("/home",{
		templateUrl : "views/home.html",
		controller : 'homeController'
	});
	
});