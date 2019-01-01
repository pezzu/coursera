(function () {
'use strict';

angular.module('Data')
.component('categoryItems', {
  templateUrl: 'views/category-items.html',
  bindings: {
    items: '<'
  }
});

})();