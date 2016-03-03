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
				scope.remove = function(rqm) {
					var httpParam = {
						params:{
							key: rqm.values.vkey ,
							requirementId: rqm.values.requirementId
						}
					};
					console.log(httpParam);
					$http.delete('http://127.0.0.1:8080/mview/changes',httpParam)
						.success(function (data, status, headers){
							if(data.code == '200'){
								rqm.status = 'removed' ;
								$state.reload();
								notification.log('Remove SUCCESS' , { addnCls: 'humane-flatty-success' });
							}
						}).error(function (data, status, headers){
							notification.log('A problem occurred, please try again', { addnCls: 'humane-flatty-error' });
							console.log(data);
						});	
				},
				scope.add = function(rqm) {
					var httpParam = {
						params:{
							key: rqm.values.vkey ,
							requirementId: rqm.values.requirementId
						}
					};
					console.log(httpParam);
					$http.post('http://127.0.0.1:8080/mview/changes',httpParam)
						.success(function (data, status, headers){
							if(data.code == '200'){
								alert('SUCCESS');
								rqm.status = 'added' ;
								$state.reload();
								notification.log('Remove SUCCESS' , { addnCls: 'humane-flatty-success' });
							}
						}).error(function (data, status, headers){
							notification.log('A problem occurred, please try again', { addnCls: 'humane-flatty-error' });
							console.log(data);
						});	
				}
			},
			template:'<a ng-if="rqm.status != \'removed\'" class="btn btn-danger btn-xs" ng-click="remove(rqm)"><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>&nbsp;Remove</a><a ng-if="rqm.status == \'removed\'" class="btn btn-success btn-xs" ng-click="add(rqm)"><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>&nbsp;Resume</a>'

		}
	};
});


