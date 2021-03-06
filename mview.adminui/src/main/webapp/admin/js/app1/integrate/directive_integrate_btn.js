define(function(require){
	'use strict';
	return function ($http,$state,notification) {
		return {
			restrict: 'EA',
			scope: {
				rqm: '&'
			},
			link: function(scope, element, attrs) {
				scope.rqm = scope.rqm();
				scope.action = function(rqm) {
					var httpParam = {
						params:{
							id: rqm.values.id
						}
					};
					$http.post('http://127.0.0.1:8080/mview/requirements/forward',{},httpParam)
						.success(function (data, status, headers){
							if(data.code == '200'){
								$state.reload();
								notification.log('Action SUCCESS' , { addnCls: 'humane-flatty-success' });
							}
						}).error(function (data, status, headers){
							notification.log('A problem occurred, please try again', { addnCls: 'humane-flatty-error' });
						});	
				},
				scope.backward = function(rqm) {
					var httpParam = {
						params:{
							id: rqm.values.id
						}
					};
					console.log(httpParam);
					$http.post('http://127.0.0.1:8080/mview/requirements/backward',httpParam)
						.success(function (data, status, headers){
							if(data.code == '200'){
								$state.reload();
								notification.log('Backward SUCCESS' , { addnCls: 'humane-flatty-success' });
							}
						}).error(function (data, status, headers){
							notification.log('A problem occurred, please try again', { addnCls: 'humane-flatty-error' });
						});	
				},
				scope.disable = function(rqm) {
					var httpParam = {
						params:{
							id: rqm.values.id
						}
					};
					console.log(httpParam);
					$http.post('http://127.0.0.1:8080/mview/requirements/disable',httpParam)
						.success(function (data, status, headers){
							if(data.code == '200'){
								$state.reload();
								notification.log('Disable SUCCESS' , { addnCls: 'humane-flatty-success' });
							}
						}).error(function (data, status, headers){
							notification.log('A problem occurred, please try again', { addnCls: 'humane-flatty-error' });
						});	
				}
			},
			template:'<span class="glyphicon" aria-hidden="true"></span>&nbsp;{{rqm.values.stateName}}&nbsp;'
				+'<a ng-if="rqm.values.stateForwardName != null" class="btn btn-success btn-xs" ng-click="action(rqm)"><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>&nbsp;{{rqm.values.stateForwardName}}</a>'
				+'<a ng-if="rqm.values.stateBackwardName != null" class="btn btn-danger btn-xs" ng-click="backward(rqm)"><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>&nbsp;{{rqm.values.stateBackwardName}}</a>'
				+'<a ng-if="rqm.values.stateDisableName != null" class="btn btn-default btn-xs" ng-click="disable(rqm)"><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>&nbsp;{{rqm.values.stateDisableName}}</a>'

		}
	};
});


