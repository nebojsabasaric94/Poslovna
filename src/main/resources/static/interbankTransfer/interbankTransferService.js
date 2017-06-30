var services = angular.module('interbankTransfer.services', [ 'ngResource' ]);

services.service('interbankTransferService', [ '$http', function($http) {

	this.findAll = function() {
		return $http.get("/interbankTransfer");
	}
	
	this.exportToXml = function() {
		return $http.post("/interbankTransfer/xml");
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
	
	this.nextInterbankTransfer = function(id){
		return $http.get("/interbankTransfer/next/" + id)
		
	}
}]);