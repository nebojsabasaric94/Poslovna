var app = angular.module('country.controllers', []);

app.controller('countryController', ['$scope','countryService','$location',
		function($scope, service, $location) {

			findAll();
			$scope.searchEntity = {id : null,name : "",country_code : ""};
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


			$scope.search = function(){
				service.search($scope.searchEntity)
				.then(function(response){
					$scope.entities = response.data; 
				},
				function(response){
					
				})
			}
			
			$scope.deselect = function(){
				$scope.selectedEntity = null;
				$scope.searchEntity = {id : null,name : "",country_code : ""};
			}
			$scope.refresh = function(){
				$scope.selectedEntity = null;
				$scope.searchEntity = {id : null,name : "",country_code : ""};
				findAll();
			}

}]);


