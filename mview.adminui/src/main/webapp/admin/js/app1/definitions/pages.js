define(function(require){
	'use strict';
	return function (NgAdminConfigurationProvider,Application) {
		var nga = NgAdminConfigurationProvider ;
        var admin = Application ;
        var page = nga.entity('pages').identifier(nga.field('skey'));
        page.listView()
	        .fields([
	            nga.field('skey').map(function truncate(key, page) {
	            	return page["key.type"] + "/" + page["key.name"]+"/"+page["key.version"] ;
	            }).isDetailLink(true),
	            
	            nga.field('key.type').label('Type'),
	            nga.field('key.name').label('Name'),
	            nga.field('key.version').label('Version'),
	            nga.field('cssDefinition')
	            	.label('cssDefinition')
	            	.map(function truncate(key, page) {
	            		if(typeof(page["cssDefinition.type"]) == "undefined"){
	            			return '' ;
	            		}
	            		return page["cssDefinition.type"] + "/" + page["cssDefinition.name"]+"/"+page["cssDefinition.version"] ;
		            }),
            	nga.field('jsDefinition')
            		.label('jsDefinition')
	            	.map(function truncate(key, page) {
	            		if(typeof(page["jsDefinition.type"]) == "undefined"){
	            			return '' ;
	            		}
		            	return page["jsDefinition.type"] + "/" + page["jsDefinition.name"]+"/"+page["jsDefinition.version"] ;
		            }),
            	nga.field('layoutDefinition')
            		.label('layoutDefinition')
	            	.map(function truncate(key, page) {
	            		if(typeof(page["layoutDefinition.type"]) == "undefined"){
	            			return '' ;
	            		}
	            		return page["layoutDefinition.type"] + "/" + page["layoutDefinition.name"]+"/"+page["layoutDefinition.version"] ;
		            }),
	            nga.field('operator')
	        ])
        	.listActions(['show','edit', 'delete']);
        ;
        page.showView().fields([
            nga.field('skey').map(function truncate(value, page) {
            	return page["key.type"] + "/" + page["key.name"]+"/"+page["key.version"] ;
            }),
            nga.field('key.type').label('Type'),
            nga.field('key.name').label('Name'),
            nga.field('key.version').label('version'),
//            nga.field('key.version','reference').label('operator')
//            	.targetEntity(admin.getEntity('users'))
//	        	.targetField(nga.field('userName'))
//            ,
            nga.field('cssDefinition').label('cssDefinition')
            	.template(function(key,v){
            		if(typeof( key.values["cssDefinition.type"]) == "undefined"){
            			return '' ;
            		}
            		var skeyvalue = key.values["cssDefinition.type"] + '/' + key.values["cssDefinition.name"]+'/'+key.values["cssDefinition.version"] ;
            		var svalue = "'" + skeyvalue + "'" ;
            		return '<a ui-sref="edit({entity: \'templates\', id: '+svalue +' })"> '+skeyvalue +'</a>' ;
            	}),
        	nga.field('jsDefinition').label('jsDefinition')
            	.template(function(key,v){
            		if(typeof( key.values["jsDefinition.type"]) == "undefined"){
            			return '' ;
            		}
            		var skeyvalue = key.values["jsDefinition.type"] + "/" + key.values["jsDefinition.name"]+"/"+key.values["jsDefinition.version"] ;
            		var svalue = "'" + skeyvalue + "'" ;
            		return '<a ui-sref="edit({entity: \'templates\', id: '+svalue +' })"> '+skeyvalue +'</a>' ;
            	}),
        	nga.field('layoutDefinition').label('layoutDefinition')
            	.template(function(key,v){
            		if(typeof( key.values["layoutDefinition.type"]) == "undefined"){
            			return '' ;
            		}
            		var skeyvalue = key.values["layoutDefinition.type"] + "/" + key.values["layoutDefinition.name"]+"/"+key.values["layoutDefinition.version"] ;
            		var svalue = "'" + skeyvalue + "'" ;
            		return '<a ui-sref="edit({entity: \'templates\', id: '+svalue +' })"> '+skeyvalue +'</a>' ;
            	}),
        	nga.field('dsDefKeys').label('dataReadServicessssss'),
        	nga.field('widgetDefKeys').label('widgetDefKeys'),
            nga.field('applyVersion'),
            nga.field('operator'),
            nga.field('ctime'),
            nga.field('mtime'),
            nga.field('content'),
            nga.field('description')
        ]);
        page.creationView().fields([
            nga.field('skey').map(function truncate(value, page) {
            	return page["key.type"] + "/" + page["key.name"]+"/"+page["key.version"] ;
            }).cssClasses('hidden-xs').editable(false),
            nga.field('key.type').label('Type').defaultValue('page').editable(false),
            nga.field('key.name').label('Name'),
            
            nga.field('cssDefKey','reference').label('cssDefinition')
	        	.targetEntity(admin.getEntity('templates/type/css'))
	        	.targetField(nga.field('vkey'))
			    .validation({required:true}),
	    	nga.field('jsDefKey','reference').label('jsDefinition')
	        	.targetEntity(admin.getEntity('templates/type/js'))
	        	.targetField(nga.field('vkey')),
	    	nga.field('layoutDefKey','reference').label('dataTemplateDefinition')
	        	.targetEntity(admin.getEntity('templates/type/layout'))
	        	.targetField(nga.field('vkey')),
        	nga.field('dsDefKeys','reference_many').label('dataReadServices')
	        	.targetEntity(admin.getEntity('templates/type/dataReadService'))
	        	.targetField(nga.field('vkey')),
	    	nga.field('widgetDefKeys','reference_many').label('widgetDefKeys')
	        	.targetEntity(admin.getEntity('widgets/type'))
	        	.targetField(nga.field('vkey')),
            
            nga.field('operator','string').defaultValue('kane')
     	]);
        page.editionView().fields([
            nga.field('skey').map(function truncate(value, page) {
            	return page["key.type"] + "/" + page["key.name"]+"/"+page["key.version"] ;
            }).editable(false),
            nga.field('key.type').label('Type').editable(false),
            nga.field('key.name').label('Name').editable(false),
            nga.field('key.version').label('Version').editable(false),

			nga.field('cssDefKey','reference').label('cssDefinition')
	        	.targetEntity(admin.getEntity('templates/type/css'))
	        	.targetField(nga.field('vkey'))
			    .validation({required:true}),
	    	nga.field('jsDefKey','reference').label('jsDefinition')
	        	.targetEntity(admin.getEntity('templates/type/js'))
	        	.targetField(nga.field('vkey')),
	        nga.field('layoutDefKey','reference').label('dataTemplateDefinition')
	        	.targetEntity(admin.getEntity('templates/type/layout'))
	        	.targetField(nga.field('vkey')),
        	nga.field('dsDefKeys','reference_many').label('dataReadServices')
	        	.targetEntity(admin.getEntity('templates/type/dataReadService'))
	        	.targetField(nga.field('vkey')),
	    	nga.field('widgetDefKeys','reference_many').label('viewTemplateDefinition')
	        	.targetEntity(admin.getEntity('widgets/type'))
	        	.targetField(nga.field('vkey')),
			
			nga.field('operator','string').defaultValue('kane'),
            nga.field('description','text')
        ]);
        admin.addEntity(page);      
	};
});