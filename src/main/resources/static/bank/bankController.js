var app = angular.module('bank.controllers', []);

app.controller('bankController', ['$scope','bankService','$location',
		function($scope, service, $location) {

			$scope.idSelectedEntity = null;
			$scope.searchEntity = {id:null,bankCode:"",pib:"",name:"",address:"",email:"",web:"",phone:"",fax:"",bank:"false"}
	
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
					service.nextBank(nextFilter).then(
						function(response){
							$scope.entities = response.data;
						}
					)
				}
				
			}
			
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
					$location.path('bank');
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
			

			$scope.nextExchangeRateList = function(){
				if(!($scope.selectedEntity))
					return;
				sessionStorage.setItem("nextFilter", $scope.selectedEntity);
				sessionStorage.setItem("backFilter", $scope.entities);
				$location.path('/exchageRateList');
			}
			
			$scope.nextLegalEntityAccount = function(){
				if(!($scope.selectedEntity))
					return;
				sessionStorage.setItem("nextFilterBank", $scope.selectedEntity);
				sessionStorage.setItem("backFilterBank", $scope.entities);
				$location.path("/legalEntityAccount");
			}
			
			$scope.nextInterbankTransfers = function(){
				if(!($scope.selectedEntity))
					return;
				sessionStorage.setItem("nextFilter", $scope.selectedEntity);
				sessionStorage.setItem("backFilter", $scope.entities);
				$location.path("/interbankTransfer");
			}
			
			$scope.openModalNext = function(){
				var modal = document.getElementById('myModalNext');
				modal.style.display = "block";
			}
			

			$scope.deselect = function(){
				$scope.selectedEntity = null;
				$scope.searchEntity = {id:null,bankCode:"",pib:"",name:"",address:"",email:"",web:"",phone:"",fax:"",bank:"false"}

			}
			$scope.refresh = function(){
				$scope.selectedEntity = null;
				$scope.searchEntity = {id:null,bankCode:"",pib:"",name:"",address:"",email:"",web:"",phone:"",fax:"",bank:"false"}

				findAll();
			}			


}]);


