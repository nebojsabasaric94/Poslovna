var app = angular.module('businessActivityCode.controllers',[]);

app.controller('businessActivityCodeController',['$scope','businessActivityCodeService','$location',
	function($scope,service,$location){

	$scope.searchEntity = {id:null,code : "",name:""};
	
	
	findAll();
	
	function findAll() {
		service.findAll().then(function(response) {
			$scope.entities = response.data;
		});
	}	

	$scope.setSelected = function(selectedEntity){
		$scope.selectedEntity = selectedEntity;
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
		$scope.searchEntity = {id:null,code : "",name:""};

	}
	$scope.refresh = function(){
		$scope.selectedEntity = null;
		$scope.searchEntity = {id:null,code : "",name:""};

		findAll();
	}
	
	
}])
