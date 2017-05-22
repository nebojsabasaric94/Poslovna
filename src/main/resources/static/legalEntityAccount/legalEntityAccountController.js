var app = angular.module('legalEntityAccount.controllers',[]);
app.controller('legalEntityAccountController',['$scope','legalEntityAccountService','$location',
	function($scope,service,$location){
	
	$scope.searchEntity = {id:null,brojRacuna:"",datumOtvaranja : "",vazeci:"true",client: null,bank:null,currency:null};
	
	findAll();
	function findAll() {
		service.findAll().then(function(response) {
			for(i = 0; i < response.data.length;i++){
				response.data[i].datumOtvaranja = transformDate(new Date(response.data[i].datumOtvaranja));
			}
			$scope.entities = response.data;
			checkIfLegalEntity();
		},
		function(response){
			
		});
	}
	
	function checkIfLegalEntity(){
		for(i=0;i<$scope.entities.length;i++){
			if($scope.entities[i].typeOfClient == "Pravno lice"){
				service.checkIfLegalEntity($scope.entities[i].id)
				.then(function(response){
					if(i < $scope.entities.length){
						response.data.datumOtvaranja =transformDate(new Date(response.data.datumOtvaranja));
						$scope.entities[i] = response.data;
					}
				},
				function(response){
					
				})
			}
		}
	}
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
	$scope.search = function(){
		service.search($scope.searchEntity)
		.then(function(response){
			for(i = 0; i < response.data.length;i++){
				response.data[i].datumOtvaranja = transformDate(new Date(response.data[i].datumOtvaranja));
			}
			$scope.entities = response.data; 
			//$scope.searchEntity = {id : null,pttNumber:"" ,name : "",country:null};

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