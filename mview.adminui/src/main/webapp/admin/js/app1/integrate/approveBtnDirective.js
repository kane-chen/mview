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
				scope.approve = function(rqm) {
					var httpParam = {
							params:{
								reqId:rqm.values.id
							}
					};
					console.log(httpParam);
					$http.get('http://127.0.0.1:8080/mview/requirement/members/join',httpParam)
						.success(function (data, status, headers){
							if(data.code == 'OK'){
								alert('JOIN SUCCESS');
							}
						}).error(function (data, status, headers){
							console.log(data);
						});	
				}
			},
			template:'<a class="btn btn-xs btn-success" ng-click="approve(rqm)"><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>&nbsp;Join</a>'

		}
	};
});


