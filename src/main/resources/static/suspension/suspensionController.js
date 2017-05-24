var app = angular.module('suspension.controllers', []);

app.controller('suspensionController', ['$scope','suspensionService','$location',
		function($scope, service, $location) {

			$scope.searchEntity = {id : null,date:"" ,transferToAccount : "",legalEntityAccount:{}};
	
	
			findAll();
		
			function findAll() {
				service.findAll().then(function(response) {
					for(i = 0; i < response.data.length;i++){
						response.data[i].date = transformDate(new Date(response.data[i].date));
					}
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
					$scope.setSelected($scope.entities.length);
					
			}
			
			
			$scope.nextNavigation = function(selectedEntity){
				if($scope.selectedEntity != $scope.entities.length )
					$scope.setSelected($scope.selectedEntity+1);
				else
					$scope.setSelected(1);
			}
			
			$scope.lastone = function(){
				$scope.setSelected($scope.entities.length);
			}
			
			
			$scope.add = function(){
				service.save($scope.entity).then(function(response) {
					findAll();
					$location.path('suspension');
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
			$scope.findAllAccounts = function() {
				service.findAllAccounts()
				.then(function(response) {
					for(i = 0; i < response.data.length;i++){
						response.data[i].datumOtvaranja = transformDate(new Date(response.data[i].datumOtvaranja));
					}
					$scope.accounts = response.data;
				},
				function(response){
					
				});
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
						response.data[i].date = transformDate(new Date(response.data[i].date));
					}
					$scope.entities = response.data; 

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
			$scope.deselect = function(){
				$scope.selectedEntity = null;
				$scope.searchEntity = {id : null,date:"" ,transferToAccount : "",legalEntityAccount:{}};

			}
			$scope.refresh = function(){
				$scope.selectedEntity = null;
				$scope.searchEntity = {id : null,date:"" ,transferToAccount : "",legalEntityAccount:{}};

				findAll();
			}			

}]);


