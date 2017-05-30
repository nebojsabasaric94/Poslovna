'use strict';


angular.module('routerApp', ['ui.router',
	'analyticsOfStatements.services', 'analyticsOfStatements.controllers',
	'bank.services', 'bank.controllers',
	'businessActivityCode.services','businessActivityCode.controllers',
	'client.services','client.controllers',
	'country.services', 'country.controllers',
	'currency.services', 'currency.controllers',
	'currencyRate.services', 'currencyRate.controllers',
	'dailyAccountBalance.services', 'dailyAccountBalance.controllers',
	'exchageRateList.services', 'exchageRateList.controllers',
	'interbankTransfer.services', 'interbankTransfer.controllers',
	'itemTransfer.services', 'itemTransfer.controllers',
	'legalEntity.services','legalEntity.controllers',
	'legalEntityAccount.services','legalEntityAccount.controllers',
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
	.state('analyticsOfStatements.search',{
		url : '/search',
		templateUrl : 'analyticsOfStatements/searchAnalyticsOfStatements.html'
	})	
	.state('bank',{
		url : '/bank',
		templateUrl : 'bank/bankPartial.html',
		controller : 'bankController'
	})
	.state('bank.search',{
		url : '/search',
		templateUrl : 'bank/searchBank.html'
	})
	.state('businessActivityCode',{
		url : '/businessActivityCode',
		templateUrl : 'businessActivityCode/businessActivityCodePartial.html',
		controller : 'businessActivityCodeController'
	})
	.state('businessActivityCode.search',{
		url : '/search',
		templateUrl : 'businessActivityCode/searchBusinessActivityCode.html'
	})
	.state('client',{
		url : '/client',
		templateUrl : 'client/clientPartial.html',
		controller : 'clientController'
	})
	.state('client.search',{
		url : '/search',
		templateUrl : 'client/searchClient.html'
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
	.state('currency.search',{
		url : '/search',
		templateUrl : 'currency/searchCurrency.html'
	})	
	.state('currencyRate',{
		url : '/currencyRate',
		templateUrl : 'currencyRate/currencyRatePartial.html',
		controller : 'currencyRateController'
	})
	.state('currencyRate.search',{
		url : '/search',
		templateUrl : 'currencyRate/searchCurrencyRate.html'
	})	
	.state('dailyAccountBalance',{
		url : '/dailyAccountBalance',
		templateUrl : 'dailyAccountBalance/dailyAccountBalancePartial.html',
		controller : 'dailyAccountBalanceController'
	})
	.state('dailyAccountBalance.search',{
		url : '/search',
		templateUrl : 'dailyAccountBalance/searchDailyAccountBalance.html'
	})	
	.state('exchageRateList',{
		url : '/exchageRateList',
		templateUrl : 'exchageRateList/exchageRateListPartial.html',
		controller : 'exchageRateListController'
	})
	.state('exchageRateList.search',{
		url : '/search',
		templateUrl : 'exchageRateList/searchExchangeRateList.html'
	})	
	.state('interbankTransfer',{
		url : '/interbankTransfer',
		templateUrl : 'interbankTransfer/interbankTransferPartial.html',
		controller : 'interbankTransferController'
	})
	.state('interbankTransfer.search',{
		url : '/search',
		templateUrl : 'interbankTransfer/searchInterbankTransfer.html'
	})	
	.state('itemTransfer',{
		url : '/itemTransfer',
		templateUrl : 'itemTransfer/itemTransferPartial.html',
		controller : 'itemTransferController'
	})
	.state('itemTransfer.search',{
		url : '/search',
		templateUrl : 'itemTransfer/searchItemTransfer.html'
	})
	.state('legalEntity',{
		url : '/legalEntity',
		templateUrl : 'legalEntity/legalEntityPartial.html',
		controller : 'legalEntityController'
	})
	.state('legalEntity.search',{
		url : '/search',
		templateUrl : 'legalEntity/searchLegalEntity.html'
	})
	.state('legalEntityAccount',{
		url : '/legalEntityAccount',
		templateUrl : 'legalEntityAccount/legalEntityAccountPartial.html',
		controller : 'legalEntityAccountController'
	})
	.state('legalEntityAccount.search',{
		url : '/search',
		templateUrl : 'legalEntityAccount/searchLegalEntityAccount.html'
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
	.state('paymentType.search',{
		url : '/search',
		templateUrl : 'paymentType/searchPaymentType.html'
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
	.state('suspension.search',{
		url : '/search',
		templateUrl : 'suspension/searchSuspension.html'
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
	
	
	
	
	
	
	.state('businessActivityCode.add', {
		url : '/addBusinessActivityCode',
		templateUrl : 'businessActivityCode/addBusinessActivityCode.html'
	})
	
	.state('client.add', {
		url : '/addClient',
		templateUrl : 'client/addClient.html'
	})
	
	.state('legalEntity.add', {
		url : '/addLegalEntity',
		templateUrl : 'legalEntity/addLegalEntity.html'
	})
	
	.state('legalEntityAccount.add', {
		url : '/addLegalEntityAccount',
		templateUrl : 'legalEntityAccount/addLegalEntityAccount.html'
	})
	
	
	
});
