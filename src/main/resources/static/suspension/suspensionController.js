var app = angular.module('suspension.controllers', []);

app.controller('suspensionController', ['$scope','suspensionService','$location',
		function($scope, service, $location) {

			$scope.searchEntity = {id : null,date:"" ,transferToAccount : "",legalEntityAccount:{}};
			$scope.entity = {id : null,date:"" ,transferToAccount : "",legalEntityAccount:{}};
			$scope.idSelectedEntity = null;
			$scope.showUpdateForm = false;

			$scope.updatedEntity={};
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
				$scope.showUpdateForm = true;
				$scope.selectedEntity = selectedEntity;
				$scope.updatedEntity.id=$scope.selectedEntity.id;
				$scope.updatedEntity.transferToAccount=selectedEntity.transferToAccount;
				
				$scope.showUpdateForm = true;
				
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
				$scope.entity.date= new Date();
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
			$scope.setSelectedAccountUpdated = function(account){
				$scope.updatedEntity.legalEntityAccount.id = account.id;
				$scope.updatedEntity.legalEntityAccount.brojRacuna = account.brojRacuna;
				$scope.entity.legalEntityAccount.id = account.id;
				$scope.entity.legalEntityAccount.brojRacuna = account.brojRacuna;
				$scope.searchEntity.legalEntityAccount.id = account.id;
				$scope.searchEntity.legalEntityAccount.brojRacuna = account.brojRacuna;
			}
			
			$scope.setSelectedAccount = function(account){
					$scope.entity.legalEntityAccount.id = account.id;
					$scope.entity.legalEntityAccount.brojRacuna = account.brojRacuna;
			}
			
			
			$scope.deselect = function(){
				$scope.selectedEntity = null;
				$scope.showUpdateForm = false;
				$scope.searchEntity = {id : null,date:"" ,transferToAccount : "",legalEntityAccount:{}};

			}
			$scope.refresh = function(){
				$scope.selectedEntity = null;
				$scope.searchEntity = {id : null,date:"" ,transferToAccount : "",legalEntityAccount:{}};

				findAll();
			}			

}]);


