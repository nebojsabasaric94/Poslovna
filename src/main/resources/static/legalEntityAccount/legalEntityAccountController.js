var app = angular.module('legalEntityAccount.controllers',[]);
app.controller('legalEntityAccountController',['$scope','legalEntityAccountService','$location',
	function($scope,service,$location){
	
		$scope.searchEntity = {id:null,brojRacuna:"",datumOtvaranja : "",vazeci:"",client: null,bank:null,currency:null};
		$scope.entity = {id:null,brojRacuna:"",datumOtvaranja : "",vazeci:"true",client: null,bank:null,currency:null};
		$scope.idSelectedEntity = null;
		
		$scope.updatedEntity={id:null,brojRacuna:"",datumOtvaranja : "",vazeci:"true",client: null,bank:null,currency:null};

			findAll();
			function findAll() {
	
				var nextFilterBank = sessionStorage.getItem("nextFilterBank");
				sessionStorage.removeItem("nextFilterBank");
				var nextFilterCurrency = sessionStorage.getItem("nextFilterCurrency");
				sessionStorage.removeItem("nextFilterCurrency");
				
				var nextFilterClient = sessionStorage.getItem("nextFilterClient");
				sessionStorage.removeItem("nextFilterClient");
				
				var nextFilterLegalEntity = sessionStorage.getItem("nextFilterLegalEntity");
				sessionStorage.removeItem("nextFilterLegalEntity");
				
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
				} else if (nextFilterClient != null){
					service.nextFilterClient(nextFilterClient).then(
							function(response){
								$scope.entities = response.data;
							}
						)
				} else if( nextFilterLegalEntity != null) {
					service.nextLegalEntity(nextFilterLegalEntity).then(
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
		
		$scope.add = function(){
			service.save($scope.entity).then(function(response) {
				findAll();
				$location.path('legalEntityAccount');
			},
			function(response){
				alert("Dodavanje neuspesno");
			}
			);
		}
		
		$scope.setSelected = function(selectedEntity){
			$scope.showUpdateForm = true;
			$scope.selectedEntity = selectedEntity;
			$scope.updatedEntity.id=$scope.selectedEntity.id;
			$scope.updatedEntity.brojRacuna=selectedEntity.brojRacuna;
			$scope.updatedEntity.datumOtvaranja=selectedEntity.datumOtvaranja;
			$scope.updatedEntity.bank = selectedEntity.bank;
			$scope.updatedEntity.client =selectedEntity.client;
			$scope.updatedEntity.currency=selectedEntity.currency;
			$scope.updatedEntity.vazeci=selectedEntity.vazeci;
			
			$scope.showUpdateForm = true;
		}
		
		$scope.saveChanges = function(){
			
			service.update($scope.updatedEntity).then(function(response){
				for(i = 0 ; i < $scope.entities.length;i++){ 
							if($scope.entities[i].id == $scope.updatedEntity.id){
								//response.data[i].datumOtvaranja = transformDate(new Date(response.data[i].datumOtvaranja));
								$scope.entities[i] = response.data;
										
							
							}
						}
						$scope.showUpdateForm = false;
					},
					function(response){
						
					})
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
		$scope.exportStatementsToXml = function(){
			var l = {};
			l.brojRacuna = $scope.selectedEntity.brojRacuna;
			service.exportStatementsToXml(l,$scope.startDate,$scope.endDate)
			.then(function(response){
				$scope.closeExportStatementsModal();
			},
			function(response){
				
			})
		}
		$scope.exportToPdf = function(){
			var l = {};
			l.brojRacuna = $scope.selectedEntity.brojRacuna;
			service.exportToPdf(l,$scope.startDatePdf,$scope.endDatePdf)
			.then(function(response){
				$scope.closeExportPdfModal();
				window.location = "/accountStatement/getpdf/";
			},
			function(response){
				
			})
		}		
		$scope.showExportStatementsModal = function(){
			var modal = document.getElementById('exportStatementsModal');
			modal.style.display = "block";		
		}
		$scope.closeExportStatementsModal = function(){
			var modal = document.getElementById('exportStatementsModal');
			modal.style.display  = "none";
		}
		$scope.showExportPdfModal = function(){
			var modal = document.getElementById('exportPdfModal');
			modal.style.display = "block";		
		}
		$scope.closeExportPdfModal = function(){
			var modal = document.getElementById('exportPdfModal');
			modal.style.display  = "none";
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
		$scope.setSelectedClientUpdated = function(client){
			$scope.updatedEntity.client = client;
			$scope.searchEntity.client = client;
			$scope.entity.client = client;
		}
		$scope.setSelectedBankSearch = function(bank){
			$scope.searchEntity.bank = bank;
		}
		$scope.setSelectedBankUpdated = function(bank){
			$scope.updatedEntity.bank = bank;
			$scope.searchEntity.bank = bank;
			$scope.entity.bank = bank;
	
		}
		$scope.setSelectedCurrencySearch = function(currency){
			$scope.searchEntity.currency = currency;
		}
		$scope.setSelectedCurrencyUpdated = function(currency){
			$scope.updatedEntity.currency = currency;
			$scope.searchEntity.currency = currency;
			$scope.entity.currency = currency;
		}
		
		
		$scope.setSelectedClient = function(client){
			$scope.entity.client = client;
		}
		$scope.setSelectedBank = function(bank){
			$scope.entity.bank = bank;
		}
		$scope.setSelectedCurrency = function(currency){
			$scope.entity.currency = currency;
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
		$scope.showUpdateForm = false;
		$scope.searchEntity = {id:null,brojRacuna:"",datumOtvaranja : "",vazeci:"",client: null,bank:null,currency:null};
	}
	$scope.refresh = function(){
		$scope.selectedEntity = null;
		$scope.searchEntity = {id:null,brojRacuna:"",datumOtvaranja : "",vazeci:"",client: null,bank:null,currency:null};
		findAll();
	}
	
	$scope.next = function(){
		if(!($scope.selectedEntity))
			return;
		sessionStorage.setItem("nextFilterLegalEntityAccount", $scope.selectedEntity.id);
		sessionStorage.setItem("backFilterLegalEntityAccount", $scope.entities);
		$location.path("/dailyAccountBalance");
	
	}
	
	$scope.back = function(){
		if(sessionStorage.getItem("backFilterBank") != null){
			sessionStorage.removeItem("backFilterBank");
			$location.path("/bank");
		} else if(sessionStorage.getItem("backFilterClient") != null){
			sessionStorage.removeItem("backFilterClient");
			$location.path("/client");
		} else if (sessionStorage.getItem("backFilterCurrency") != null){
			sessionStorage.removeItem("backFilterCurrency");
			$location.path("/currency");
		} else {
			return;
		}
	
	}


}])