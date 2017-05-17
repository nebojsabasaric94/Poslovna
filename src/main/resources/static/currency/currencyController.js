var app = angular.module('currency.controllers', []);

app.controller('currencyController', ['$scope','currencyService','$location',
		function($scope, service, $location) {

			$scope.searchEntity = {id : null,official_code:"" ,name : "",domicilna:"false"};
			$scope.idSelectedEntity = null;
	
	
			findAll();
		
			function findAll() {
				service.findAll().then(function(response) {
					$scope.entities = response.data;
				});
			}
			
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
			$scope.search = function(){
				service.search($scope.searchEntity)
				.then(function(response){
					$scope.entities = response.data; 
					//$scope.searchEntity = {id : null,pttNumber:"" ,name : "",country:null};

				},
				function(response){
					
				})
			}
			
			$scope.deselect = function(){
				$scope.selectedEntity = null;
				$scope.searchEntity = {id : null,official_code:"" ,name : "",domicilna:"false"};

			}
			$scope.refresh = function(){
				$scope.selectedEntity = null;
				$scope.searchEntity = {id : null,official_code:"" ,name : "",domicilna:"false"};

				findAll();
			}
}]);


