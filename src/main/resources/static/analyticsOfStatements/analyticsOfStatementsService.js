var services = angular.module('analyticsOfStatements.services', [ 'ngResource' ]);

services.service('analyticsOfStatementsService', [ '$http', function($http) {

	this.findAll = function() {
		return $http.get("/analyticsOfStatements");
	}
	
	this.save = function(analyticsOfStatements) {
		return $http.post("/analyticsOfStatements",analyticsOfStatements);
	}
	
	this.next = function(id) {
		return $http.get("/nextPlaces/" + id);
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
	
}]);