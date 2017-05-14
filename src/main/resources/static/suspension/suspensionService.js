var services = angular.module('suspension.services', [ 'ngResource' ]);

services.service('suspensionService', [ '$http', function($http) {

	this.findCountries = function() {
		return $http.get("/suspension");
	}
	
	this.addsuspension = function(suspension) {
		return $http.post("/addsuspension",suspension);
	}
	
	this.next = function(id) {
		return $http.get("/nextPlaces/" + id);
	}
	
	this.delete = function(suspension){
		return $http.delete('/deletesuspension/' + suspension)
	}

}]);