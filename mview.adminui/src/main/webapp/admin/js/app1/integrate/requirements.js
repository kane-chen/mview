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
	            nga.field('status').label('Status'),
	            nga.field('operator'),
	            nga.field('description')
	        ])
        	.listActions(['show','edit', 'delete','<join-btn rqm="::entry"></join-btn>']);
        ;
        requirements.showView().fields([
            nga.field('name','string'),
            nga.field('status','string').label('Status')
            	 .template(function(key,v){
            		return '<integrate-btn rqm="::entry"></integrate-btn>' ;
            	 }),
            nga.field('operator','string'),
            nga.field('mtime','string'),
            nga.field('description','text'),
            nga.field('Changes','referenced_list')
            	.targetEntity(nga.entity('changes').identifier(nga.field('pathkey'))) 
                .targetReferenceField('requirement_id')
                .targetFields([
                    nga.field('pathkey').label('List')
	                    .template(function(key,v){
	                    	var changeType = key.values["key.type"] ;
	                    	var entityName = 'templates';
	                    	if(changeType == 'widget'){
	                    		entityName = 'widgets';
	                    	}else if(changeType == 'page'){
	                    		entityName = 'pages';
	                    	}
	                    	var pathkey = key._identifierValue
		            		return '<a ui-sref="edit({entity: \''+ entityName +'\', id: \''+pathkey +'\' })"> '+pathkey +'</a>' ;
		            	}),
                    nga.field('vkey').label('Operate')
	                    .template(function(key,v){
		            		return '<remove-btn rqm="::entry"></remove-btn>' ;
		            	}),
                ])
        ]);
        requirements.creationView().fields([
            nga.field('name','string'),
            nga.field('description','text')
     	]);
        requirements.editionView().fields(requirements.creationView().fields());
        admin.addEntity(requirements);      
	};
});