define(function(require){
	'use strict';
	return function ($http) {
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
								key:rqm
							}
					};
					console.log(httpParam);
					$http.delete('http://127.0.0.1:8080/mview/changes',httpParam)
						.success(function (data, status, headers){
							if(data.code == 'OK'){
								alert('JOIN SUCCESS');
							}
						}).error(function (data, status, headers){
							console.log(data);
						});	
				}
			},
			template:'<a class="btn btn-danger btn-xs" ng-click="remove(rqm)"><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>&nbsp;Remove</a>'

		}
	};
});


