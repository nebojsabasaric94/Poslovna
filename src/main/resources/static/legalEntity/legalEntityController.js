var app = angular.module('legalEntity.controllers',[]);

app.controller('legalEntityController',['$scope','legalEntityService','$location',
	function($scope,service,$location){

	
	$scope.searchEntity = {id : null,naziv_klijenta:"",skraceni_naziv_klijenta:"", maticni_broj:"", pib:"",businessActivityCode:null,
			nadlezni_poreski_organ_za_klijenta:"", naziv_organa:"",address:"" ,phone : "",email:"",addressForStatements:"",fax:"",
			emailStatements:false,firstName:"",lastName:"",jmbg:"",typeOfClient:"Pravno lice",residence:null
			};	
	
	
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

	$scope.idSelectedEntity = null;
	
	
	$scope.setSelected = function(selectedEntity){
		$scope.selectedEntity = selectedEntity;
	}
	
	
	$scope.firstone = function(){
		$scope.setSelected(1);
	}
	
	$scope.previous = function(selectedEntity){
		if($scope.selectedEntity != 1)
			$scope.setSelected($scope.selectedEntity-1);
		else
			$scope.setSelected($scope.entities.length);
			
	}
	
	
	$scope.nextNavigation = function(selectedEntity){
		if($scope.selectedEntity != $scope.entities.length )
			$scope.setSelected($scope.selectedEntity+1);
		else
			$scope.setSelected(1);
	}
	
	$scope.lastone = function(){
		$scope.setSelected($scope.entities.length);
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
}])
