var services = angular.module('place.services', [ 'ngResource' ]);

services.service('placeService', [ '$http', function($http) {

	this.findCountries = function() {
		return $http.get("/place");
	}
	
	this.addplace = function(place) {
		return $http.post("/addplace",place);
	}
	
	this.next = function(id) {
		return $http.get("/nextPlaces/" + id);
	}
	
	this.delete = function(place){
		return $http.delete('/deleteplace/' + place)
	}

}]);