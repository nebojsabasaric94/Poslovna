var services = angular.module('exchageRateList.services', [ 'ngResource' ]);

services.service('exchageRateListService', [ '$http', function($http) {

	this.findCountries = function() {
		return $http.get("/exchageRateList");
	}
	
	this.addexchageRateList = function(exchageRateList) {
		return $http.post("/addexchageRateList",exchageRateList);
	}
	
	this.next = function(id) {
		return $http.get("/nextPlaces/" + id);
	}
	
	this.delete = function(exchageRateList){
		return $http.delete('/deleteexchageRateList/' + exchageRateList)
	}

}]);