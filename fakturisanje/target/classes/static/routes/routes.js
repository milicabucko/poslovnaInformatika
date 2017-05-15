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
	.when("/home",{
		templateUrl : "views/home.html",
		controller : 'homeController'
	});
	
});