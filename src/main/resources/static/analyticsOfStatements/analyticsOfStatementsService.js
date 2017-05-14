var services = angular.module('analyticsOfStatements.services', [ 'ngResource' ]);

services.service('analyticsOfStatementsService', [ '$http', function($http) {

	this.findCountries = function() {
		return $http.get("/analyticsOfStatements");
	}
	
	this.addanalyticsOfStatements = function(analyticsOfStatements) {
		return $http.post("/addanalyticsOfStatements",analyticsOfStatements);
	}
	
	this.next = function(id) {
		return $http.get("/nextPlaces/" + id);
	}
	
	this.delete = function(analyticsOfStatements){
		return $http.delete('/deleteanalyticsOfStatements/' + analyticsOfStatements)
	}

}]);