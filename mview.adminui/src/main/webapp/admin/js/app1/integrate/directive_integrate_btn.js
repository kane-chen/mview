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
							id: rqm.values.requirementId
						}
					};
					console.log(httpParam);
					$http.put('http://127.0.0.1:8080/mview/requirements/forward',httpParam)
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
							id: rqm.values.requirementId
						}
					};
					console.log(httpParam);
					$http.put('http://127.0.0.1:8080/mview/requirements/backward',httpParam)
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
							id: rqm.values.requirementId
						}
					};
					console.log(httpParam);
					$http.put('http://127.0.0.1:8080/mview/requirements/disable',httpParam)
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
			template:'<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>&nbsp;1111'
				+'<a ng-if="rqm.stateForwardName != null" class="btn btn-danger btn-xs" ng-click="action(rqm)"><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>&nbsp;'+222+'</a>'
				+'<a ng-if="rqm.stateBackwardName != null" class="btn btn-danger btn-xs" ng-click="backward(rqm)"><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>&nbsp;'+333+'</a>'
				+'<a ng-if="rqm.stateDisableName != null" class="btn btn-danger btn-xs" ng-click="disable(rqm)"><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>&nbsp;'+444+'</a>'

		}
	};
});


