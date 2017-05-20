var app = angular.module('legalEntityAccount.controllers',[]);
app.controller('legalEntityAccountController',['$scope','legalEntityAccountService','$location',
	function($scope,service,$location){
	
	
	findAll();
	function findAll() {
		service.findAll().then(function(response) {
			$scope.entities = response.data;
		},
		function(response){
			
		});
	}
}])