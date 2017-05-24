var services = angular.module('dailyAccountBalance.services', [ 'ngResource' ]);

services.service('dailyAccountBalanceService', [ '$http', function($http) {

	this.findAll = function() {
		return $http.get("/dailyAccountBalance");
	}
	
	this.save = function(dailyAccountBalance) {
		return $http.post("/dailyAccountBalance",dailyAccountBalance);
	}
	
	this.next = function(id) {
		return $http.get("/dailyAccountBalance/nextLegalEntityAccount/" + id);
	}
	
	this.delete = function(dailyAccountBalance){
		return $http.get('/dailyAccountBalance/deleteDailyAccountBalance/' + dailyAccountBalance)
	}
	
	this.search = function(entity){
		return $http.post('/dailyAccountBalance/search',entity);
	}
	this.findAllAccounts = function(){
		return $http.get('/legalEntityAccount');
	}
	this.checkIfLegalEntity = function(id){
		return $http.get('/legalEntity/'+id+"/");
	}
	
}]);