var services = angular.module('place.services', [ 'ngResource' ]);

services.service('placeService', [ '$http', function($http) {

	this.findAll = function() {
		return $http.get("/place");
	}
	
	this.save = function(place) {
		return $http.post("/place",place);
	}
	
	this.next = function(id) {
		return $http.get("/nextPlaces/" + id);
	}
	
	this.delete = function(place){
		return $http.delete('/deleteplace/' + place)
	}

}]);