var app = angular.module('client.controllers',[]);

app.controller('clientController',['$scope','clientService','$location',
	function($scope,service,$location){

	$scope.searchEntity = {id : null,address:"" ,phone : "",email:"",addressForStatements:"",
			emailStatements:false,firstName:"",lastName:"",jmbg:"",typeOfClient:"Fizicko lice",residence:null};

	
	findAll();
	function findAll() {

		var nextFilter = sessionStorage.getItem("nextFilter");
		sessionStorage.removeItem("nextFilter");
		
		if(nextFilter == null){
			service.findAll().then(
				function(response) {
					$scope.entities = response.data;
				});
		} else {
			service.next(nextFilter).then(
				function(response){
					$scope.entities = response.data;
				}
			)
		}
		
	}	
	$scope.setSelected = function(selectedEntity){
		$scope.selectedEntity = selectedEntity;
	}	
	$scope.firstone = function(){
		$scope.setSelected($scope.entities[0]);
	}
	
	$scope.previous = function(selectedEntity){
		var index = -1;
		
		for(i = 0 ; i  < $scope.entities.length;i++){
			if($scope.entities[i].id == $scope.selectedEntity.id)
				index = i;
		}
		if(index != 0)				
			$scope.setSelected($scope.entities[index-1])
		else	
			$scope.setSelected($scope.entities[$scope.entities.length-1])
			
	}
	
	
	$scope.nextNavigation = function(selectedEntity){
		
		var index = -1;
		
		for(i = 0 ; i  < $scope.entities.length;i++){
			if($scope.entities[i].id == $scope.selectedEntity.id)
				index = i;
		}
			
		if(index == $scope.entities.length-1)
			$scope.setSelected($scope.entities[0])
		else				
			$scope.setSelected($scope.entities[index+1])
	}
	
	$scope.lastone = function(){
		$scope.setSelected($scope.entities[$scope.entities.length-1])
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
	
	$scope.back = function(){
		var backFilter = sessionStorage.getItem("backFilterPlace");
		sessionStorage.removeItem("backFilterPlace");
		if(backFilter == null)
			return;
		$location.path("/place");
	
	}
	
	$scope.next = function(){
		if(!($scope.selectedEntity))
			return;
		sessionStorage.setItem("nextFilterClient", $scope.selectedEntity.id);
		sessionStorage.setItem("backFilterClient", $scope.entities);
		$location.path('/legalEntityAccount');
	
		
	}
	
}])
