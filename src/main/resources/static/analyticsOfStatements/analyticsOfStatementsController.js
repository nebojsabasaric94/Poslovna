var app = angular.module('analyticsOfStatements.controllers', []);

app.controller('analyticsOfStatementsController', ['$scope','analyticsOfStatementsService','$location',
		function($scope, service, $location) {
			$scope.searchEntity = {debtor_originator:"",purposeOfPayment:"",creditor_recipient:"",dateOfReceipt:"",currencyDate:"",debtorAccount:"",
					modelAssigments:null,referenceNumberAssigments:"",accountCreditor:"",modelApproval:null,referenceNumberCreditor:"",emergency:false,
					sum:null,typeOfMistake:null,status:"",dailyAccountBalance:{},paymentType:{},paymentCurrency:{},place:{}
			};

			$scope.selectedDailyAccountBalanceDate = "";
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
					$location.path('analyticsOfStatements');
				},
				function(response){
					alert("Dodavanje neuspesno");
				}
				);
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
			
			$scope.deselect = function(){
				$scope.selectedEntity = null;
				$scope.searchEntity = {debtor_originator:"",purposeOfPayment:"",creditor_recipient:"",dateOfReceipt:"",currencyDate:"",debtorAccount:"",
						modelAssigments:null,referenceNumberAssigments:"",accountCreditor:"",modelApproval:null,referenceNumberCreditor:"",emergency:false,
						sum:null,typeOfMistake:null,status:"",dailyAccountBalance:{},paymentType:{},paymentCurrency:{},place:{}
				};

			}
			$scope.refresh = function(){
				$scope.selectedEntity = null;
				$scope.searchEntity = {debtor_originator:"",purposeOfPayment:"",creditor_recipient:"",dateOfReceipt:"",currencyDate:"",debtorAccount:"",
						modelAssigments:null,referenceNumberAssigments:"",accountCreditor:"",modelApproval:null,referenceNumberCreditor:"",emergency:false,
						sum:null,typeOfMistake:null,status:"",dailyAccountBalance:{},paymentType:{},paymentCurrency:{},place:{}
				};
				findAll();
			}			
			

}]);


