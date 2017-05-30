var services = angular.module('businessActivityCode.services',['ngResource']);

services.service('businessActivityCodeService',['$http',function($http){
		
		this.findAll = function() {
			return $http.get("/businessActivityCode");
		}		

		this.search = function(entity){
			return $http.post('/businessActivityCode/search',entity);
		}
		
		this.save = function(businessActivityCode) {
			return $http.post("/businessActivityCode",businessActivityCode);
		}
		
		
}]);