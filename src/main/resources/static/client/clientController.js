var app = angular.module('client.controllers',[]);

app.controller('clientController',['$scope','clientService','$location',
	function($scope,service,$location){

	$scope.searchEntity = {id : null,address:"" ,phone : "",email:"",addressForStatements:"",
			emailStatements:"",firstName:"",lastName:"",jmbg:"",typeOfClient:"Fizicko lice",residence:null};
	$scope.entity = {id : null,address:"" ,phone : "",email:"",addressForStatements:"",
			emailStatements:false,firstName:"",lastName:"",jmbg:"",typeOfClient:"Fizicko lice",residence:null};
	$scope.updatedEntity={};
	
	//$scope.updatedEntity=new Object;
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
		$scope.showUpdateForm = true;
		$scope.selectedEntity = selectedEntity;
		$scope.updatedEntity.id=$scope.selectedEntity.id;
		$scope.updatedEntity.firstName=selectedEntity.firstName;
		$scope.updatedEntity.lastName=selectedEntity.lastName;
		$scope.updatedEntity.jmbg=selectedEntity.jmbg;
		$scope.updatedEntity.address=selectedEntity.address;
		$scope.updatedEntity.phone=selectedEntity.phone;
		$scope.updatedEntity.email=selectedEntity.email;
		$scope.updatedEntity.typeOfClient="Fizicko lice";
		$scope.updatedEntity.addressForStatements=selectedEntity.addressForStatements;
		$scope.updatedEntity.residence=selectedEntity.residence;
		
		//i ostali
		$scope.showUpdateForm = true;
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
	
	$scope.add = function(){
		service.save($scope.entity).then(function(response) {
			findAll();
			$location.path('client');
		},
		function(response){
			alert("Dodavanje neuspesno");
		}
		);
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
	
	$scope.saveChanges = function(){
		
		service.update($scope.updatedEntity).then(function(response){
			//function(response){
				
				//$window.location.reload();
			//}
			for(i = 0 ; i < $scope.entities.length;i++){
				if($scope.entities[i].id == $scope.updatedEntity.id){
					$scope.entities[i] = response.data;
				}
			}
			$scope.showUpdateForm = false;
		},
		function(response){
					
		})
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
	$scope.setSelectedPlace = function(place){
		$scope.entity.residence = place;
	}
	$scope.setSelectedPlaceUpdate = function(place){
		$scope.updatedEntity.residence = place;
		$scope.searchEntity.residence = place;
		$scope.entity.residence = place;
	}
	
	$scope.deselect = function(){
		$scope.selectedEntity = null;
		$scope.showUpdateForm = false;
		$scope.searchEntity = {id : null,address:"" ,phone : "",email:"",addressForStatements:"",
				emailStatements:"",firstName:"",lastName:"",jmbg:"",typeOfClient:"Fizicko lice",residence:null};
	}
	$scope.refresh = function(){
		$scope.selectedEntity = null;
		$scope.searchEntity = {id : null,address:"" ,phone : "",email:"",addressForStatements:"",
				emailStatements:"",firstName:"",lastName:"",jmbg:"",typeOfClient:"Fizicko lice",residence:null};
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
