var services = angular.module('paymentType.services', [ 'ngResource' ]);

services.service('paymentTypeService', [ '$http', function($http) {

	this.findAll = function() {
		return $http.get("/paymentType");
	}
	
	this.save = function(paymentType) {
		return $http.post("/paymentType",paymentType);
	}
	
	this.next = function(id) {
		return $http.get("/nextPlaces/" + id);
	}
	
	this.delete = function(paymentType){
		return $http.get('/paymentType/deletePaymentType/' + paymentType)
	}
	this.search = function(entity){
		return $http.post('/paymentType/search',entity);
	}
	this.update = function(paymentType){
		return $http.put("/paymentType/update", paymentType);
	}
}]);