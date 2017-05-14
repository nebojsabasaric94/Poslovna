var services = angular.module('paymentType.services', [ 'ngResource' ]);

services.service('paymentTypeService', [ '$http', function($http) {

	this.findCountries = function() {
		return $http.get("/paymentType");
	}
	
	this.addpaymentType = function(paymentType) {
		return $http.post("/addpaymentType",paymentType);
	}
	
	this.next = function(id) {
		return $http.get("/nextPlaces/" + id);
	}
	
	this.delete = function(paymentType){
		return $http.delete('/deletepaymentType/' + paymentType)
	}

}]);