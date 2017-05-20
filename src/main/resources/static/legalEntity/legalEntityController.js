var app = angular.module('legalEntity.controllers',[]);

app.controller('legalEntityController',['$scope','legalEntityService','$location',
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