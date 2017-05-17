var app = angular.module('place.controllers', []);

app.controller('placeController', ['$scope','placeService','$location',
		function($scope, service, $location) {

			$scope.idSelectedEntity = null;
			$scope.searchEntity = {id : null,pttNumber:"" ,name : "",country:null};
	
			findAll();
			
			function findAll() {

				var nextFilter = sessionStorage.getItem("nextFilter");
				sessionStorage.removeItem("nextFilter");
				
				if(nextFilter == null){
					service.findAll().then(
						function(response) {
							$scope.entities = response.data;
						});
				} else {
					service.next(nextFilter).then(
						function(response){
							$scope.entities = response.data;
						}
					)
				}
				
			}
			
			$scope.setSelected = function(selectedEntity){
				$scope.selectedEntity = selectedEntity;
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

			$scope.add = function(){
				service.save($scope.entity).then(function(response) {
					findAll();
					$location.path('place');
				},
				function(response){
					alert("Dodavanje neuspesno");
				});
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
				$scope.searchEntity = {id : null,pttNumber:"" ,name : "",country:null};

			}
			$scope.refresh = function(){
				$scope.selectedEntity = null;
				$scope.searchEntity = {id : null,pttNumber:"" ,name : "",country:null};

				findAll();
			}
}]);


