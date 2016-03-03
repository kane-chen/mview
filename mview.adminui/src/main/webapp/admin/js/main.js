
require.config({
    baseUrl: './js',
	paths: {
		'ng-admin': '../lib/ng-admin.min',
		'text':'../lib/requiretxt'
	},
	// Shim of the libraries
	shim: {
    }
})

define(function (require) {
    'use strict';

    // exports: angular, ng-admin, restangular, ui.router
    require('ng-admin'); 
    //app-module
	var ExampleModule = angular.module('myApp', ['ng-admin']);
	//directive
	ExampleModule.directive('approveBtn', require('app1/integrate/directive_approve_btn'));
	ExampleModule.directive('removeBtn', require('app1/integrate/directive_remove_btn'));
	ExampleModule.directive('integrateBtn', require('app1/integrate/directive_integrate_btn'));
	// Config
	ExampleModule.config(require('app1/app'));

	return ExampleModule;

});

