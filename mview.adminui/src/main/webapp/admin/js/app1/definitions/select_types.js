define(function(require){
	'use strict';
	return function (NgAdminConfigurationProvider,Application) {
		var nga = NgAdminConfigurationProvider ;
        var admin = Application ;
        var template_css = nga.entity('templates/type/css').identifier(nga.field('vkey'));
        admin.addEntity(template_css);
        var template_js = nga.entity('templates/type/js').identifier(nga.field('vkey'));
        admin.addEntity(template_js);
        var template_layout = nga.entity('templates/type/layout').identifier(nga.field('vkey'));
        admin.addEntity(template_layout);
        var template_dataTemplate = nga.entity('templates/type/dataTemplate').identifier(nga.field('vkey'));
        admin.addEntity(template_dataTemplate);
        var template_viewTemplate = nga.entity('templates/type/viewTemplate').identifier(nga.field('vkey'));
        admin.addEntity(template_viewTemplate);
        var template_dataReadService = nga.entity('templates/type/dataReadService').identifier(nga.field('vkey'));
        admin.addEntity(template_dataReadService);
        
        var type_widget = nga.entity('widgets/type').identifier(nga.field('vkey'));
        admin.addEntity(type_widget);
	};
});