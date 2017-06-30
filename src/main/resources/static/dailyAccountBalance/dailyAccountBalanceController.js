var app = angular.module('dailyAccountBalance.controllers', []);

app.controller('dailyAccountBalanceController', ['$scope','dailyAccountBalanceService','$location',
		function($scope, service, $location) {

			$scope.searchEntity = {id : null,trafficDate:"" ,previousState : null,trafficToBenefit:null,trafficToTheBurden:null,newState:null,legalEntityAccount:{}};
			$scope.entity = {id : null,trafficDate:"" ,previousState : null,trafficToBenefit:null,trafficToTheBurden:null,newState:null,legalEntityAccount:{}};
	
			findAll();
			
			function findAll() {

				var nextFilter = sessionStorage.getItem("nextFilterLegalEntityAccount");
				sessionStorage.removeItem("nextFilterLegalEntityAccount");
				
				if(nextFilter == null){
					service.findAll().then(
						function(response) {
							for(i = 0; i < response.data.length;i++){
								response.data[i].trafficDate = transformDate(new Date(response.data[i].trafficDate));
							}
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
					$location.path('dailyAccountBalance');
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
			

			$scope.findAllAccounts = function(){
				service.findAllAccounts()
				.then(function(response){
					for(i = 0; i < response.data.length;i++){
						response.data[i].datumOtvaranja = transformDate(new Date(response.data[i].datumOtvaranja));
					}
					$scope.accounts = response.data;
					checkIfLegalEntity();
				},
				function(response){
					
				})
			}
			function checkIfLegalEntity(){
				for(i=0;i<$scope.accounts.length;i++){
					if($scope.accounts[i].client.typeOfClient == "Pravno lice")
					service.checkIfLegalEntity($scope.accounts[i].client.id)
					.then(function(response){
						if(i < $scope.accounts.length){
							$scope.accounts[i].client = response.data;
						}
					},
					function(response){
							
					})
				}
			}
			function transformDate(dateObj){
				var month = ("0" + (dateObj.getMonth() + 1)).slice(-2); //months from 1-12
				var day = ("0" + dateObj.getDate()).slice(-2);
				var year = dateObj.getFullYear();

				var newdate = year + "/" + month + "/" + day;
				return newdate;
			}
			
			$scope.search = function(){
				service.search($scope.searchEntity)
				.then(function(response){
					for(i = 0; i < response.data.length;i++){
						response.data[i].trafficDate = transformDate(new Date(response.data[i].trafficDate));
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
			$scope.setSelectedAccountSearch = function(account){
				$scope.searchEntity.legalEntityAccount.id = account.id;
				$scope.searchEntity.legalEntityAccount.brojRacuna = account.brojRacuna;
			}
			$scope.setSelectedAccount = function(account){
				$scope.entity.legalEntityAccount.id = account.id;
				$scope.entity.legalEntityAccount.brojRacuna = account.brojRacuna;
			}
			
			$scope.deselect = function(){
				$scope.selectedEntity = null;
				
				$scope.searchEntity = {id : null,trafficDate:"" ,previousState : null,trafficToBenefit:null,trafficToTheBurden:null,newState:null,legalEntityAccount:{}};

			}
			$scope.refresh = function(){
				$scope.selectedEntity = null;
				$scope.searchEntity = {id : null,trafficDate:"" ,previousState : null,trafficToBenefit:null,trafficToTheBurden:null,newState:null,legalEntityAccount:{}};

				findAll();
			}			
			
			$scope.next = function(){
				if(!($scope.selectedEntity))
					return;
				sessionStorage.setItem("nextFilterDaily", $scope.selectedEntity.id);
				sessionStorage.setItem("backFilterDaily", $scope.entities);
				$location.path('/analyticsOfStatements');
			
			}
			
			$scope.back = function(){
				if(sessionStorage.getItem("backFilterLegalEntityAccount") != null){
					sessionStorage.removeItem("backFilterLegalEntityAccount");
					$location.path("/legalEntityAccount");
				} else {
					return;
				}
			}
}]);


