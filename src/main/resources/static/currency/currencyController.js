var app = angular.module('currency.controllers', []);

app.controller('currencyController', ['$scope','currencyService','$location',
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
					$location.path('currency');
				},
				function(response){
					alert("Dodavanje neuspesno");
				}
				);
			}
			
			$scope.delete = function(){
				if(!($scope.selectedEntity))
					return;
				service.delete($scope.selectedEntity.id).then(
					function(response){
						$scope.entities.splice($scope.entities.indexOf($scope.selectedEntity),1);
					}, function(response){
						alert("brisanje nije moguce");
					}
				)
			}
}]);


