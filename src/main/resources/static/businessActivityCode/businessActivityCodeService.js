var services = angular.module('businessActivityCode.services',['ngResource']);

services.service('businessActivityCodeService',['$http',function($http){
		
		this.findAll = function() {
			return $http.get("/businessActivityCode");
		}		


}]);