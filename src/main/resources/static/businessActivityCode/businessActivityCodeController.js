var app = angular.module('businessActivityCode.controllers',[]);

app.controller('businessActivityCodeController',['$scope','businessActivityCodeService','$location',
	function($scope,service,$location){

	findAll();
	
	function findAll() {
		service.findAll().then(function(response) {
			$scope.entities = response.data;
		});
	}	

	$scope.setSelected = function(selectedEntity){
		$scope.selectedEntity = selectedEntity;
	}	
	
}])
