var services = angular.module('client.services',['ngResource']);

services.service('clientService',['$http',function($http){
	
	this.findAllPlaces = function(){
		return $http.get('/place');
	}
	this.findAll =  function(){
		return $http.get('/client');
	}
	this.search =  function(client){
		return $http.post('/client/search',client);
	}
	
	this.next = function(id){
		return $http.get('/client/nextPlace/'+id);
	}
	
	this.save = function(client) {
		return $http.post("/client",client);
	}

	this.update = function(client){
		return $http.put("/client/update", client);
	}
	
}])