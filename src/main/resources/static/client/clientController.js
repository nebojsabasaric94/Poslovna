var app = angular.module('client.controllers',[]);

app.controller('clientController',['$scope','clientService','$location',
	function($scope,service,$location){

	$scope.searchEntity = {id : null,address:"" ,phone : "",email:"",addressForStatements:"",
			emailStatements:false,firstName:"",lastName:"",jmbg:"",typeOfClient:"Fizicko lice",residence:null};

	
	findAll();
	function findAll() {
		service.findAll()
		.then(function(response) {
			$scope.entities = response.data;
		},
		function(response){
			
		});
	}	
	$scope.setSelected = function(selectedEntity){
		$scope.selectedEntity = selectedEntity;
	}	
	$scope.search = function(){
		service.search($scope.searchEntity)
		.then(function(response){
			$scope.entities = response.data; 

		},
		function(response){
			
		})
	}
	$scope.showModalSearch = function(){
		var modal = document.getElementById('myModalSearch');
		modal.style.display = "block";		
	}
	$scope.closeModal = function(){
		var modal = document.getElementById('myModalSearch');
		modal.style.display  = "none";

	}
	$scope.findAllPlaces = function(){
		service.findAllPlaces()
		.then(function(response){
			$scope.places = response.data;
		},
		function(response){
			
		})
	}
	$scope.setSelectedPlaceSearch = function(place){
		$scope.searchEntity.residence = place;
	}	
	$scope.deselect = function(){
		$scope.selectedEntity = null;
		$scope.searchEntity = {id : null,address:"" ,phone : "",email:"",addressForStatements:"",
				emailStatements:false,firstName:"",lastName:"",jmbg:"",typeOfClient:"Fizicko lice",residence:null};
	}
	$scope.refresh = function(){
		$scope.selectedEntity = null;
		$scope.searchEntity = {id : null,address:"" ,phone : "",email:"",addressForStatements:"",
				emailStatements:false,firstName:"",lastName:"",jmbg:"",typeOfClient:"Fizicko lice",residence:null};
		findAll();
	}	
}])