'use strict';


angular.module('routerApp', ['ui.router',
	'analyticsOfStatements.services', 'analyticsOfStatements.controllers',
	'bank.services', 'bank.controllers',
	'country.services', 'country.controllers',
	'currency.services', 'currency.controllers',
	'currencyRate.services', 'currencyRate.controllers',
	'dailyAccountBalance.services', 'dailyAccountBalance.controllers',
	'exchageRateList.services', 'exchageRateList.controllers',
	'interbankTransfer.services', 'interbankTransfer.controllers',
	'itemTransfer.services', 'itemTransfer.controllers',
	'nationalBank.services', 'nationalBank.controllers',
	'paymentType.services', 'paymentType.controllers',
	'place.services', 'place.controllers',
	'suspension.services', 'suspension.controllers',
	]) 

.config(function($stateProvider,$urlRouterProvider){
	
	//$urlRouterProvider.otherwise('/country');
	
	
	$stateProvider
	.state('analyticsOfStatements',{
		url : '/analyticsOfStatements',
		templateUrl : 'analyticsOfStatements/analyticsOfStatementsPartial.html',
		controller : 'analyticsOfStatementsController'
	})
	
	.state('bank',{
		url : '/bank',
		templateUrl : 'bank/bankPartial.html',
		controller : 'bankController'
	})
	
	.state('country',{
		url : '/country',
		templateUrl : 'country/countryPartial.html',
		controller : 'countryController'
	})
	
	.state('currency',{
		url : '/currency',
		templateUrl : 'currency/currencyPartial.html',
		controller : 'currencyController'
	})
	
	.state('currencyRate',{
		url : '/currencyRate',
		templateUrl : 'currencyRate/currencyRatePartial.html',
		controller : 'currencyRateController'
	})
	
	.state('dailyAccountBalance',{
		url : '/dailyAccountBalance',
		templateUrl : 'dailyAccountBalance/dailyAccountBalancePartial.html',
		controller : 'dailyAccountBalanceController'
	})
	
	.state('exchageRateList',{
		url : '/exchageRateList',
		templateUrl : 'exchageRateList/exchageRateListPartial.html',
		controller : 'exchageRateListController'
	})
	
	.state('interbankTransfer',{
		url : '/interbankTransfer',
		templateUrl : 'interbankTransfer/interbankTransferPartial.html',
		controller : 'interbankTransferController'
	})
	
	.state('itemTransfer',{
		url : '/itemTransfer',
		templateUrl : 'itemTransfer/itemTransferPartial.html',
		controller : 'itemTransferController'
	})
	
	.state('nationalBank',{
		url : '/nationalBank',
		templateUrl : 'nationalBank/nationalBankPartial.html',
		controller : 'nationalBankController'
	})
	
	.state('paymentType',{
		url : '/paymentType',
		templateUrl : 'paymentType/paymentTypePartial.html',
		controller : 'paymentTypeController'
	})
	
	
	.state('place',{
		url : '/place',
		templateUrl : 'place/placePartial.html',
		controller : 'placeController'
	})
	
	.state('suspension',{
		url : '/suspension',
		templateUrl : 'suspension/suspensionPartial.html',
		controller : 'suspensionController'
	})
	
});
