var services = angular.module('itemTransfer.services', [ 'ngResource' ]);

services.service('itemTransferService', [ '$http', function($http) {

	this.findAll = function() {
		return $http.get("/itemTransfer");
	}
	
	this.save = function(itemTransfer) {
		return $http.post("/itemTransfer",itemTransfer);
	}
	
	this.next = function(id) {
		return $http.get("/nextPlaces/" + id);
	}
	
	this.delete = function(itemTransfer){
		return $http.get('/itemTransfer/deleteItemTransfer/' + itemTransfer)
	}

}]);