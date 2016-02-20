define(function(require){
	'use strict';
	function ngAdmin(NgAdminConfigurationProvider) {
        var nga = NgAdminConfigurationProvider;
	    var admin = nga.application('My First Admin').baseApiUrl('http://127.0.0.1:8080/mview/');

//	    admin.dashboard(require('./dashboard/config')(nga, admin));
	    admin.header(require('text!../../view/header.html'));
	    //menu
        require('app1/menu')(nga,admin);
        //app-views
        require('app1/users')(nga,admin);
        require('app1/integrate/requirements')(nga,admin);
        require('app1/definitions/templates')(nga,admin);
        require('app1/definitions/select_types')(nga,admin);
        require('app1/definitions/widgets')(nga,admin);
        require('app1/definitions/pages')(nga,admin);
	    nga.configure(admin);
    }
    ngAdmin.$inject = ['NgAdminConfigurationProvider'];
	return ngAdmin;
});