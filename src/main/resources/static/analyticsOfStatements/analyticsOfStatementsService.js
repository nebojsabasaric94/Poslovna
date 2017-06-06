var services = angular.module('analyticsOfStatements.services', [ 'ngResource' ]);

services.service('analyticsOfStatementsService', [ '$http', function($http) {

	this.findAll = function() {
		return $http.get("/analyticsOfStatements");
	}
	
	this.importXml = function(file) {
		return $http.get("/analyticsOfStatements/xml/"+file);
	}
	
	this.nextPlace = function(id) {
		return $http.get("/analyticsOfStatements/nextPlace/" + id);
	}
	
	this.nextCurrency = function(id){
		return $http.get("/analyticsOfStatements/nextCurrency/" + id);
	}
	
	this.nextDaily = function(id){
		return $http.get("/analyticsOfStatements/nextDailyAccountBalance/" + id);
	}
	
	this.nextPaymentType = function(id){
		return $http.get("/analyticsOfStatements/nextPaymentType/" + id);
	}
	
	this.delete = function(analyticsOfStatements){
		return $http.delete('/deleteanalyticsOfStatements/' + analyticsOfStatements)
	}
	
	this.findAllDailyAccountBalances = function(){
		return $http.get('/dailyAccountBalance');
	}
	
	this.findAllPaymentTypes = function(){
		return $http.get('/paymentType');
	}
	
	this.findAllPaymentCurrencies = function(){
		return $http.get('/currency')
	}
	
	this.findAllPlaces = function(){
		return $http.get('/place');
	}
	
	this.search = function(entity){
		return $http.post('/analyticsOfStatements/search',entity);
	}
	
	this.findXmlFiles = function(){
		return $http.get('/analyticsOfStatements/files');
	}
	
}]);