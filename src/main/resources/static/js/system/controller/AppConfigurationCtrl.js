var configurationApp = angular.module('configurationApp', ['base']);
configurationApp.controller('configurationCtrl', ['$rootScope', '$scope','configurationService',function ($rootScope,$scope, configurationService) {
	$scope.queryFilter = {};
	//添加推荐电影,1为添加，0为修改
    $scope.insertAppConfig = function (sign) {
        var selectArray = $("#webLog_list tbody input:checked");
        if(!selectArray || (selectArray.length != 1 && sign == 0)){
            alertDialog("请选择一个");
            return;
        }
        var titleName = selectArray && selectArray.length > 0 ? '修改配置':'添加配置';
        // var movieId = $("#movieName").val();
        var id = $(selectArray[0]).val();
        if(id && sign == 0){
            configurationService.detail(id).then(function(response){
                $scope.app = response.data;
            });
        }
        layer.open({
            type : 1,
            title : titleName,
            maxmin : true,
            shadeClose : true, //点击遮罩关闭层
            area : [ '576px', '468px' ],
            content : $('#App_user_style'),
            btn : [ '保存', '取消' ],
            yes : function(index, layero) {
                if (movieId == "") {
                    alertDialog("配置名不能为空");
                    return;
                } else {
                    var app = angular.copy($scope.app);
                    if(!app){
                        layer.msg('配置信息不能为空！', {
                            time : 1000,
                            icon : 1
                        });
                        return;
                    }
                    if(!movieId){
                        configurationService.insertAppConfig(app).then(function(response){
                            layer.alert(response.msg, {
                                title : '提示框',
                                icon : 1
                            },function(){
                                layer.close(index);
                                window.location.reload();
                            });
                        });
                    } else {
                        configurationService.insertAppConfig(app).then(function(response){
                            layer.alert(response.msg, {
                                title : '提示框',
                                icon : 1
                            },function(){
                                layer.close(index);
                                window.location.reload();
                            });
                        });
                    }
                }
            }
        });
    };
    //删除已有的配置
    $scope.deleteAppConfig = function () {
        var selectArray = $("#webLog_list tbody input:checked");
        if (!selectArray || selectArray.length == 0) {
            alertDialog("请选择标题");
            return;
        }
        var appIds = new Array();
        $.each(selectArray, function (i, e) {
            var val = $(this).val();
            appIds.push(val);
        });
        if(appIds.length == 0) {
            return;
        }
        layer.confirm('本次操作将会清除所有当前推荐列表！', {
            btn : ['确定', '取消']
        }, function () {
            configurationService.deleteAppConfig(appIds).then(function (resp) {
                layer.msg(resp.msg, {
                    time : 1000,
                    icon : 1
                }, function () {
                    window.location.reload();
                });
            })
        });
    }
 }]);