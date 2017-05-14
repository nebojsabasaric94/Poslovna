var services = angular.module('currency.services', [ 'ngResource' ]);

services.service('currencyService', [ '$http', function($http) {

	this.findCountries = function() {
		return $http.get("/currency");
	}
	
	this.addcurrency = function(currency) {
		return $http.post("/addcurrency",currency);
	}
	
	this.next = function(id) {
		return $http.get("/nextPlaces/" + id);
	}
	
	this.delete = function(currency){
		return $http.delete('/deletecurrency/' + currency)
	}

}]);