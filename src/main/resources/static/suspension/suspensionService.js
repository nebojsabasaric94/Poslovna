var services = angular.module('suspension.services', [ 'ngResource' ]);

services.service('suspensionService', [ '$http', function($http) {

	this.findAll = function() {
		return $http.get("/suspension");
	}
	
	this.save = function(suspension) {
		return $http.post("/suspension",suspension);
	}
	
	this.next = function(id) {
		return $http.get("/nextPlaces/" + id);
	}
	
	this.delete = function(suspension){
		return $http.get('/suspension/deleteSuspension/' + suspension)
	}

}]);