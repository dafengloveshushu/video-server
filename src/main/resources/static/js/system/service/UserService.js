userApp.service('userService', ['$http', '$q', 'baseService',
        function($http, $q, baseService) {
            return {
            	addUser: function(user) {
                    var url = _ctx + '/user/add';
                    return baseService.post(url,user);
                },
                deleteMovie: function(json) {
                    var url = _ctx + '/video/movie-main/delete';
                    return baseService.post(url,json);
                },
                addVipTag: function(json) {
                    var url = _ctx + '/video/movie-main/addVip';
                    return baseService.post(url,json);
                },
                delVipTag: function(user) {
                    var url = _ctx + '/video/movie-main/delVip';
                    return baseService.post(url,user);
                },
                getMovieMain: function(movieId) {
                    var url = _ctx + '/video/movie-main/detail';
                    return baseService.post(url,movieId);
                },
                getMovieParts: function(movieId) {
            	    var url = _ctx + '/video/movie-parts/detail';
            	    return baseService.post(url,movieId);
                },
                getRoleMap: function(userId) {
                    var url = _ctx + '/role/getRoleMap';
                    return baseService.post(url,userId);
                },
                saveUserRole: function(param) {
                    var url = _ctx + '/userRole/add';
                    return baseService.postForm(url,param);
                }
            }
        }
]);
