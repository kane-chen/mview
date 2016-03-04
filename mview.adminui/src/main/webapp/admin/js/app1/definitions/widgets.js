define(function(require){
	'use strict';
	return function (NgAdminConfigurationProvider,Application) {
		var nga = NgAdminConfigurationProvider ;
        var admin = Application ;
        var widget = nga.entity('widgets').identifier(nga.field('skey'));
        widget.listView()
	        .fields([
	            nga.field('skey').map(function truncate(key, widget) {
	            	return widget["key.type"] + "/" + widget["key.name"]+"/"+widget["key.version"] ;
	            }).isDetailLink(true),
	            
	            nga.field('key.type').label('Type'),
	            nga.field('key.name').label('Name'),
	            nga.field('key.version').label('Version'),
	            nga.field('cssDefinition')
	            	.label('cssDefinition')
	            	.map(function truncate(key, widget) {
	            		if(typeof(widget["cssDefinition.type"]) == "undefined"){
	            			return '' ;
	            		}
	            		return widget["cssDefinition.type"] + "/" + widget["cssDefinition.name"]+"/"+widget["cssDefinition.version"] ;
		            }),
            	nga.field('jsDefinition')
            		.label('jsDefinition')
	            	.map(function truncate(key, widget) {
	            		if(typeof(widget["jsDefinition.type"]) == "undefined"){
	            			return '' ;
	            		}
		            	return widget["jsDefinition.type"] + "/" + widget["jsDefinition.name"]+"/"+widget["jsDefinition.version"] ;
		            }),
            	nga.field('dataTemplateDefinition')
            		.label('dataTemplateDefinition')
	            	.map(function truncate(key, widget) {
	            		if(typeof(widget["dataTemplateDefinition.type"]) == "undefined"){
	            			return '' ;
	            		}
	            		return widget["dataTemplateDefinition.type"] + "/" + widget["dataTemplateDefinition.name"]+"/"+widget["dataTemplateDefinition.version"] ;
		            }),
            	nga.field('viewTemplateDefinition')
            		.label('viewTemplateDefinition')
	            	.map(function truncate(key, widget) {
	            		if(typeof(widget["viewTemplateDefinition.type"]) == "undefined"){
	            			return '' ;
	            		}
		            	return widget["viewTemplateDefinition.type"] + "/" + widget["viewTemplateDefinition.name"]+"/"+widget["viewTemplateDefinition.version"] ;
		            }),
	            nga.field('operator')
	        ])
        	.listActions(['show','edit', 'delete']);
        ;
        widget.showView().fields([
            nga.field('skey').map(function truncate(value, widget) {
            	return widget["key.type"] + "/" + widget["key.name"]+"/"+widget["key.version"] ;
            }),
            nga.field('key.type').label('Type'),
            nga.field('key.name').label('Name'),
            nga.field('key.version').label('Version'),
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
        	nga.field('dataTemplateDefinition').label('dataTemplateDefinition')
            	.template(function(key,v){
            		if(typeof( key.values["dataTemplateDefinition.type"]) == "undefined"){
            			return '' ;
            		}
            		var skeyvalue = key.values["dataTemplateDefinition.type"] + "/" + key.values["dataTemplateDefinition.name"]+"/"+key.values["dataTemplateDefinition.version"] ;
            		var svalue = "'" + skeyvalue + "'" ;
            		return '<a ui-sref="edit({entity: \'templates\', id: '+svalue +' })"> '+skeyvalue +'</a>' ;
            	}),
        	nga.field('viewTemplateDefinition').label('viewTemplateDefinition')
            	.template(function(key,v){
            		if(typeof( key.values["viewTemplateDefinition.type"]) == "undefined"){
            			return '' ;
            		}
            		var skeyvalue = key.values["viewTemplateDefinition.type"] + "/" + key.values["viewTemplateDefinition.name"]+"/"+key.values["viewTemplateDefinition.version"] ;
            		var svalue = "'" + skeyvalue + "'" ;
            		return '<a ui-sref="edit({entity: \'templates\', id: '+svalue +' })"> '+skeyvalue +'</a>' ;
            	}),
        	nga.field('dsDefKeys').label('dataReadServicessssss'),
            nga.field('applyVersion'),
            nga.field('operator'),
            nga.field('ctime'),
            nga.field('mtime'),
            nga.field('content'),
            nga.field('description')
        ]);
        widget.creationView().fields([
            nga.field('skey').map(function truncate(value, widget) {
            	return widget["key.type"] + "/" + widget["key.name"]+"/"+widget["key.version"] ;
            }).cssClasses('hidden-xs').editable(false),
            nga.field('key.type').label('Type').defaultValue('widget').editable(false),
            nga.field('key.name').label('Name'),
            nga.field('key.version').label('Version'),
            
//            	.template('<ma-field field="::field" value="entry.values[field.name()]" entry="entry" entity="templates" form="formController.form" datastore="::formController.dataStore"></ma-field>', true),
            nga.field('cssDefKey','reference').label('cssDefinition')
	        	.targetEntity(admin.getEntity('templates/type/css'))
	        	.targetField(nga.field('vkey'))
			    .validation({required:true})
//	        	.permanentFilters({keytype:'css'})
		        .perPage(10),
	    	nga.field('jsDefKey','reference').label('jsDefinition')
	        	.targetEntity(admin.getEntity('templates/type/js'))
	        	.targetField(nga.field('vkey'))
		        .perPage(10),
	    	nga.field('dtDefKey','reference').label('dataTemplateDefinition')
	        	.targetEntity(admin.getEntity('templates/type/dataTemplate'))
	        	.targetField(nga.field('vkey')),
	    	nga.field('vtDefKey','reference').label('viewTemplateDefinition')
	        	.targetEntity(admin.getEntity('templates/type/viewTemplate'))
	        	.targetField(nga.field('vkey')),
        	nga.field('dsDefKeys','reference_many').label('dataReadServicessssss')
	        	.targetEntity(admin.getEntity('templates/type/dataReadService'))
	        	.targetField(nga.field('vkey')),
            
            nga.field('operator','string').defaultValue('kane')
     	]);
        widget.editionView().fields([
            nga.field('skey').map(function truncate(value, widget) {
            	return widget["key.type"] + "/" + widget["key.name"]+"/"+widget["key.version"] ;
            }).editable(false),
            nga.field('key.type').label('Type').editable(false),
            nga.field('key.name').label('Name').editable(false),
            nga.field('key.version').label('Version').editable(false),

			nga.field('cssDefKey','reference').label('cssDefinition')
	        	.targetEntity(admin.getEntity('templates/type/css'))
	        	.targetField(nga.field('vkey'))
			    .validation({required:true})
		        .perPage(10),
	    	nga.field('jsDefKey','reference').label('jsDefinition')
	        	.targetEntity(admin.getEntity('templates/type/js'))
	        	.targetField(nga.field('vkey'))
		        .perPage(10),
	    	nga.field('dtDefKey','reference').label('dataTemplateDefinition')
	        	.targetEntity(admin.getEntity('templates/type/dataTemplate'))
	        	.targetField(nga.field('vkey')),
	    	nga.field('vtDefKey','reference').label('viewTemplateDefinition')
	        	.targetEntity(admin.getEntity('templates/type/viewTemplate'))
	        	.targetField(nga.field('vkey')),
        	nga.field('dsDefKeys','reference_many').label('dataReadServicessssss')
	        	.targetEntity(admin.getEntity('templates/type/dataReadService'))
	        	.targetField(nga.field('vkey')),
			
			nga.field('operator','string').defaultValue('kane'),
            nga.field('description','text')
        ]);
        admin.addEntity(widget);      
	};
});