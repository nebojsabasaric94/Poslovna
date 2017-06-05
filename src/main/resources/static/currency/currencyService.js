var services = angular.module('currency.services', [ 'ngResource' ]);

services.service('currencyService', [ '$http', function($http) {

	this.findAllCountries = function(){
		return $http.get("/country");
	}	
	
	this.findAll = function() {
		return $http.get("/currency");
	}
	
	this.save = function(currency) {
		return $http.post("/currency",currency);
	}
	
	this.next = function(id) {
		return $http.get("currency/nextCurrencies/" + id);
	}
	
	this.delete = function(currency){
		return $http.get('/currency/deleteCurrency/' + currency)
	}
	this.search = function(entity){
		return $http.post('/currency/search',entity);
	}
	this.update = function(currency){
		return $http.put("/currency/update", currency);
	}

	
}]);