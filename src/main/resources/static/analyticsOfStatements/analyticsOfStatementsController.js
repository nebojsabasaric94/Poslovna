var app = angular.module('analyticsOfStatements.controllers', []);

app.controller('analyticsOfStatementsController', ['$scope','analyticsOfStatementsService','$location',
		function($scope, service, $location) {
			$scope.searchEntity = {debtor_originator:"",purposeOfPayment:"",creditor_recipient:"",dateOfReceipt:"",currencyDate:"",debtorAccount:"",
					modelAssigments:null,referenceNumberAssigments:"",accountCreditor:"",modelApproval:null,referenceNumberCreditor:"",emergency:"",
					sum:null,typeOfMistake:null,status:"",dailyAccountBalance:{},paymentType:{},paymentCurrency:{},place:{}
			};
			$scope.entity = {debtor_originator:"",purposeOfPayment:"",creditor_recipient:"",dateOfReceipt:"",currencyDate:"",debtorAccount:"",
					modelAssigments:null,referenceNumberAssigments:"",accountCreditor:"",modelApproval:null,referenceNumberCreditor:"",emergency:false,
					sum:null,typeOfMistake:null,status:"",dailyAccountBalance:{},paymentType:{},paymentCurrency:{},place:{}
			};
			

			$scope.selectedDailyAccountBalanceDate = "";
			findAll();
		
			function findAll() {

				var nextFilterPlace = sessionStorage.getItem("nextFilterPlace");
				sessionStorage.removeItem("nextFilterPlace");
				
				var nextFilterCurrency = sessionStorage.getItem("nextFilterCurrency");
				sessionStorage.removeItem("nextFilterCurrency");
				
				var nextFilterDaily = sessionStorage.getItem("nextFilterDaily");
				sessionStorage.removeItem("nextFilterDaily");
				
				var nextFilterPaymentType = sessionStorage.getItem("nextFilterPaymentType");
				sessionStorage.removeItem("nextFilterPaymentType");
				
				if(nextFilterPlace != null){
					service.nextPlace(nextFilterPlace).then(
						function(response){
							$scope.entities = response.data;
						}
					)
				} else if (nextFilterCurrency != null) {
					service.nextCurrency(nextFilterCurrency).then(
							function(response){
								$scope.entities = response.data;
							}
						)
				} else if(nextFilterDaily != null){
					service.nextDaily(nextFilterDaily).then(
							function(response){
								$scope.entities = response.data;
							}
						)
				} else if(nextFilterPaymentType != null){
					service.nextPaymentType(nextFilterPaymentType).then(
							function(response){
								$scope.entities = response.data;
							}
						)
				}
				
				else {
				
					service.findAll().then(
						function(response) {
							$scope.entities = response.data;
						});
				
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
					$scope.entities = response.data; 
					//$scope.searchEntity = {id : null,pttNumber:"" ,name : "",country:null};

				},
				function(response){
					
				})
			}
			
			$scope.findXmlFiles = function(){
				service.findXmlFiles()
				.then(function(response){
					$scope.xmlFiles = response.data;
				},
				function(response){
					
				})
			} 
			$scope.importFile = function(file){
				service.importXml(file)
				.then(function(response){
					$scope.findXmlFiles();
					findAll();
					$location.path('analyticsOfStatements');
				},
				function(response){
					
				})
			}			
			
			$scope.showXmlFilesModal = function(){
				var modal = document.getElementById('xmlFilesModal');
				modal.style.display = "block";		
			}
			$scope.closeXmlFilesModal = function(){
				var modal = document.getElementById('xmlFilesModal');
				modal.style.display  = "none";
			}
			
			$scope.showPlaceModal = function(){
				var modal = document.getElementById('placeModal');
				modal.style.display = "block";		
			}
			$scope.closePlaceModal = function(){
				var modal = document.getElementById('placeModal');
				modal.style.display  = "none";
			}
			$scope.showPaymentCurrencyModal = function(){
				var modal = document.getElementById('paymentCurrencyModal');
				modal.style.display = "block";		
			}
			$scope.closePaymentCurrencyModal = function(){
				var modal = document.getElementById('paymentCurrencyModal');
				modal.style.display  = "none";
			}
			$scope.showPaymentTypeModal = function(){
				var modal = document.getElementById('paymentTypeModal');
				modal.style.display = "block";		
			}
			$scope.closePaymentTypeModal = function(){
				var modal = document.getElementById('paymentTypeModal');
				modal.style.display  = "none";
			}
			$scope.showDailyAccountBalanceModal = function(){
				var modal = document.getElementById('dailyAccountBalanceModal');
				modal.style.display = "block";		
			}			
			$scope.closeDailyAccountBalanceModal = function(){
				var modal = document.getElementById('dailyAccountBalanceModal');
				modal.style.display  = "none";
			}
			$scope.findAllDailyAccountBalances = function(){
				service.findAllDailyAccountBalances()
				.then(function(response){
					for(i = 0; i < response.data.length;i++){
						response.data[i].trafficDate = transformDate(new Date(response.data[i].trafficDate));
					}
					$scope.dailyAccountBalances = response.data;
				},
				function(response){
					
				})
			}
			$scope.findAllPaymentTypes = function(){
				service.findAllPaymentTypes()
				.then(function(response){
					$scope.paymentTypes = response.data;
				},
				function(response){
					
				})
			}
			$scope.findAllPaymentCurrencies = function(){
				service.findAllPaymentCurrencies()
				.then(function(response){
					$scope.paymentCurrencies= response.data;
				},
				function(response){
					
				})
			}			
			$scope.findAllPlaces = function(){
				service.findAllPlaces()
				.then(function(response){
					$scope.places = response.data;
				},
				function(response){
					
				})
			}
			$scope.setSelectedDailyAccountBalanceSearch = function(dab){
				$scope.searchEntity.dailyAccountBalance.id = dab.id;
				$scope.selectedDailyAccountBalanceDate = transformDate(new Date(dab.trafficDate));

			}
			$scope.setSelectedPaymentTypeSearch = function(pt){
				$scope.searchEntity.paymentType = pt;
			}	
			$scope.setSelectedPaymentCurrencySearch = function(pc){
				$scope.searchEntity.paymentCurrency = pc;
			}			
			$scope.setSelectedPlaceSearch = function(place){
				$scope.searchEntity.place = place;
			}
			$scope.setSelectedDailyAccountBalance = function(dab){
				$scope.entity.dailyAccountBalance.id = dab.id;
				$scope.selectedDailyAccountBalanceDate = transformDate(new Date(dab.trafficDate));

			}
			$scope.setSelectedPaymentType = function(pt){
				$scope.entity.paymentType = pt;
			}	
			$scope.setSelectedPaymentCurrency = function(pc){
				$scope.entity.paymentCurrency = pc;
			}			
			$scope.setSelectedPlace= function(place){
				$scope.entity.place = place;
			}
			
			$scope.deselect = function(){
				$scope.selectedEntity = null;
				$scope.searchEntity = {debtor_originator:"",purposeOfPayment:"",creditor_recipient:"",dateOfReceipt:"",currencyDate:"",debtorAccount:"",
						modelAssigments:null,referenceNumberAssigments:"",accountCreditor:"",modelApproval:null,referenceNumberCreditor:"",emergency:"",
						sum:null,typeOfMistake:null,status:"",dailyAccountBalance:{},paymentType:{},paymentCurrency:{},place:{}
				};

			}
			$scope.refresh = function(){
				$scope.selectedEntity = null;
				$scope.searchEntity = {debtor_originator:"",purposeOfPayment:"",creditor_recipient:"",dateOfReceipt:"",currencyDate:"",debtorAccount:"",
						modelAssigments:null,referenceNumberAssigments:"",accountCreditor:"",modelApproval:null,referenceNumberCreditor:"",emergency:"",
						sum:null,typeOfMistake:null,status:"",dailyAccountBalance:{},paymentType:{},paymentCurrency:{},place:{}
				};
				findAll();
			}			
			
			$scope.back = function(){
				if(sessionStorage.getItem("backFilterPlace") != null){
					sessionStorage.removeItem("backFilterPlace");
					$location.path("/place");
				} else if(sessionStorage.getItem("backFilterCurrency") != null){
					sessionStorage.removeItem("backFilterCurrency");
					$location.path("/currency");
				} else if(sessionStorage.getItem("backFilterDaily") != null){
					sessionStorage.removeItem("backFilterDaily");
					$location.path("/dailyAccountBalance");
				} else if(sessionStorage.getItem("backFilterPaymentType") != null){
					sessionStorage.removeItem("backFilterPaymentType");
					$location.path("/paymentType");
				} else {
					return;
				}
			}
			
			$scope.next = function(){
				if(!($scope.selectedEntity))
					return;
				sessionStorage.setItem("nextAnalyticsOfStatements", $scope.selectedEntity.itemNumber);
				sessionStorage.setItem("backAnalyticsOfStatements", $scope.entities);
				$location.path('/itemTransfer');
			
			
			
			}

}]);


