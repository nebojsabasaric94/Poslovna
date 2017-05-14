var app = angular.module('nationalBank.controllers', []);

app.controller('nationalBankController', ['$scope','nationalBankService','$location',
		function($scope, nationalBankService, $location) {

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


