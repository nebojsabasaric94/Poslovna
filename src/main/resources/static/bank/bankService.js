var services = angular.module('bank.services', [ 'ngResource' ]);

services.service('bankService', [ '$http', function($http) {

	this.findAll = function() {
		return $http.get("/bank");
	}
	
	this.save = function(bank) {
		return $http.post("/bank",bank);
	}
	
	this.next = function(id) {
		return $http.get("/nextPlaces/" + id);
	}
	
	this.delete = function(bank){
		return $http.get('/bank/deleteBank/' + bank)
	}
	
	

}]);