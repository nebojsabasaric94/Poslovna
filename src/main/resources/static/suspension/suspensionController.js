var app = angular.module('suspension.controllers', []);

app.controller('suspensionController', ['$scope','suspensionService','$location',
		function($scope, suspensionService, $location) {

			findAll();
		
			function findAll() {
				bankService.findAll().then(function(response) {
					$scope.entities = response.data;
				});
			}
			
			$scope.idSelectedEntity = null;
			$scope.setSelected = function(selectedEntity){
				$scope.selectedEntity = selectedEntity;
			}

}]);


