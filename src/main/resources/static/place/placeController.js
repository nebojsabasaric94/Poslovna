var app = angular.module('place.controllers', []);

app.controller('placeController', ['$scope','placeService','$location',
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
					$location.path('place');
				},
				function(response){
					alert("Dodavanje neuspesno");
				}
				);
			}
}]);


