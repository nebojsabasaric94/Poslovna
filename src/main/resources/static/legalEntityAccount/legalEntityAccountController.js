var app = angular.module('legalEntityAccount.controllers',[]);
app.controller('legalEntityAccountController',['$scope','legalEntityAccountService','$location',
	function($scope,service,$location){
	
		$scope.searchEntity = {id:null,brojRacuna:"",datumOtvaranja : "",vazeci:"true",client: null,bank:null,currency:null};
		$scope.idSelectedEntity = null;
		
		findAll();
		function findAll() {
			service.findAll().then(function(response) {
				for(i = 0; i < response.data.length;i++){
					response.data[i].datumOtvaranja = transformDate(new Date(response.data[i].datumOtvaranja));
				}
				$scope.entities = response.data;
				//checkIfLegalEntity();
			},
			function(response){
				
			});
		}
		
		/*function checkIfLegalEntity(){
			for(i=0;i<$scope.entities.length;i++){
				if($scope.accounts[i].client.typeOfClient == "Pravno lice")
					service.checkIfLegalEntity($scope.entities[i].client.id)
					.then(function(response){
						if(i < $scope.entities.length){
							$scope.entities[i].client = response.data;
			findAll();
			function findAll() {
	
				var nextFilterBank = sessionStorage.getItem("nextFilterBank");
				sessionStorage.removeItem("nextFilterBank");
				var nextFilterCurrency = sessionStorage.getItem("nextFilterCurrency");
				sessionStorage.removeItem("nextFilterCurrency");
				
				if(nextFilterBank != null){
					service.nextBank(nextFilterBank).then(
						function(response){
							$scope.entities = response.data;
						}
					)
				
				} else if(nextFilterCurrency != null){
					service.nextCurrency(nextFilterCurrency).then(
							function(response){
								$scope.entities = response.data;
							}
						)
				} else {
					service.findAll().then(
						function(response) {
						   for(i = 0; i < response.data.length;i++){
							   response.data[i].datumOtvaranja = transformDate(new Date(response.data[i].datumOtvaranja));
						   }
						   $scope.entities = response.data;
						   //checkIfLegalEntity();
					})
			}
		}*/



		function transformDate(dateObj){
			var month = ("0" + (dateObj.getMonth() + 1)).slice(-2); //months from 1-12
			var day = ("0" + dateObj.getDate()).slice(-2);
			var year = dateObj.getFullYear();
	
			var newdate = year + "/" + month + "/" + day;
			return newdate;
		}
		
		function findAllLegalEntities(){
			service.findAllLegalEntities()
			.then(function(response){
				for(i = 0; i < response.data.length;i++)
					$scope.clients.push(response.data[i]);
			},function(response){
				
			})
		}
		$scope.findAllClients = function(){
			service.findAllClients()
			.then(function(response){
				$scope.clients = response.data;
				findAllLegalEntities();
			},
			function(response){
				
			})
		}
		$scope.findAllBanks = function(){
			service.findAllBanks()
			.then(function(response){
				$scope.banks = response.data;
			},
			function(response){
				
			})
		}
		$scope.findAllCurrencies = function(){
			service.findAllCurrencies()
			.then(function(response){
				$scope.currencies = response.data;
			},
			function(response){
				
			})
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
		
		
		
		$scope.search = function(){
			service.search($scope.searchEntity)
			.then(function(response){
				for(i = 0; i < response.data.length;i++){
					response.data[i].datumOtvaranja = transformDate(new Date(response.data[i].datumOtvaranja));
				}
				$scope.entities = response.data; 
	
			},
			function(response){
				
			})
		}	
		
		$scope.showClientModalSearch = function(){
			var modal = document.getElementById('clientModalSearch');
			modal.style.display = "block";		
		}
		$scope.closeClientModalSearch = function(){
			var modal = document.getElementById('clientModalSearch');
			modal.style.display  = "none";
		}
		$scope.showBankModalSearch = function(){
			var modal = document.getElementById('bankModalSearch');
			modal.style.display = "block";		
		}
		$scope.closeBankModalSearch = function(){
			var modal = document.getElementById('bankModalSearch');
			modal.style.display  = "none";
		}
		$scope.showCurrencyModalSearch = function(){
			var modal = document.getElementById('currencyModalSearch');
			modal.style.display = "block";		
		}
		$scope.closeCurrencyModalSearch = function(){
			var modal = document.getElementById('currencyModalSearch');
			modal.style.display  = "none";
		}	
		$scope.setSelectedClientSearch = function(client){
			$scope.searchEntity.client = client;
		}
		$scope.setSelectedBankSearch = function(bank){
			$scope.searchEntity.bank = bank;
		}
		$scope.setSelectedCurrencySearch = function(currency){
			$scope.searchEntity.currency = currency;
		}
	
		$scope.deselect = function(){
			$scope.selectedEntity = null;
			$scope.searchEntity = {id:null,brojRacuna:"",datumOtvaranja : "",vazeci:"true",client: null,bank:null,currency:null};
		}
		$scope.refresh = function(){
			$scope.selectedEntity = null;
			$scope.searchEntity = {id:null,brojRacuna:"",datumOtvaranja : "",vazeci:"true",client: null,bank:null,currency:null};
			findAll();
		}

}])