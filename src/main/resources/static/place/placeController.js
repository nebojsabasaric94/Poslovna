var app = angular.module('place.controllers', []);

app.controller('placeController', ['$scope','placeService','$location',
		function($scope, service, $location) {

			$scope.idSelectedEntity = null;
			$scope.searchEntity = {id : null,pttNumber:"" ,name : "",country:null};
			$scope.entity = {id : null,pttNumber:"" ,name : "",country:null};
			
		
			$scope.updatedEntity={};
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
				$scope.showUpdateForm = true;
				$scope.selectedEntity = selectedEntity;
				$scope.updatedEntity.id=$scope.selectedEntity.id;
				$scope.updatedEntity.name=selectedEntity.name;
				$scope.updatedEntity.pttNumber=selectedEntity.pttNumber;
				$scope.updatedEntity.country=selectedEntity.country;
				
				
			}
			
			
			
			
			$scope.saveChanges = function(){
				
				service.update($scope.updatedEntity).then(function(response){
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
			
			$scope.showModalSearch = function(){
				var modal = document.getElementById('myModalSearch');
				modal.style.display = "block";		
			}
			$scope.closeModal = function(){
				var modal = document.getElementById('myModalSearch');
				modal.style.display  = "none";

			}
			$scope.findAllCountries = function(){
				service.findAllCountries()
				.then(function(response){
					$scope.countries = response.data;
				},
				function(response){
					
				})
			}
			$scope.setSelectedCountrySearch = function(country){
				$scope.searchEntity.country = country;
			}
			$scope.setSelectedCountry = function(country){
				$scope.entity.country = country;
			}
			$scope.setSelectedCountryUpdate = function(country){
		     	$scope.updatedEntity.country = country;
				$scope.searchEntity.country = country;
				$scope.entity.country = country;


			}
			
			
			$scope.deselect = function(){
				$scope.selectedEntity = null;
				$scope.showUpdateForm = false;
				$scope.searchEntity = {id : null,pttNumber:"" ,name : "",country:null};

			}
			$scope.refresh = function(){
				$scope.selectedEntity = null;
				$scope.searchEntity = {id : null,pttNumber:"" ,name : "",country:null};

				findAll();
			}	
			
			$scope.back = function(){
				var backFilter = sessionStorage.getItem("backFilter");
				sessionStorage.removeItem("backFilter");
				if(backFilter == null)
					return;
				
				$location.path("/country");
			
			}
			
			$scope.nextClient = function(){
				if(!($scope.selectedEntity))
					return;
				sessionStorage.setItem("nextFilter", $scope.selectedEntity.id);
				sessionStorage.setItem("backFilterPlace", $scope.entities);
				$location.path('/client');
			}
			
			$scope.nextAnalyticsOfStatements = function(){
				if(!($scope.selectedEntity))
					return;
				sessionStorage.setItem("nextFilterPlace", $scope.selectedEntity.id);
				sessionStorage.setItem("backFilterPlace", $scope.entities);
				$location.path('/analyticsOfStatements');
			}
}]);


