define(function(require){
	'use strict';
	return function (NgAdminConfigurationProvider,Application) {
		var nga = NgAdminConfigurationProvider ;
        var admin = Application ;
        var user = nga.entity('users');
        user.listView()
	        .fields([
	            nga.field('id').isDetailLink(true),
	            nga.field('userName'),
	            nga.field('email')
	        ])
        	.listActions(['show','edit', 'delete']);
        ;
        user.showView().fields([
            nga.field('id'),
            nga.field('userName','string'),
            nga.field('email','email')
        ]);
        user.creationView().fields([
            nga.field('userName','string'),
            nga.field('email','email')
     	]);
        user.editionView().fields(user.creationView().fields());
        admin.addEntity(user);      
	};
});