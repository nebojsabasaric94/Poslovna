var app = angular.module('paymentType.controllers', []);

app.controller('paymentTypeController', ['$scope','paymentTypeService','$location',
		function($scope, paymentTypeService, $location) {
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


