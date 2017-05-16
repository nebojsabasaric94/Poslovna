var app = angular.module('country.controllers', []);

app.controller('countryController', ['$scope','countryService','$location',
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
					$location.path('country');
				},
				function(response){
					alert("Dodavanje neuspesno");
				}
				);
			}

}]);


