var app = angular.module('itemTransfer.controllers', []);

app.controller('itemTransferController', ['$scope','itemTransferService','$location',
		function($scope, service, $location) {
			$scope.searchEntity = {id : null,interbankTransfer:{} ,analyticsOfStatements :{}};

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
					$location.path('itemTransfer');
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
			
			$scope.showModalAnalyticsOfStatements = function(){
				var modal = document.getElementById('modalAnalyticsOfStatements');
				modal.style.display = "block";		
			}
			$scope.closeModalAnalyticsOfStatements = function(){
				var modal = document.getElementById('modalAnalyticsOfStatements');
				modal.style.display  = "none";
			}
			$scope.showModalInterbankTransfers = function(){
				var modal = document.getElementById('modalInterbankTransfers');
				modal.style.display = "block";		
			}
			$scope.closeModalInterbankTransfers = function(){
				var modal = document.getElementById('modalInterbankTransfers');
				modal.style.display  = "none";
			}
			$scope.findAllAnalyticsOfStatements = function(){
				service.findAllAnalyticsOfStatements()
				.then(function(response){
					$scope.analyticsOfStatements = response.data;
				},
				function(response){
					
				})
			}
			$scope.findAllInterbankTransfers = function(){
				service.findAllInterbankTransfers ()
				.then(function(response){
					$scope.interbankTransfers = response.data;
				},
				function(response){
					
				})
			}
			$scope.setSelectedStatementSearch = function(statement){
				$scope.searchEntity.analyticsOfStatements = statement;
			}
			$scope.setSelectedInterbankTransferSearch = function(transfer){
				$scope.searchEntity.interbankTransfer = transfer;
			}			
			$scope.deselect = function(){
				$scope.selectedEntity = null;
				$scope.searchEntity = {id : null,interbankTransfer:{} ,analyticsOfStatements :{}};

			}
			$scope.refresh = function(){
				$scope.selectedEntity = null;
				$scope.searchEntity = {id : null,interbankTransfer:{} ,analyticsOfStatements :{}};

				findAll();
			}	
			

}]);


