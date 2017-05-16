var app = angular.module('currencyRate.controllers', []);

app.controller('currencyRateController', ['$scope','currencyRateService','$location',
		function($scope, service, $location) {

			findAll();
		
			function findAll() {
				service.findAll().then(function(response) {
					$scope.entities = response.data;
				});
			}
			
			$scope.idSelectedEntity = null;
			$scope.setSelected = function(selectedEntity){
				$scope.selectedEntity = selectedEntity;
			}
			
			$scope.add = function(){
				service.save($scope.entity).then(function(response) {
					findAll();
					$location.path('currencyRate');
				},
				function(response){
					alert("Dodavanje neuspesno");
				}
				);
			}

}]);


