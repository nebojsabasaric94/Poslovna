var services = angular.module('legalEntity.services',['ngResource']);

services.service('legalEntityService',['$http',function($http){

	this.findAllBusinessActivities = function(){
		return $http.get('/businessActivityCode')
	}
	
	this.findAllPlaces = function(){
		return $http.get("/place");
	}
	
	this.findAll = function(){
		return $http.get("/legalEntity");
	}

	this.search = function(legalEntity){
		return $http.post("/legalEntity/search",legalEntity);
	}
	this.delete = function(id){
		return $http.delete("/legalEntity/"+id+"/")
	}
	
	this.nextFilterBussinesActivity = function(id){
		return $http.get("/legalEntity/nextFilterBussinesActivity/" + id);
	}
	
}])
