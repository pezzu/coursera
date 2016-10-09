(function () {
'use strict';

angular.module('Data')
.controller('CategoryItemsController', CategoryItemsController);


CategoryItemsController.$inject = ['items'];
function CategoryItemsController(items) {
    var categoryItems = this;
    categoryItems.items = items;
}

})();
