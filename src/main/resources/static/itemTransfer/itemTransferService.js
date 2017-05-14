var services = angular.module('itemTransfer.services', [ 'ngResource' ]);

services.service('itemTransferService', [ '$http', function($http) {

	this.findCountries = function() {
		return $http.get("/itemTransfer");
	}
	
	this.additemTransfer = function(itemTransfer) {
		return $http.post("/additemTransfer",itemTransfer);
	}
	
	this.next = function(id) {
		return $http.get("/nextPlaces/" + id);
	}
	
	this.delete = function(itemTransfer){
		return $http.delete('/deleteitemTransfer/' + itemTransfer)
	}

}]);