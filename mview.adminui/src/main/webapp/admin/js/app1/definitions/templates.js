define(function(require){
	'use strict';
	return function (NgAdminConfigurationProvider,Application) {
		var nga = NgAdminConfigurationProvider ;
        var admin = Application ;
        var template = nga.entity('templates').identifier(nga.field('skey'));
        template.listView()
	        .fields([
	            nga.field('skey').map(function truncate(key, template) {
	            	return template["key.type"] + "/" + template["key.name"]+"/"+template["key.version"] ;
	            }).isDetailLink(true),
	            
	            nga.field('key.type').label('Type'),
	            nga.field('key.name').label('Name'),
	            nga.field('key.version').label('Version'),
	            nga.field('content'),
	            nga.field('description'),
	            nga.field('operator'),
	            nga.field('ctime'),
	            nga.field('mtime')
	        ])
	        .filters([
                nga.field('keytype')
            ])
        	.listActions(['show','edit', 'delete']);
        ;
        template.showView().fields([
            nga.field('skey').map(function truncate(value, template) {
            	return template["key.type"] + "/" + template["key.name"]+"/"+template["key.version"] ;
            }),
            nga.field('key.type').label('Type'),
            nga.field('key.name').label('Name'),
            nga.field('key.version').label('Version'),
            nga.field('applyVersion'),
            nga.field('operator'),
            nga.field('ctime'),
            nga.field('mtime'),
            nga.field('content'),
            nga.field('description')
        ]);
        template.creationView().fields([
            nga.field('skey').map(function truncate(value, template) {
            	return template["key.type"] + "/" + template["key.name"]+"/"+template["key.version"] ;
            }).cssClasses('hidden-xs').editable(false),
            nga.field('key.type','choice').label('Type').choices([
              	{ value: 'css', label: 'CSS' },
              	{ value: 'js', label: 'JS' },
              	{ value: 'layout', label: 'layout' },
              	{ value: 'dataTemplate', label: 'dataTemplate' },
              	{ value: 'viewTemplate', label: 'viewTemplate' },
              	{ value: 'dataReadService', label: 'dataReadService' }
            ]),
            nga.field('key.name').label('Name').validation({required: true }),
            nga.field('key.version').label('Version').validation({required: true }),
            nga.field('content','text'),
            nga.field('operator','string').defaultValue('kane')
     	]);
        template.editionView().fields([
            nga.field('skey').map(function truncate(value, template) {
            	return template["key.type"] + "/" + template["key.name"]+"/"+template["key.version"] ;
            }).editable(false),
            nga.field('key.type').label('Type').editable(false),
            nga.field('key.name').label('Name').editable(false),
            nga.field('key.version').label('Version').editable(false),
            nga.field('content','text'),
            nga.field('operator','string').defaultValue('kane')
        ]);
        admin.addEntity(template);      
	};
});