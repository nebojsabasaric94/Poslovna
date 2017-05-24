var services = angular.module('interbankTransfer.services', [ 'ngResource' ]);

services.service('interbankTransferService', [ '$http', function($http) {

	this.findAll = function() {
		return $http.get("/interbankTransfer");
	}
	
	this.save = function(interbankTransfer) {
		return $http.post("/interbankTransfer",interbankTransfer);
	}
	
	this.next = function(id) {
		return $http.get("/nextPlaces/" + id);
	}
	
	this.delete = function(interbankTransfer){
		return $http.get('/interbankTransfer/deleteInterbankTransfer/' + interbankTransfer)
	}
	this.findAllBanks = function(){
		return $http.get('/bank');
	}
	this.search = function(entity){
		return $http.post('/interbankTransfer/search',entity);
	}
}]);