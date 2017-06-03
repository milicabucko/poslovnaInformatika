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
	.when("/primka",{
		templateUrl : "views/primka.html",
		controller : 'primkaController'
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

		.when("/listaSvihPreduzecaPP",{
		templateUrl : "views/listaSvihPreduzecaPP.html",
		controller : 'listaSvihPreduzecaPPController'
		})

	.when("/listaSvihPreduzecaPP",{
		templateUrl : "views/listaSvihPreduzecaPP.html",
		controller : 'listaSvihPreduzecaPPController'
	})

	.when("/magacin", {
		templateUrl : "views/magacin.html",
		controller : 'magacinController'
	})

	.when("/pregledCenovnika", {
		templateUrl : "views/pregledCenovnika.html",
		controller : 'pregledCenovnikaController'
	})
	.when("/pregledMagacina", {
		templateUrl : "views/pregledMagacina.html",
		controller : 'pregledMagacinaController'
	})
	.when("/home",{
		templateUrl : "views/home.html",
		controller : 'homeController'
	});
	
});