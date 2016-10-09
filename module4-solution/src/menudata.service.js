(function () {
'use strict';

angular.module('Data')
.service('MenuDataService', MenuDataService)
.constant('ApiBaseUrl', 'https://davids-restaurant.herokuapp.com')
;

MenuDataService.$inject = ['$http', 'ApiBaseUrl'];
function MenuDataService($http, ApiBaseUrl) {
    var service = this;

    service.getAllCategories  = function() {
        return $http({
            method: 'GET',
            url: (ApiBaseUrl + '/categories.json')
        })
        .then(function(response) {
            return response.data;
        })
        .catch(function(error) {
            console.error('Service error: ', error);
        });
    };

    service.getItemsForCategory = function(categoryShortName) {
        return $http({
            method: 'GET',
            url: (ApiBaseUrl + '/menu_items.json'),
            params: {category: categoryShortName}
        })
        .then(function(response) {
            return response.data.menu_items;
        })
        .catch(function(error) {
            console.error('Service error: ', error);
        });
    };

}

})();