define(function(require){
	'use strict';
	return function (NgAdminConfigurationProvider,Application) {
		var nga = NgAdminConfigurationProvider ;
		
        var admin = Application ;
        var changes = nga.entity('changes').identifier(nga.field('pathkey'));
        changes.listView()
	        .fields([
	            nga.field('vkey'),
	            nga.field('pathkey')
	        ]) ;
        changes.showView().fields([
            nga.field('vkey'),
            nga.field('pathkey')
        ]);
        admin.addEntity(changes);      
	};
});