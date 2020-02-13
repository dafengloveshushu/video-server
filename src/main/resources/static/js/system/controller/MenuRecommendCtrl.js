var menuRecommendApp = angular.module('menuRecommendApp', ['base']);
menuRecommendApp.controller('menuRecommendCtrl', ['$rootScope', '$scope','menuRecommendService',function ($rootScope,$scope, menuRecommendService) {
	$scope.queryFilter = {};
	//添加推荐电影,1为添加，0为修改
    $scope.insertMenu = function (sign) {
        var selectArray = $("#User_list tbody input:checked");
        if(!selectArray || (selectArray.length != 1 && sign == 0)){
            alertDialog("请选择一个");
            return;
        }
        var titleName = selectArray && selectArray.length > 0 ? '修改推荐电影':'添加推荐电影';
        // var movieId = $("#movieName").val();
        var movieId = $(selectArray[0]).val();
        if(movieId && sign == 0){
            menuRecommendService.detail(movieId).then(function(response){
                console.info(response.data);
                $scope.movie = response.data;
            });
        }
        layer.open({
            type : 1,
            title : titleName,
            maxmin : true,
            shadeClose : true, //点击遮罩关闭层
            area : [ '576px', '468px' ],
            content : $('#Add_user_style'),
            btn : [ '保存', '取消' ],
            yes : function(index, layero) {
                if (movieId == "") {
                    alertDialog("电影名不能为空");
                    return;
                } else {
                    var movie = angular.copy($scope.movie);
                    if(!movie){
                        layer.msg('电影名或标题不能为空！', {
                            time : 1000,
                            icon : 1
                        });
                        return;
                    }
                    if(!movieId){
                        menuRecommendService.insertMenu(movie).then(function(response){
                            layer.alert(response.msg, {
                                title : '提示框',
                                icon : 1
                            },function(){
                                layer.close(index);
                                window.location.reload();
                            });
                        });
                    } else {
                        menuRecommendService.insertMenu(movie).then(function(response){
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
    //删除已有的推荐列表
    $scope.deleteRecommend = function () {
        var selectArray = $("#User_list tbody input:checked");
        if (!selectArray || selectArray.length == 0) {
            alertDialog("请选择标题");
            return;
        }
        var movieIds = new Array();
        $.each(selectArray, function (i, e) {
            var val = $(this).val();
            movieIds.push(val);
        });
        if(movieIds.length == 0) {
            return;
        }
        layer.confirm('本次操作将会清除所有当前推荐列表！', {
            btn : ['确定', '取消']
        }, function () {
            menuRecommendService.deleteRecommend(movieIds).then(function (resp) {
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