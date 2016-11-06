(function () {

"use strict";

angular.module("public")
.directive("menuItemExists", menuItemExists);

menuItemExists.$inject = ['MenuService']   
function menuItemExists(MenuService) {
   return {
      require: 'ngModel',
      link: function(scope, element, attr, ctrl) {
         ctrl.$asyncValidators.username = function(modelValue, viewValue) {
            return MenuService.getMenuItem(modelValue.toUpperCase());
         };  
      }
   };
}

})();