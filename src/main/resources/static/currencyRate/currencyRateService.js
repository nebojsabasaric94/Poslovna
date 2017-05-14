var services = angular.module('currencyRate.services', [ 'ngResource' ]);

services.service('currencyRateService', [ '$http', function($http) {

	this.findCountries = function() {
		return $http.get("/currencyRate");
	}
	
	this.addcurrencyRate = function(currencyRate) {
		return $http.post("/addcurrencyRate",currencyRate);
	}
	
	this.next = function(id) {
		return $http.get("/nextPlaces/" + id);
	}
	
	this.delete = function(currencyRate){
		return $http.delete('/deletecurrencyRate/' + currencyRate)
	}

}]);