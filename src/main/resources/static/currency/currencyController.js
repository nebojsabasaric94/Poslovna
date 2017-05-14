var app = angular.module('currency.controllers', []);

app.controller('currencyController', ['$scope','currencyService','$location',
		function($scope, currencyService, $location) {

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


