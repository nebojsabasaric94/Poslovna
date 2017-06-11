var app = angular.module('country.controllers', []);

app.controller('countryController', ['$scope','countryService','$location',
		function($scope, service, $location) {

	
			$scope.showUpdateForm = false;
			$scope.updatedEntity={};
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
				$scope.showUpdateForm = true;
				$scope.selectedEntity = selectedEntity;
				$scope.updatedEntity.id=$scope.selectedEntity.id;
				$scope.updatedEntity.name=selectedEntity.name;
				$scope.updatedEntity.country_code=selectedEntity.country_code;
				$scope.showUpdateForm = true;
			}
			
			//$scope.startChange = function(){
				//$("[name=updatedId]").val($("#state_id").text());// da mu se i ID prosledi :D
				//$("[name=updatedName]").val($("#state_name").text());
				//$("[name=updatedCode]").val($("#state_code").text());
			//}
			
			$scope.saveChanges = function(){
				
				service.update($scope.updatedEntity).then(function(response){
					//function(response){
						
						//$window.location.reload();
					//}
							for(i = 0 ; i < $scope.entities.length;i++){
								if($scope.entities[i].id == $scope.updatedEntity.id){
									$scope.entities[i] = response.data;
								}
							}
							$scope.showUpdateForm = false;
						},
						function(response){
							
						})
			}
			
			
			$scope.firstone = function(){
				$scope.setSelected($scope.entities[0]);
			}
			
			$scope.previous = function(selectedEntity){
				var index = -1;
				
				for(i = 0 ; i  < $scope.entities.length;i++){
					if($scope.entities[i].id == $scope.selectedEntity.id)
						index = i;
				}
				if(index != 0)				
					$scope.setSelected($scope.entities[index-1])
				else	
					$scope.setSelected($scope.entities[$scope.entities.length-1])
					
			}
			
			
			$scope.nextNavigation = function(selectedEntity){
				
				var index = -1;
				
				for(i = 0 ; i  < $scope.entities.length;i++){
					if($scope.entities[i].id == $scope.selectedEntity.id)
						index = i;
				}
					
				if(index == $scope.entities.length-1)
					$scope.setSelected($scope.entities[0])
				else				
					$scope.setSelected($scope.entities[index+1])
			}
			
			$scope.lastone = function(){
				$scope.setSelected($scope.entities[$scope.entities.length-1])
			}
			
			
			

			$scope.nextPlace = function(){
				if(!($scope.selectedEntity))
					return;
				sessionStorage.setItem("nextFilter", $scope.selectedEntity.id);
				sessionStorage.setItem("backFilter", $scope.entities);
				$location.path('/place');
			}
			
			$scope.nextCurrency = function(){
				if(!($scope.selectedEntity))
					return;
				sessionStorage.setItem("nextFilter", $scope.selectedEntity.id);
				sessionStorage.setItem("backFilter", $scope.entities);
				$location.path('/currency');
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
				$scope.showUpdateForm = false;
				$scope.searchEntity = {id : null,name : "",country_code : ""};
			}
			$scope.refresh = function(){
				$scope.selectedEntity = null;
				$scope.searchEntity = {id : null,name : "",country_code : ""};
				findAll();
			}

}]);


