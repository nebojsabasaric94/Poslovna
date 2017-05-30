var app = angular.module('legalEntity.controllers',[]);

app.controller('legalEntityController',['$scope','legalEntityService','$location',
	function($scope,service,$location){

	
	$scope.searchEntity = {id : null,naziv_klijenta:"",skraceni_naziv_klijenta:"", maticni_broj:"", pib:"",businessActivityCode:null,
			nadlezni_poreski_organ_za_klijenta:"", naziv_organa:"",address:"" ,phone : "",email:"",addressForStatements:"",fax:"",
			emailStatements:false,firstName:"",lastName:"",jmbg:"",typeOfClient:"Pravno lice",residence:null
			};	
	$scope.entity = {id : null,naziv_klijenta:"",skraceni_naziv_klijenta:"", maticni_broj:"", pib:"",businessActivityCode:null,
			nadlezni_poreski_organ_za_klijenta:"", naziv_organa:"",address:"" ,phone : "",email:"",addressForStatements:"",fax:"",
			emailStatements:false,firstName:"",lastName:"",jmbg:"",typeOfClient:"Pravno lice",residence:null
			};	
	
	
	findAll();
	function findAll() {
		
		var nextFilterBussinesActivity = sessionStorage.getItem("nextFilterBussinesActivity");
		sessionStorage.removeItem("nextFilterBussinesActivity");
		
		
		if(nextFilterBussinesActivity != null){
			service.nextFilterBussinesActivity(nextFilterBussinesActivity).then(
				function(response){
					$scope.entities = response.data;
				}
			)
		
		}  else {
			service.findAll().then(
				function(response) {
				   $scope.entities = response.data;
			})
		}
}	

	$scope.delete = function(){
		service.delete($scope.selectedEntity)

		.then(function(response){
			
		})
	}
	$scope.idSelectedEntity = null;
	
	
	$scope.setSelected = function(selectedEntity){
		$scope.selectedEntity = selectedEntity;
	}
	
	
	$scope.firstone = function(){
		$scope.setSelected($scope.entities[0]);
	}
	
	
	$scope.add = function(){
		service.save($scope.entity).then(function(response) {
			findAll();
			$location.path('legalEntity');
		},
		function(response){
			alert("Dodavanje neuspesno");
		}
		);
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
			$scope.selectedEntity = null;

		},
		function(response){
			
		})
	}
	
	$scope.showModalPlaceSearch = function(){
		var modal = document.getElementById('modalPlaceSearch');
		modal.style.display = "block";		
	}
	$scope.closeModalPlaceSearch = function(){
		var modal = document.getElementById('modalPlaceSearch');
		modal.style.display  = "none";
	}
	$scope.showMoodalBusinessActivitySearch = function(){
		var modal = document.getElementById('modalBusinessActivitySearch');
		modal.style.display = "block";		
	}
	$scope.closeModalBusinessActivitySearch = function(){
		var modal = document.getElementById('modalBusinessActivitySearch');
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
	$scope.findAllBusinessActivites = function(){
		service.findAllBusinessActivities()
		.then(function(response){
			$scope.businessActivities = response.data;
		},
		function(response){
			
		})
	}
	$scope.setSelectedPlaceSearch = function(place){
		$scope.searchEntity.residence = place;
	}
	$scope.setSelectedBusinessActivitySearch = function(businessActivityCode){
		$scope.searchEntity.businessActivityCode = businessActivityCode;
	}	
	$scope.setSelectedPlace = function(place){
		$scope.entity.residence = place;
	}
	$scope.setSelectedBusinessActivity = function(businessActivityCode){
		$scope.entity.businessActivityCode = businessActivityCode;
	}
	
	
	$scope.deselect = function(){
		$scope.selectedEntity = null;
		$scope.searchEntity = {id : null,naziv_klijenta:"",skraceni_naziv_klijenta:"",maticni_broj:"",pib:"",businessActivityCode:null,
				nadlezni_poreski_organ_za_klijenta:"",naziv_organa:"",address:"" ,phone : "",email:"",addressForStatements:"",fax:"",
				emailStatements:false,firstName:"",lastName:"",jmbg:"",typeOfClient:"Pravno lice",residence:null};
	}
	$scope.refresh = function(){
		$scope.selectedEntity = null;
		$scope.searchEntity = {id : null,naziv_klijenta:"",skraceni_naziv_klijenta:"",maticni_broj:"",pib:"",businessActivityCode:null,
				nadlezni_poreski_organ_za_klijenta:"",naziv_organa:"",address:"" ,phone : "",email:"",addressForStatements:"",fax:"",
				emailStatements:false,firstName:"",lastName:"",jmbg:"",typeOfClient:"Pravno lice",residence:null};
		findAll();
	}	
	
	$scope.back = function(){
		if(sessionStorage.getItem("backFilterBussinesActivity") != null){
			sessionStorage.removeItem("backFilterBussinesActivity");
			$location.path("/businessActivityCode");
		} else {
			return;
		}
	}
	
	$scope.next = function(){
		if(!($scope.selectedEntity))
			return;
		sessionStorage.setItem("nextFilterLegalEntity", $scope.selectedEntity.id);
		sessionStorage.setItem("backFilterLegalEntity", $scope.entities);
		$location.path("/legalEntityAccount");
	}
	
}])
