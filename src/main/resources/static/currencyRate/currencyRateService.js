var services = angular.module('currencyRate.services', [ 'ngResource' ]);

services.service('currencyRateService', [ '$http', function($http) {

	this.findAllCurrencies = function(){
		return $http.get('currency');
	}
	
	this.findAllExchangeRateLists = function(){
		return $http.get('exchangeRateList');
	}	
	this.findAll = function() {
		return $http.get("/currencyRate");
	}
	
	this.save = function(currencyRate) {
		return $http.post("/currencyRate",currencyRate);
	}
	
	this.next = function(id) {
		return $http.get("/nextPlaces/" + id);
	}
	
	this.delete = function(currencyRate){
		return $http.get('/currencyRate/deleteCurrencyRate/' + currencyRate)
	}
	this.search = function(entity){
		return $http.post('/currencyRate/search',entity);
	}
}]);