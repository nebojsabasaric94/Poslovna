var app = angular.module('bank.controllers', []);

app.controller('bankController', ['$scope','bankService','$location',
		function($scope, service, $location) {

			$scope.idSelectedEntity = null;
			$scope.searchEntity = {id:null,bankCode:"",pib:"",name:"",address:"",email:"",web:"",phone:"",fax:"",bank:"false"}
	
			findAll();

			function findAll() {
				service.findAll().then(function(response) {
					$scope.entities = response.data;
				});
			}
			
$scope.idSelectedEntity = null;
			
			
			$scope.setSelected = function(selectedEntity){
				$scope.selectedEntity = selectedEntity;
			}
			
			
			$scope.firstone = function(){
				$scope.setSelected(1);
			}
			
			$scope.previous = function(selectedEntity){
				if($scope.selectedEntity != 1)
					$scope.setSelected($scope.selectedEntity-1);
				else
					$scope.setSelected(1);
					
			}
			
			
			$scope.nextNavigation = function(selectedEntity){
				if($scope.selectedEntity != $scope.entities.length )
					$scope.setSelected($scope.selectedEntity+1);
				else
					$scope.setSelected($scope.entities.length);
			}
			
			$scope.lastone = function(){
				$scope.setSelected($scope.entities.length);
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
				sessionStorage.setItem("nextFilter", $scope.selectedEntity.id);
				$location.path('/exchageRateList');
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


