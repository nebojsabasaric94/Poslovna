var services = angular.module('client.services',['ngResource']);

services.service('clientService',['$http',function($http){
	this.findAll =  function(){
		return $http.get('/client');
	}
}])