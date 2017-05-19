var services = angular.module('legalEntity.services',['ngResource']);

services.service('legalEntityService',['$http',function($http){

	this.findAll = function(){
		return $http.get('/legalEntity');
	}
}])
