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
	
	this.nextFilterClient = function(id){
		return $http.get("/legalEntityAccount/nextClient/" + id)
	}
	
	this.nextLegalEntity = function(id){
		return $http.get("/legalEntityAccount/nextLegalEntity/" + id)
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
	
	this.save = function(legalEntityAccount) {
		return $http.post("/legalEntityAccount",legalEntityAccount);
	}
	
	this.exportStatementsToXml = function(selectedEntity,startDate,endDate){
		return $http.post("/accountStatement/xml/"+startDate+"/"+endDate,selectedEntity)
	}
	this.exportToPdf = function(selectedEntity,startDate,endDate){
		return $http.post("/accountStatement/pdf/"+startDate+"/"+endDate,selectedEntity)
	}
	
	this.update = function(legalEntityAccount){
		return $http.put("/legalEntityAccount/update", legalEntityAccount);
	}
}])