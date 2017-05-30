var app = angular.module('exchageRateList.controllers', ['angular-popover']);

app.controller('exchageRateListController', ['$scope','exchageRateListService','$location',
		function($scope, service, $location) {

			$scope.searchEntity = {id : null,date:null ,numberOfExchangeRateList : "",appliedBy:null};
			$scope.entity = {id : null,date:null ,numberOfExchangeRateList : "",appliedBy:null};
			$scope.idSelectedEntity = null;

		    	
			findAll();
			
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
			
			
		
			function findAll() {

				var nextFilter = sessionStorage.getItem("nextFilter");
				sessionStorage.removeItem("nextFilter");
				
				if(nextFilter == null){
					service.findAll().then(
						function(response) {
							for(i = 0; i < response.data.length;i++){
								response.data[i].date = transformDate(new Date(response.data[i].date));
								response.data[i].appliedBy = transformDate(new Date(response.data[i].appliedBy));
							}
							$scope.entities = response.data;
						});
				} else {
					service.next(nextFilter).then(
						function(response){
							for(i = 0; i < response.data.length;i++){
								response.data[i].date = transformDate(new Date(response.data[i].date));
								response.data[i].appliedBy = transformDate(new Date(response.data[i].appliedBy));
							}
							$scope.entities = response.data;
							
						}
					)
				}
				
			}
			function transformDate(dateObj){
				var month = ("0" + (dateObj.getMonth() + 1)).slice(-2); //months from 1-12
				var day = ("0" + dateObj.getDate()).slice(-2);
				var year = dateObj.getFullYear();

				var newdate = year + "/" + month + "/" + day;
				return newdate;
			}

			//$scope.idSelectedEntity = null;
			
		
			
			
			$scope.add = function(){
				service.save($scope.entity).then(function(response) {
					findAll();
					$location.path('exchageRateList');
				},
				function(response){
					alert("Dodavanje neuspesno");
				});
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
					for(i = 0; i < response.data.length;i++){
						response.data[i].date = transformDate(new Date(response.data[i].date));
						response.data[i].appliedBy = transformDate(new Date(response.data[i].appliedBy));
					}
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
				
			$scope.findAllBanks = function(){
				service.findAllBanks()
				.then(function(response){
					$scope.banks = response.data;
				},
				function(response){			
				})
			}				
			$scope.setSelectedBank = function(bank){
					$scope.searchEntity.commercialBankRate = bank;
			}	
			$scope.setSelectedBankAdd = function(bank){
				$scope.entity.commercialBankRate = bank;
		}	
			$scope.deselect = function(){
				$scope.selectedEntity = null;
				$scope.searchEntity = {id : null,date:null ,numberOfExchangeRateList : "",appliedBy:null};

			}
			$scope.refresh = function(){
				$scope.selectedEntity = null;
				$scope.searchEntity = {id : null,date:null ,numberOfExchangeRateList : "",appliedBy:null};

				findAll();
			}
			
			$scope.back = function(){
				var backFilter = sessionStorage.getItem("backFilter");
				sessionStorage.removeItem("backFilter");
				if(backFilter == null)
					return;
				
				$location.path("/bank");
			}
			
			$scope.next = function(){
				if(!($scope.selectedEntity))
					return;
				sessionStorage.setItem("nextFilter", $scope.selectedEntity.id);
				sessionStorage.setItem("backFilter", $scope.entities);
				$location.path('/currencyRate');
			}
			
			
}]);


