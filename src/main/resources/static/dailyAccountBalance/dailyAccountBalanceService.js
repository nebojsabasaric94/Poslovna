var services = angular.module('dailyAccountBalance.services', [ 'ngResource' ]);

services.service('dailyAccountBalanceService', [ '$http', function($http) {

	this.findCountries = function() {
		return $http.get("/dailyAccountBalance");
	}
	
	this.adddailyAccountBalance = function(dailyAccountBalance) {
		return $http.post("/adddailyAccountBalance",dailyAccountBalance);
	}
	
	this.next = function(id) {
		return $http.get("/nextPlaces/" + id);
	}
	
	this.delete = function(dailyAccountBalance){
		return $http.delete('/deletedailyAccountBalance/' + dailyAccountBalance)
	}

}]);