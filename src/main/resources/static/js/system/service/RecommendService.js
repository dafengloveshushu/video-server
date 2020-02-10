menuRecommendApp.service('menuRecommendService', ['$http', '$q', 'baseService',
        function($http, $q, baseService) {
            return {
                deleteRecommend: function(json) {
            	  var url = _ctx + '/video/menu-recommend/delete';
            	  return baseService.post(url, json);
                },
                insertMenu: function (json) {
                    var url = _ctx + '/video/menu-recommend/insert';
                    return baseService.post(url, json);
                }
            }
        }
]);
