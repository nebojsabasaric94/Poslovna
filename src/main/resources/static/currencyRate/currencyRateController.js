var app = angular.module('currencyRate.controllers', []);

app.controller('currencyRateController', ['$scope','currencyRateService','$location',
		function($scope, service, $location) {

			$scope.idSelectedEntity = null;
			
			$scope.searchEntity = {id : null,buyingExchangeRate:null ,middleExchangeRate:null,sellingExchangeRate:null,
					baseCurrency:null,currencyInList:{},accordingToCurrency:null};
			$scope.entity = {id : null,buyingExchangeRate:null ,middleExchangeRate:null,sellingExchangeRate:null,
					baseCurrency:null,currencyInList:{},accordingToCurrency:null};
			
		//	$scope.updatedEntity={id : null,buyingExchangeRate:null ,middleExchangeRate:null,sellingExchangeRate:null,
			//	baseCurrency:null,currencyInList:{},accordingToCurrency:null};
			$scope.updatedEntity=new Object;
			findAll();
		
			function findAll() {

				var nextFilter = sessionStorage.getItem("nextFilter");
				sessionStorage.removeItem("nextFilter");
				var nextFilterBaseCurrency = sessionStorage.getItem("nextFilterBaseCurrency");
				sessionStorage.removeItem("nextFilterBaseCurrency");
				var nextFilterAccordingToCurrency = sessionStorage.getItem("nextAccordingToCurrency");
				sessionStorage.removeItem("nextAccordingToCurrency");
				
				if(nextFilterBaseCurrency != null){
					service.nextBaseCurrency(nextFilterBaseCurrency).then(
							function(response){
								$scope.entities = response.data;
							}
						)					
				} else if (nextFilterAccordingToCurrency != null){
					service.nextAccordingToCurrency(nextFilterAccordingToCurrency).then(
							function(response){
								$scope.entities = response.data;
							}
						)										
				} else if(nextFilter != null){
					service.next(nextFilter).then(
							function(response){
								$scope.entities = response.data;
							}
						)
				} else {
					service.findAll().then(
							function(response) {
								$scope.entities = response.data;
							});
					
				}
				
				
			}
			
			function transformDate(dateObj){
				var month = ("0" + (dateObj.getMonth() + 1)).slice(-2); //months from 1-12
				var day = ("0" + dateObj.getDate()).slice(-2);
				var year = dateObj.getFullYear();

				var newdate = year + "/" + month + "/" + day;
				return newdate;
			}
			
			
			$scope.setSelected = function(selectedEntity){
				$scope.selectedEntity = selectedEntity;
				$scope.showUpdateForm = true;
				$scope.selectedEntity = selectedEntity;
			//	$scope.updatedEntity.id=$scope.selectedEntity.id;
				//$scope.updatedEntity.buyingExchangeRate=selectedEntity.buyingExchangeRate;
			//	$scope.updatedEntity.middleExchangeRate=selectedEntity.middleExchangeRate;
		//		$scope.updatedEntity.sellingExchangeRate=selectedEntity.sellingExchangeRate;
				$scope.updatedEntity=selectedEntity;
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
			
			
			$scope.add = function(){
				service.save($scope.entity).then(function(response) {
					findAll();
					$location.path('currencyRate');
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
				if($scope.searchEntity.currencyInList != null){
					$scope.searchEntity.currencyInList.date = null;
					$scope.searchEntity.currencyInList.appliedBy = null;
			}
				service.search($scope.searchEntity)
				.then(function(response){
					$scope.entities = response.data; 
					//$scope.searchEntity = {id : null,pttNumber:"" ,name : "",country:null};

				},
				function(response){
					
				})
			}
			
			$scope.showModalSearch = function(base){
				$scope.base = base;
				var modal = document.getElementById('myModalSearch');
				modal.style.display = "block";		
			}
			$scope.closeModal = function(){
				var modal = document.getElementById('myModalSearch');
				modal.style.display  = "none";

			}			
			$scope.showModalSearchExchangeRateLists = function(){
				var modal = document.getElementById('myModalSearchExchangeRateLists');
				modal.style.display = "block";					
			}
			$scope.closeModalSearchExchangeRateLists = function(){
				var modal = document.getElementById('myModalSearchExchangeRateLists');
				modal.style.display = "none";					
			}
			
			$scope.findAllCurrencies = function(){
				service.findAllCurrencies()
				.then(function(response){
					$scope.currencies = response.data;
				},
				function(response){
					
				})
			}
			
			$scope.findAllExchangeRateLists = function(){
				service.findAllExchangeRateLists()
				.then(function(response){
					for(i = 0; i < response.data.length;i++){
						response.data[i].date = transformDate(new Date(response.data[i].date));
						response.data[i].appliedBy = transformDate(new Date(response.data[i].appliedBy));
					}
					$scope.exchangeRateLists = response.data;
				},
				function(response){
					
				})
			}				
			
			$scope.setSelectedCurrencySearch = function(currency){
				if($scope.base)
					$scope.searchEntity.baseCurrency = currency;
				else
					$scope.searchEntity.accordingToCurrency = currency;
			}	
			
			$scope.setSelectedCurrencyUpdated = function(currency){
				if($scope.base)
					$scope.updatedEntity.baseCurrency = currency;
				else
					$scope.updatedEntity.accordingToCurrency = currency;
			}	
			
			$scope.setSelectedExchangeRateList = function(exchangeRateList){
				$scope.searchEntity.currencyInList.id = exchangeRateList.id;
			}		
			
			
			$scope.setSelectedCurrency = function(currency){
				if($scope.base)
					$scope.entity.baseCurrency = currency;
				else
					$scope.entity.accordingToCurrency = currency;
			}			
			
			$scope.setSelectedExchangeRateListAdd = function(exchangeRateList){
				$scope.entity.currencyInList.id = exchangeRateList.id;
			}	
			$scope.setSelectedExchangeRateListUpdated = function(exchangeRateList){
				$scope.updatedEntity.currencyInList.id = exchangeRateList.id;
			}	
			
			$scope.deselect = function(){
				$scope.selectedEntity = null;
				$scope.showUpdateForm = false;
				$scope.searchEntity = {id : null,buyingExchangeRate:null ,middleExchangeRate:null,sellingExchangeRate:null,
						baseCurrency:null,currencyInList:{},accordingToCurrency:null};
			}
			$scope.refresh = function(){
				$scope.selectedEntity = null;
				$scope.showUpdateForm = false;
				$scope.searchEntity = {id : null,buyingExchangeRate:null ,middleExchangeRate:null,sellingExchangeRate:null,
						baseCurrency:null,currencyInList:{},accordingToCurrency:null};
				findAll();
			}
			
			$scope.back = function(){
				if(sessionStorage.getItem("backAccordingToCurrency") != null){
					sessionStorage.removeItem("backAccordingToCurrency");
					$location.path("/currency");
				} else if(sessionStorage.getItem("backFilterBaseCurrency") != null){
					sessionStorage.removeItem("backFilterBaseCurrency");
					$location.path("/currency");
				} else if (sessionStorage.getItem("backFilter") != null){
					sessionStorage.removeItem("backFilter");
					$location.path("/exchageRateList");
				} else {
					return;
				}
			}
}]);


