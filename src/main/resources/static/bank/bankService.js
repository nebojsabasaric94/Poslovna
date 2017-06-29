var services = angular.module('bank.services', [ 'ngResource' ]);

services.service('bankService', [ '$http', function($http) {

	this.findAll = function() {
		return $http.get("/bank");
	}
	
	this.save = function(bank) {
		return $http.post("/bank",bank);
	}
	
	this.nextBank = function(id) {
		return $http.get("/nextBank/" + id);
	}
	
	this.delete = function(bank){
		return $http.get('/bank/deleteBank/' + bank)
	}
	
	this.search = function(entity){
		return $http.post('/bank/search',entity);
	}
	
	this.exportAccounts = function(id){
		return $http.get('/legalEntityAccount/pdf/'+id);
		
	}
	this.update = function(bank){
		return $http.put("/bank/update", bank);
	}


}]);