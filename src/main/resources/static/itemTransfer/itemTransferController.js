var app = angular.module('itemTransfer.controllers', []);

app.controller('itemTransferController', ['$scope','itemTransferService','$location',
		function($scope, service, $location) {
			$scope.searchEntity = {id : null,interbankTransfer:{} ,analyticsOfStatements :{}};
			$scope.entity = {id : null,interbankTransfer:{} ,analyticsOfStatements :{}};
			findAll();
		
			function findAll() {
	
				var nextInterbankTransfer = sessionStorage.getItem("nextInterbankTransfer");
				sessionStorage.removeItem("nextInterbankTransfer");
				
				var nextAnalyticsOfStatements = sessionStorage.getItem("nextAnalyticsOfStatements");
				sessionStorage.removeItem("nextAnalyticsOfStatements");
				
				if(nextInterbankTransfer != null){
					service.nextInterbankTransfer(nextInterbankTransfer).then(
						function(response){
							$scope.entities = response.data;
						}
					)
				
				} else if(nextAnalyticsOfStatements != null){
					service.nextAnalyticsOfStatements(nextAnalyticsOfStatements).then(
							function(response){
								$scope.entities = response.data;
							}
						)
					
					
					
				} else {
					service.findAll().then(
						function(response) {
						   $scope.entities = response.data;
					})
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
			$scope.setSelectedStatement = function(statement){
				$scope.entity.analyticsOfStatements = statement;
			}
			$scope.setSelectedInterbankTransfer = function(transfer){
				$scope.entity.interbankTransfer = transfer;
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
			
			$scope.back = function(){
				if(sessionStorage.getItem("backInterbankTransfer") != null){
					sessionStorage.removeItem("backInterbankTransfer");
					$location.path("/interbankTransfer");
				} else if(sessionStorage.getItem("backAnalyticsOfStatements") != null){
						sessionStorage.removeItem("backAnalyticsOfStatements");
						$location.path("/analyticsOfStatements");
				} else {
					return;
				}
			
			}
			

}]);


