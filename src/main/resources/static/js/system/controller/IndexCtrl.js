var indexApp = angular.module('indexApp', ['base']);
indexApp.controller('indexCtrl', ['$rootScope', '$scope','indexService',function ($rootScope,$scope,indexService) {
	//退出登录
	$scope.logout = function(){
	   layer.confirm('是否确定退出系统？', {
		   btn: ['是','否']
		}, function(){
		  location.href= _ctx + "/video/gm-admin/logout";
		 });
	};
 }]);