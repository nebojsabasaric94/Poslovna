var services = angular.module('place.services', [ 'ngResource' ]);

services.service('placeService', [ '$http', function($http) {
	
	this.findAllCountries = function(){
		return $http.get("/country");
	}
	this.findAll = function() {
		return $http.get("/place");
	}
	
	this.save = function(place) {
		return $http.post("/place",place);
	}
	
	this.next = function(id) {
		return $http.get("/place/nextPlaces/" + id);
	}
	
	this.delete = function(place){
		return $http.get("/place/deletePlace/" + place);
	}
	this.search = function(entity){
		return $http.post('/place/search',entity);
	}
	
	this.update = function(place){
		return $http.put("/place/update", place);
	}
}]);