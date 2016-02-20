define(function(require){
	'use strict';
	return function (NgAdminConfigurationProvider,Application) {
		var nga = NgAdminConfigurationProvider ;
		
        var admin = Application ;
        var requirements = nga.entity('requirements');
        requirements.listView()
	        .fields([
	            nga.field('id').isDetailLink(true),
	            nga.field('name'),
	            nga.field('status'),
	            nga.field('operator'),
	            nga.field('description')
	        ])
        	.listActions(['show','edit', 'delete','<approve-btn rqm="::entry"></approve-btn>']);
        ;
        requirements.showView().fields([
            nga.field('id'),
            nga.field('name','string'),
            nga.field('status','string'),
            nga.field('operator','string'),
            nga.field('mtime','string'),
            nga.field('description','text'),
        ]);
        requirements.creationView().fields([
            nga.field('name','string'),
            nga.field('description','text')
     	]);
        requirements.editionView().fields(requirements.creationView().fields());
        admin.addEntity(requirements);      
	};
});