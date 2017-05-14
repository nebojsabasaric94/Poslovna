var app = angular.module('itemTransfer.controllers', []);

app.controller('itemTransferController', ['$scope','itemTransferService','$location',
		function($scope, itemTransferService, $location) {
		
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


