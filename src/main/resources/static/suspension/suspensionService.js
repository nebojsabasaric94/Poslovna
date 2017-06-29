var services = angular.module('suspension.services', [ 'ngResource' ]);

services.service('suspensionService', [ '$http', function($http) {

	this.findAll = function() {
		return $http.get("/suspension");
	}
	
	this.save = function(suspension) {
		return $http.post("/suspension",suspension);
	}
	
	this.next = function(id) {
		return $http.get("/nextPlaces/" + id);
	}
	
	this.delete = function(suspension){
		return $http.get('/suspension/deleteSuspension/' + suspension)
	}
	
	this.findAllAccounts = function(){
		return $http.get('/legalEntityAccount');
	}
	this.checkIfLegalEntity = function(id){
		return $http.get('/legalEntity/'+id+"/");
	}
	
	this.search = function(suspension){
		return $http.post('/suspension/search',suspension);
	}

	this.update = function(suspension){
		return $http.put("/suspension/update", suspension);
	}
}]);