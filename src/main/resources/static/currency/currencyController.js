var app = angular.module('currency.controllers', []);

app.controller('currencyController', ['$scope','currencyService','$location',
		function($scope, service, $location) {

			$scope.searchEntity = {id : null,official_code:"" ,name : "",domicilna:"false",country:null};
			$scope.idSelectedEntity = null;
	
	
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
			
			$scope.idSelectedEntity = null;
			
			
			$scope.setSelected = function(selectedEntity){
				$scope.selectedEntity = selectedEntity;
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
			
			$scope.deselect = function(){
				$scope.selectedEntity = null;
				$scope.searchEntity = {id : null,official_code:"" ,name : "",domicilna:"false",country:null};

			}
			$scope.refresh = function(){
				$scope.selectedEntity = null;
				$scope.searchEntity = {id : null,official_code:"" ,name : "",domicilna:"false",country:null};

				findAll();
			}
			
			$scope.back = function(){
				var backFilter = sessionStorage.getItem("backFilter");
				sessionStorage.removeItem("backFilter");
				if(backFilter == null)
					return;
				
				$location.path("/country");
			
			}
			
			$scope.nextLegalEntityAccount = function(){
				if(!($scope.selectedEntity))
					return;
				sessionStorage.setItem("nextFilterCurrency", $scope.selectedEntity.id);
				sessionStorage.setItem("backFilterCurrency", $scope.entities);
				$location.path("/legalEntityAccount");
			
			}
			
			$scope.nextAnalyticsOfStatements = function(){
				if(!($scope.selectedEntity))
					return;
				sessionStorage.setItem("nextFilterCurrency", $scope.selectedEntity.id);
				sessionStorage.setItem("backFilterCurrency", $scope.entities);
				$location.path('/analyticsOfStatements');
			}
}]);


