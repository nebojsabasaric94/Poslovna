var app = angular.module('businessActivityCode.controllers',[]);

app.controller('businessActivityCodeController',['$scope','businessActivityCodeService','$location',
	function($scope,service,$location){

	$scope.showUpdateForm = false;
	$scope.updatedEntity={};
	$scope.searchEntity = {id:null,code : "",name:""};
	
	
	findAll();
	
	function findAll() {
		service.findAll().then(function(response) {
			$scope.entities = response.data;
		});
	}	

	$scope.idSelectedEntity = null;
	
	
	$scope.setSelected = function(selectedEntity){
		$scope.showUpdateForm = true;
		$scope.selectedEntity = selectedEntity;
		$scope.updatedEntity.id=$scope.selectedEntity.id;
		$scope.updatedEntity.name=selectedEntity.name;
		$scope.updatedEntity.code=selectedEntity.code;
		$scope.showUpdateForm = true;
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
			$location.path('businessActivityCode');
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
	
	
	

	
	
	$scope.search = function(){
		service.search($scope.searchEntity)
		.then(function(response){
			$scope.entities = response.data; 
			//$scope.searchEntity = {id : null,pttNumber:"" ,name : "",country:null};

		},
		function(response){
			
		})
	}	
	
	$scope.deselect = function(){
		$scope.selectedEntity = null;
		$scope.showUpdateForm = false;
		$scope.searchEntity = {id:null,code : "",name:""};

	}
	$scope.refresh = function(){
		$scope.selectedEntity = null;
		$scope.searchEntity = {id:null,code : "",name:""};

		findAll();
	}
	
	$scope.next = function(){
		if(!($scope.selectedEntity))
			return;
		sessionStorage.setItem("nextFilterBussinesActivity", $scope.selectedEntity.id);
		sessionStorage.setItem("backFilterBussinesActivity", $scope.entities);
		$location.path('/legalEntity');
	}
	
	
}])
