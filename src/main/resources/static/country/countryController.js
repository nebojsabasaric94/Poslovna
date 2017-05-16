var app = angular.module('country.controllers', []);

app.controller('countryController', ['$scope','countryService','$location',
		function($scope, service, $location) {

			findAll();
		
			function findAll() {

			//	countryService.findAll().then(function(response) {

				service.findAll().then(function(response) {
					$scope.entities = response.data;
				});
			}
			
			$scope.idSelectedEntity = null;
			$scope.setSelected = function(selectedEntity){
				$scope.selectedEntity = selectedEntity;
			}
			

			$scope.next = function(){
				if(!($scope.selectedEntity))
					return;
				sessionStorage.setItem("nextFilter", $scope.selectedEntity);
				$location.path('/place');
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


