var app = angular.module('client.controllers',[]);

app.controller('clientController',['$scope','clientService','$location',
	function($scope,service,$location){

	findAll();
	function findAll() {

	//	countryService.findAll().then(function(response) {

		service.findAll()
		.then(function(response) {
			$scope.entities = response.data;
		},
		function(response){
			
		});
	}	
	
}])
