var services = angular.module('legalEntityAccount.services',['ngResource']);
services.service('legalEntityAccountService',['$http',function($http){
	this.findAll = function(){
		return $http.get('/legalEntityAccount');
	}
}])