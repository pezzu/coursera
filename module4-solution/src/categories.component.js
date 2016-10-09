(function () {
'use strict';

angular.module('Data')
.component('CategoriesList',
  templateUrl: 'views/categories-list.template.html',
  bindings: {
    items: '<'
  }
);

})();