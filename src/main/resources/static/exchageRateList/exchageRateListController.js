var app = angular.module('exchageRateList.controllers', []);

app.controller('exchageRateListController', ['$scope','exchageRateListService','$location',
		function($scope, exchageRateListService, $location) {

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


