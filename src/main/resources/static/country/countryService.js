var services = angular.module('country.services', [ 'ngResource' ]);

services.service('countryService', [ '$http', function($http) {

	this.findCountries = function() {
		return $http.get("/country");
	}
	
	this.addcountry = function(country) {
		return $http.post("/addcountry",country);
	}
	
	this.next = function(id) {
		return $http.get("/nextPlaces/" + id);
	}
	
	this.delete = function(country){
		return $http.delete('/deletecountry/' + country)
	}

}]);