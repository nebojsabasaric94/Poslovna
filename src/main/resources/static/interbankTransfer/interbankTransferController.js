var app = angular.module('interbankTransfer.controllers', []);

app.controller('interbankTransferController', ['$scope','interbankTransferService','$location',
		function($scope, service, $location) {
		
			$scope.searchEntity = {idMessage : null,typeOfMessage:"" ,date : "",sum:null,senderBank:{},bank:{}};
			findAll();
			$scope.entity = {idMessage : null,typeOfMessage:"" ,date : "",sum:null,senderBank:{},bank:{}};
			findAll();
		
			function findAll() {
				
				var nextFilter = sessionStorage.getItem("nextFilter");
				sessionStorage.removeItem("nextFilter");
				
				if(nextFilter == null){
					service.findAll().then(
						function(response) {
							for(i = 0; i < response.data.length;i++){
								response.data[i].date = transformDate(new Date(response.data[i].date));
							}
							$scope.entities = response.data;
						});
				} else {
					service.nextInterbankTransfer(nextFilter).then(
						function(response){
							for(i = 0; i < response.data.length;i++){
								response.data[i].date = transformDate(new Date(response.data[i].date));
							}
							$scope.entities = response.data;
						}
					)
				}
			}
				/*service.findAll().then(function(response) {
					for(i = 0; i < response.data.length;i++){
						response.data[i].date = transformDate(new Date(response.data[i].date));
					}
					$scope.entities = response.data;
				});
			}*/
			
			$scope.selectedEntity = null;
			
			$scope.setSelected = function(selectedEntity){
				$scope.selectedEntity = selectedEntity;
			}
			
			
			$scope.firstone = function(){
				$scope.setSelected($scope.entities[0]);
			}
			
			$scope.previous = function(selectedEntity){
				var index = -1;
				
				for(i = 0 ; i  < $scope.entities.length;i++){
					if($scope.entities[i].idMessage == $scope.selectedEntity.idMessage)
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
					if($scope.entities[i].idMessage == $scope.selectedEntity.idMessage)
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
			
			
			
			
			$scope.exportToXml = function(){
				service.exportToXml().then(function(response) {
					findAll();
					$location.path('interbankTransfer');
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
					//$scope.searchEntity = {id : null,pttNumber:"" ,name : "",country:null};

				},
				function(response){
					
				})
			}
			
			$scope.showModalBankSender = function(){
				var modal = document.getElementById('modalBankSender');
				modal.style.display = "block";		
			}
			$scope.closeBankSenderModal = function(){
				var modal = document.getElementById('modalBankSender');
				modal.style.display  = "none";
			}
			$scope.showModalBank = function(){
				var modal = document.getElementById('modalBank');
				modal.style.display = "block";		
			}
			$scope.closeBankModal = function(){
				var modal = document.getElementById('modalBank');
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
			$scope.setSelectedBankSenderSearch = function(senderBank){
				$scope.searchEntity.senderBank = senderBank;
			}
			$scope.setSelectedBankSearch = function(bank){
				$scope.searchEntity.bank = bank;
			}	
			$scope.setSelectedBankSender = function(senderBank){
				$scope.entity.senderBank = senderBank;
			}
			$scope.setSelectedBank = function(bank){
				$scope.entity.bank = bank;
			}
			$scope.deselect = function(){
				$scope.selectedEntity = null;
				$scope.searchEntity = {idMessage : null,typeOfMessage:"" ,date : "",sum:null,senderBank:{},bank:{}};

			}
			$scope.refresh = function(){
				$scope.selectedEntity = null;
				$scope.searchEntity = {idMessage : null,typeOfMessage:"" ,date : "",sum:null,senderBank:{},bank:{}};

				findAll();
			}	
			
			$scope.next = function(){
				if(!($scope.selectedEntity))
					return;
				sessionStorage.setItem("nextInterbankTransfer", $scope.selectedEntity.idMessage);
				sessionStorage.setItem("backInterbankTransfer", $scope.entities);
				$location.path('/itemTransfer');
			
			
			}
			
			$scope.back = function(){
				var backFilter = sessionStorage.getItem("backFilter");
				sessionStorage.removeItem("backFilter");
				if(backFilter == null)
					return;
				
				$location.path("/bank");
			
			}
}]);


