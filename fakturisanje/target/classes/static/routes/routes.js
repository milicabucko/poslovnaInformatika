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
	.when("/home",{
		templateUrl : "views/home.html",
		controller : 'homeController'
	});
	
});