var app = angular.module('nationalBank.controllers', []);

app.controller('nationalBankController', ['$scope','nationalBankService','$location',
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
					$location.path('nationalBank');
				},
				function(response){
					alert("Dodavanje neuspesno");
				}
				);
			}
}]);


