var services = angular.module('nationalBank.services', [ 'ngResource' ]);

services.service('nationalBankService', [ '$http', function($http) {

	this.findAll = function() {
		return $http.get("/nationalBank");
	}
	
	this.save = function(nationalBank) {
		return $http.post("/nationalBank",nationalBank);
	}
	
	this.next = function(id) {
		return $http.get("/nextPlaces/" + id);
	}
	
	this.delete = function(nationalBank){
		return $http.get('/nationalBank/deleteNationalBank/' + nationalBank)
	}

}]);