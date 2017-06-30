var app = angular.module('legalEntity.controllers',[]);

app.controller('legalEntityController',['$scope','legalEntityService','$location',
	function($scope,service,$location){

	
	$scope.searchEntity = {id : null,naziv_klijenta:"",skraceni_naziv_klijenta:"", maticni_broj:"", pib:"",businessActivityCode:null,
			nadlezni_poreski_organ_za_klijenta:"", naziv_organa:"",address:"" ,phone : "",email:"",addressForStatements:"",fax:"",
			emailStatements:"",firstName:"",lastName:"",jmbg:"",typeOfClient:"Pravno lice",residence:null
			};	
	$scope.entity = {id : null,naziv_klijenta:"",skraceni_naziv_klijenta:"", maticni_broj:"", pib:"",businessActivityCode:null,
			nadlezni_poreski_organ_za_klijenta:"", naziv_organa:"",address:"" ,phone : "",email:"",addressForStatements:"",fax:"",
			emailStatements:false,firstName:"",lastName:"",jmbg:"",typeOfClient:"Pravno lice",residence:null
			};	
	$scope.updatedEntity={};
	$scope.showUpdateForm = false;

	
	
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
		$scope.showUpdateForm = true;
	
		$scope.selectedEntity = selectedEntity;
		$scope.updatedEntity.id=$scope.selectedEntity.id;
		$scope.updatedEntity.naziv_klijenta=selectedEntity.naziv_klijenta;
		$scope.updatedEntity.fax=selectedEntity.fax;
		$scope.updatedEntity.skraceni_naziv_klijenta=selectedEntity.skraceni_naziv_klijenta;
		$scope.updatedEntity.maticni_broj=selectedEntity.maticni_broj;
		$scope.updatedEntity.pib=selectedEntity.pib;
		$scope.updatedEntity.naziv_organa=selectedEntity.naziv_organa;
		$scope.updatedEntity.nadlezni_poreski_organ_za_klijenta=selectedEntity.nadlezni_poreski_organ_za_klijenta;
		$scope.updatedEntity.firstName=selectedEntity.firstName;
		$scope.updatedEntity.lastName=selectedEntity.lastName;
		$scope.updatedEntity.jmbg=selectedEntity.jmbg;
		$scope.updatedEntity.address=selectedEntity.address;
		$scope.updatedEntity.emailStatements=selectedEntity.emailStatements;
		$scope.updatedEntity.phone=selectedEntity.phone;
		$scope.updatedEntity.email=selectedEntity.email;
		$scope.updatedEntity.typeOfClient="Fizicko lice";
		$scope.updatedEntity.addressForStatements=selectedEntity.addressForStatements;
		$scope.updatedEntity.businessActivityCode = selectedEntity.businessActivityCode;
		$scope.updatedEntity.residence = selectedEntity.residence;

	}
	
	$scope.saveChanges = function(){
		
		service.update($scope.updatedEntity).then(function(response){
			
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
	$scope.setSelectedPlaceUpdated = function(place){
		$scope.updatedEntity.residence = place;
	}
	$scope.setSelectedBusinessActivityUpdated = function(businessActivityCode){
		$scope.updatedEntity.businessActivityCode = businessActivityCode;
	}
	
	
	$scope.deselect = function(){
		$scope.selectedEntity = null;
		$scope.showUpdateForm = false;
		$scope.searchEntity = {id : null,naziv_klijenta:"",skraceni_naziv_klijenta:"",maticni_broj:"",pib:"",businessActivityCode:null,
				nadlezni_poreski_organ_za_klijenta:"",naziv_organa:"",address:"" ,phone : "",email:"",addressForStatements:"",fax:"",
				emailStatements:"",firstName:"",lastName:"",jmbg:"",typeOfClient:"Pravno lice",residence:null};
	}
	$scope.refresh = function(){
		$scope.selectedEntity = null;
		$scope.searchEntity = {id : null,naziv_klijenta:"",skraceni_naziv_klijenta:"",maticni_broj:"",pib:"",businessActivityCode:null,
				nadlezni_poreski_organ_za_klijenta:"",naziv_organa:"",address:"" ,phone : "",email:"",addressForStatements:"",fax:"",
				emailStatements:"",firstName:"",lastName:"",jmbg:"",typeOfClient:"Pravno lice",residence:null};
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
