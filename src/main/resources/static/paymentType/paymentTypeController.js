var app = angular.module('paymentType.controllers', []);

app.controller('paymentTypeController', ['$scope','paymentTypeService','$location',
		function($scope, service, $location) {
			
			$scope.searchEntity = {code : "",nameOfPaymentType :""};

			$scope.idSelectedEntity = null;
			
			$scope.entity ={code : "",nameOfPaymentType :""};
			
		
			$scope.updatedEntity={};
			
			
			findAll();
			$scope.idSelectedEntity = null;
		
			function findAll() {
				service.findAll().then(function(response) {
					$scope.entities = response.data;
				});
			}
			

			$scope.setSelected = function(selectedEntity){
				
				$scope.showUpdateForm = true;
				$scope.selectedEntity = selectedEntity;
				$scope.updatedEntity.id=$scope.selectedEntity.id;
				$scope.updatedEntity.code=selectedEntity.code;
				$scope.updatedEntity.nameOfPaymentType=selectedEntity.nameOfPaymentType;
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
					$location.path('paymentType');
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
			
			$scope.deselect = function(){
				$scope.selectedEntity = null;
				$scope.showUpdateForm = false;
				$scope.searchEntity = {code : "",nameOfPaymentType :""};

			}
			$scope.refresh = function(){
				$scope.selectedEntity = null;
				$scope.searchEntity = {code : "",nameOfPaymentType :""};

				findAll();
			}			
			
			$scope.next = function(){
				if(!($scope.selectedEntity))
					return;
				sessionStorage.setItem("nextFilterPaymentType", $scope.selectedEntity.id);
				sessionStorage.setItem("backFilterPaymentType", $scope.entities);
				$location.path('/analyticsOfStatements');
			
			}
}]);


