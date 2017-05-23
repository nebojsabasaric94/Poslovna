var services = angular.module('legalEntityAccount.services',['ngResource']);
services.service('legalEntityAccountService',['$http',function($http){
	this.findAll = function(){
		return $http.get('/legalEntityAccount');
	}
	
	this.nextBank = function(id){
		return $http.get("/legalEntityAccount/nextBank/" + id)
	}
	
	this.nextCurrency = function(id){
		return $http.get("/legalEntityAccount/nextCurrency/" + id)
	}
	
}])