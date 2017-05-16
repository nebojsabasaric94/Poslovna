var app = angular.module('place.controllers', []);

app.controller('placeController', ['$scope','placeService','$location',
		function($scope, placeService, $location) {

			findAll();
		
			function findAll() {
				var nextFilter = sessionStorage.getItem("nextFilter");
				sessionStorage.removeItem("nextFilter");
				
				if(nextFilter == null){
					placeService.findAll().then(
						function(response) {
							$scope.entities = response.data;
						});
				} else {
					placeService.next(nextFilter).then(
						function(response){
							
							$scope.entities = response.data;
						}
					)
				}
				
			}
			
			$scope.idSelectedEntity = null;
			$scope.setSelected = function(selectedEntity){
				$scope.selectedEntity = selectedEntity;
			}
			
			$scope.delete = function(){
				if(!($scope.selectedEntity))
					return;
				placeService.delete($scope.selectedEntity.id).then(
					function(response){
						$scope.entities.splice($scope.entities.indexOf($scope.selectedEntity),1);
					}, function(response){
						alert("brisanje nije moguce");
					}
				)
			}
}]);


