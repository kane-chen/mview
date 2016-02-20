define(function(require){
	'use strict';
	return function (NgAdminConfigurationProvider,Application) {
		var nga = NgAdminConfigurationProvider ;
        var app = Application ;
        app.menu()
	    	.addChild(nga.menu()
	            .title('Users')
	            .icon('<span class="fa fa-users fa-fw"></span>')
	            .active(function(path) {
		            return path.indexOf('/users') === 0;
		        })
	            .addChild(nga.menu()
	                .title('Users')
	                .link('/users/list') // this state isn't handled by ng-admin - no problem
	                .icon('<span class="fa fa-scissors fa-fw"></span>'))
	                .active(function(path) {
			            return path.indexOf('/users') === 0;
	                })
	    	)
	    	.addChild(nga.menu()
	            .title('Integrate')
	            .icon('<span class="fa fa-th-list fa-fw"></span>')
	            .active(function(path) {
		            return path.indexOf('/requirements') === 0;
		        })
	            .addChild(nga.menu()
	                .title('Requirement')
	                .link('/requirements/list') // this state isn't handled by ng-admin - no problem
	                .icon('<span class="fa fa-picture-o fa-fw"></span>'))
	                .active(function(path) {
			            return path.indexOf('/requirements') === 0;
	                })
	    	)
	    	.addChild(nga.menu()
	    		.title('Definitions')
	            .icon('<span class="fa fa-shopping-cart fa-fw"></span>')
	            .active(function(path) {
		            return path.indexOf('/templates') === 0 || path.indexOf('/widgets') === 0 || path.indexOf('/pages') === 0;
		        })
				.addChild(nga.menu()
	                .title('Templates')
	                .link('/templates/list?search={"has_ordered":"false"}') // use the same entity list for several menu items
	                .icon('<span class="fa fa-credit-card fa-fw"></span>')
	                .active(function(path) {
			            return path.indexOf('/templates') === 0;
	                })
                ) 
	            .addChild(nga.menu()
	                .title('Widget')
	                .link('/widgets/list?search={"has_ordered":"true"}') // use the same entity list for several menu items
	                .icon('<span class="fa fa-usd fa-fw"></span>')
	                .active(function(path) {
			            return path.indexOf('/widgets') === 0;
	                })
	            )
	            .addChild(nga.menu()
	                .title('Page')
	                .link('/pages/list?search={"has_ordered":"true"}') // use the same entity list for several menu items
	                .icon('<span class="fa fa-hand-o-left fa-fw"></span>')
	                .active(function(path) {
			            return path.indexOf('/pages') === 0;
	                })
	            )
	    	);
    };
	
});