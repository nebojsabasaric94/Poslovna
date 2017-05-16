var app = angular.module('paymentType.controllers', []);

app.controller('paymentTypeController', ['$scope','paymentTypeService','$location',
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
					$location.path('paymentType');
				},
				function(response){
					alert("Dodavanje neuspesno");
				}
				);
			}

}]);


