menuMovieTypeApp.service('menuMovieTypeService', ['$http', '$q', 'baseService',
        function($http, $q, baseService) {
            return {
                deleteMovieType: function(json) {
            	  var url = _ctx + '/video/menu-movie-type/delete';
            	  return baseService.post(url, json);
                },
                insertMovieType: function (json) {
                    var url = _ctx + '/video/menu-movie-type/insert';
                    return baseService.post(url, json);
                }
            }
        }
]);
