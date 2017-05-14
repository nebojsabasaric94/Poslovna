var app = angular.module('bank.controllers', []);

app.controller('bankController', ['$scope','bankService','$location',
		function($scope, bankService, $location) {

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


