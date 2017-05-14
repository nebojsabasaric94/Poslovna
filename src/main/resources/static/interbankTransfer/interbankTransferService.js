var services = angular.module('interbankTransfer.services', [ 'ngResource' ]);

services.service('interbankTransferService', [ '$http', function($http) {

	this.findCountries = function() {
		return $http.get("/interbankTransfer");
	}
	
	this.addinterbankTransfer = function(interbankTransfer) {
		return $http.post("/addinterbankTransfer",interbankTransfer);
	}
	
	this.next = function(id) {
		return $http.get("/nextPlaces/" + id);
	}
	
	this.delete = function(interbankTransfer){
		return $http.delete('/deleteinterbankTransfer/' + interbankTransfer)
	}

}]);