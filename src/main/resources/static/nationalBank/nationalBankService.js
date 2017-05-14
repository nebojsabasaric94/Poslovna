var services = angular.module('nationalBank.services', [ 'ngResource' ]);

services.service('nationalBankService', [ '$http', function($http) {

	this.findCountries = function() {
		return $http.get("/nationalBank");
	}
	
	this.addnationalBank = function(nationalBank) {
		return $http.post("/addnationalBank",nationalBank);
	}
	
	this.next = function(id) {
		return $http.get("/nextPlaces/" + id);
	}
	
	this.delete = function(nationalBank){
		return $http.delete('/deletenationalBank/' + nationalBank)
	}

}]);