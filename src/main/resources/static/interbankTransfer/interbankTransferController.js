var app = angular.module('interbankTransfer.controllers', []);

app.controller('interbankTransferController', ['$scope','interbankTransferService','$location',
		function($scope, interbankTransferService, $location) {
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


