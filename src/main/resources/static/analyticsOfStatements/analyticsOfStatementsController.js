var app = angular.module('analyticsOfStatements.controllers', []);

app.controller('analyticsOfStatementsController', ['$scope','analyticsOfStatementsService','$location',
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
					$location.path('analyticsOfStatements');
				},
				function(response){
					alert("Dodavanje neuspesno");
				}
				);
			}

}]);


