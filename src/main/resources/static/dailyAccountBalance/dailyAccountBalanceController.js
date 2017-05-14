var app = angular.module('dailyAccountBalance.controllers', []);

app.controller('dailyAccountBalanceController', ['$scope','dailyAccountBalanceService','$location',
		function($scope, dailyAccountBalanceService, $location) {

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


