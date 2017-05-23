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
	
	this.checkIfLegalEntity = function(id){
		return $http.get('/legalEntity/'+id+"/");
	}
	
	this.findAllClients = function(){
		return $http.get('/client');
	}
	this.findAllLegalEntities = function(){
		return $http.get('/legalEntity')
	}
	
	this.findAllBanks = function(){
		return $http.get('/bank');
	}
	this.findAllCurrencies = function(){
		return $http.get('/currency');
	}
	this.search = function(entity){
		return $http.post('/legalEntityAccount/search',entity);
	}

}])