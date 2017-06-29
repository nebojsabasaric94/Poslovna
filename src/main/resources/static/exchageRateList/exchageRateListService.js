var services = angular.module('exchageRateList.services', [ 'ngResource' ]);

services.service('exchageRateListService', [ '$http', function($http) {

	this.findAllBanks = function(){
		return $http.get("/bank");
	}	
	this.findAll = function() {
		return $http.get("/exchangeRateList");
	}
	
	this.save = function(exchageRateList) {
		return $http.post("/exchangeRateList",exchangeRateList);
	}
	
	this.next = function(id) {
		return $http.get("/exchangeRateList/nextExchangeRateList/" + id);
	}
	
	this.delete = function(exchageRateList){
		return $http.get('/exchangeRateList/deleteExchageRateList/' + exchageRateList)
	}
	this.search = function(entity){
		return $http.post('/exchangeRateList/search',entity);
	}
	this.update = function(exchangeRateList){
		return $http.put("/exchangeRateList/update", exchangeRateList);
	}

}]);