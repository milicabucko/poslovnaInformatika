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
	.when("/listaSvihNarudzbenica", {
		templateUrl : "views/listaSvihNarudzbenica.html",
		controller : 'listaSvihNarudzbenicaController'
	})
	
	.when("/pregledMagacina", {
		templateUrl : "views/pregledMagacina.html",
		controller : 'pregledMagacinaController'
	})
	.when("/pregledStopa", {
		templateUrl : "views/pregledStopa.html",
		controller : 'pregledStopaController'
	})
	.when("/pregledVrstiPDVa", {
		templateUrl : "views/pregledVrstiPDVa.html",
		controller : 'pregledVrstiPDVaController'
	})
	.when("/stopa",{
		templateUrl : "views/stopa.html",
		controller : 'stopaController'
	})
	.when("/vrsta",{
		templateUrl : "views/vrsta.html",
		controller : 'vrstaController'
	})
	.when("/grupa",{
		templateUrl : "views/grupa.html",
		controller : 'grupaArtikalaController'
	})
	.when("/grupeArtikala",{
		templateUrl : "views/pregledGrupa.html",
		controller : 'pregledGrupaController'
	})
	.when("/jediniceMere",{
		templateUrl : "views/pregledJedinicaMere.html",
		controller : 'pregledJedinicaMereController'
	})
	.when("/jedinicaMere",{
		templateUrl : "views/jedinicaMere.html",
		controller : 'pregledJedinicaMereController'
	})
	.when("/artikal",{
		templateUrl : "views/artikal.html",
		controller : 'artikalController'
	})
	.when("/poslovnaGodina",{
		templateUrl : "views/poslovnaGodina.html",
		controller : 'poslovnaGodinaController'
	})
	.when("/home",{
		templateUrl : "views/home.html",
		controller : 'homeController'
	});
	
});