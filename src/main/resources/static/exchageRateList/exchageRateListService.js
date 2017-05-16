var services = angular.module('exchageRateList.services', [ 'ngResource' ]);

services.service('exchageRateListService', [ '$http', function($http) {

	this.findAll = function() {
		return $http.get("/exchangeRateList");
	}
	
	this.save = function(exchageRateList) {
		return $http.post("/exchangeRateList",exchageRateList);
	}
	
	this.next = function(id) {
		return $http.get("/nextPlaces/" + id);
	}
	
	this.delete = function(exchageRateList){
		return $http.get('/exchangeRateList/deleteExchageRateList/' + exchageRateList)
	}

}]);