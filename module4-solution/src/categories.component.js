(function () {
'use strict';

angular.module('Data')
.component('categoriesList', {
  templateUrl: 'views/categories-list.template.html',
  bindings: {
    items: '<'
  }
});

})();