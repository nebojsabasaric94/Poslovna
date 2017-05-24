var services = angular.module('itemTransfer.services', [ 'ngResource' ]);

services.service('itemTransferService', [ '$http', function($http) {

	this.findAll = function() {
		return $http.get("/itemTransfer");
	}
	
	this.save = function(itemTransfer) {
		return $http.post("/itemTransfer",itemTransfer);
	}
	
	this.nextInterbankTransfer = function(id) {
		return $http.get("/itemTransfer/nextInterbankTransfer/" + id);
	}
	
	this.nextAnalyticsOfStatements = function(id){
		return $http.get("/itemTransfer/nextAnalyticsOfStatements/" + id);
	}
	
	this.delete = function(itemTransfer){
		return $http.get('/itemTransfer/deleteItemTransfer/' + itemTransfer)
	}

	this.findAllAnalyticsOfStatements = function(){
		return $http.get('/analyticsOfStatements');
	}
	
	this.findAllInterbankTransfers  = function(){
		return $http.get('/interbankTransfer');
	}
	
	this.search = function(item){
		return $http.post('/itemTransfer/search',item);
	}
}]);