(function () {
'use strict';

angular.module('Data')
.controller('MainCategoriesListController', MainCategoriesListController);


MainCategoriesListController.$inject = ['items'];
function MainCategoriesListController(items) {
    var mainlist = this;
    mainlist.items = items;
}

})();
