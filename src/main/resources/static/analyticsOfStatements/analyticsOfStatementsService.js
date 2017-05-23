var services = angular.module('analyticsOfStatements.services', [ 'ngResource' ]);

services.service('analyticsOfStatementsService', [ '$http', function($http) {

	this.findAll = function() {
		return $http.get("/analyticsOfStatements");
	}
	
	this.save = function(analyticsOfStatements) {
		return $http.post("/analyticsOfStatements",analyticsOfStatements);
	}
	
	this.nextPlace = function(id) {
		return $http.get("/analyticsOfStatements/nextPlace/" + id);
	}
	
	this.nextCurrency = function(id){
		return $http.get("/analyticsOfStatements/nextCurrency/" + id);
	}
	
	this.delete = function(analyticsOfStatements){
		return $http.delete('/deleteanalyticsOfStatements/' + analyticsOfStatements)
	}

}]);