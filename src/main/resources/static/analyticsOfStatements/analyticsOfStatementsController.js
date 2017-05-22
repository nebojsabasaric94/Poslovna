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
			
			
			$scope.firstone = function(){
				$scope.setSelected(1);
			}
			
			$scope.previous = function(selectedEntity){
				if($scope.selectedEntity != 1)
					$scope.setSelected($scope.selectedEntity-1);
				else
					$scope.setSelected($scope.entities.length);
					
			}
			
			
			$scope.nextNavigation = function(selectedEntity){
				if($scope.selectedEntity != $scope.entities.length )
					$scope.setSelected($scope.selectedEntity+1);
				else
					$scope.setSelected(1);
			}
			
			$scope.lastone = function(){
				$scope.setSelected($scope.entities.length);
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


