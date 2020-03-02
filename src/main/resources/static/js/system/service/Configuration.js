configurationApp.service('configurationService', ['$http', '$q', 'baseService',
        function($http, $q, baseService) {
            return {
                deleteAppConfig: function(json) {
            	  var url = _ctx + '/video/app-configuration/delete';
            	  return baseService.post(url, json);
                },
                insertAppConfig: function (json) {
                    var url = _ctx + '/video/app-configuration/insert';
                    return baseService.post(url, json);
                },
                detail: function (json) {
                    var url = _ctx + '/video/app-configuration/detail';
                    return baseService.post(url, json);
                }
            }
        }
]);
