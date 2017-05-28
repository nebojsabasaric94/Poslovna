var services = angular.module('country.services', [ 'ngResource' ]);

services.service('countryService', [ '$http', function($http) {

	this.findAll = function() {
		return $http.get("/country");
	}
	
	this.save = function(country) {
		return $http.post("/country",country);
	}
	
	this.next = function(id) {
		return $http.get("/nextPlaces/" + id);
	} 
	
	this.delete = function(country){
		return $http.delete('/country/' + country+"/");
	}
	
	this.update = function(country){
		return $http.put("/country/update", country);
	}
	
	this.search = function(entity){
		return $http.post('/country/search',entity);
	}
	

}]);