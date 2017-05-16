var services = angular.module('currencyRate.services', [ 'ngResource' ]);

services.service('currencyRateService', [ '$http', function($http) {

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
		return $http.delete('/deletecurrencyRate/' + currencyRate)
	}

}]);