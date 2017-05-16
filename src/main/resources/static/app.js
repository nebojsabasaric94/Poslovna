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
	.state('country.search',{
		url : '/search',
		templateUrl : 'country/searchCountry.html',
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
	.state('place.search',{
		url : '/search',
		templateUrl : 'place/searchPlace.html'
	})	
	.state('suspension',{
		url : '/suspension',
		templateUrl : 'suspension/suspensionPartial.html',
		controller : 'suspensionController'
	})
	
	.state('bank.add', {
		url : '/addBank',
		templateUrl : 'bank/addBank.html'
	})
	
	.state('analyticsOfStatements.add', {
		url : '/addAnalyticsOfStatements',
		templateUrl : 'analyticsOfStatements/addAnalyticsOfStatements.html'
	})
	
	.state('country.add', {
		url : '/addCountry',
		templateUrl : 'country/addCountry.html'
	})
	
	.state('currency.add', {
		url : '/addCurrency',
		templateUrl : 'currency/addCurrency.html'
	})
	
	.state('currencyRate.add', {
		url : '/addCurrencyRate',
		templateUrl : 'currencyRate/addCurrencyRate.html'
	})
	
	.state('dailyAccountBalance.add', {
		url : '/addDailyAccountBalance',
		templateUrl : 'dailyAccountBalance/addDailyAccountBalance.html'
	})
	
	.state('exchageRateList.add', {
		url : '/addExchageRateList',
		templateUrl : 'exchageRateList/addExchageRateList.html'
	})
	
	
	
	.state('interbankTransfer.add', {
		url : '/addInterbankTransfer',
		templateUrl : 'interbankTransfer/addInterbankTransfer.html'
	})
	
	.state('itemTransfer.add', {
		url : '/addItemTransfer',
		templateUrl : 'itemTransfer/addItemTransfer.html'
	})
	
	.state('nationalBank.add', {
		url : '/addNationalBank',
		templateUrl : 'nationalBank/addNationalBank.html'
	})
	
	.state('paymentType.add', {
		url : '/addPaymentType',
		templateUrl : 'paymentType/addPaymentType.html'
	})
	
	.state('place.add', {
		url : '/addPlace',
		templateUrl : 'place/addPlace.html'
	})
	
	.state('suspension.add', {
		url : '/addSuspension',
		templateUrl : 'suspension/addSuspension.html'
	})
	
	
	
});
