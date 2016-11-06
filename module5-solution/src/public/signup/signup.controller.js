(function () {

angular.module('public')
.controller('SignUpController', SignUpController);

SignUpController.$inject = ['MenuService'];   
function SignUpController(MenuService) {
   var $ctrl = this;
   
   $ctrl.submit = function () {
      MenuService.getDishById($ctrl.dishShortName)
         .then(function (dish) {
            $ctrl.dishName = dish.name;
            $ctrl.dishNotFound = false;
         })
         .catch(function (error) {
            $ctrl.dishNotFound = true;
         });
   }
}

})();