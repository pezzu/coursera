(function () {

angular.module('public')
.controller('SignUpController', SignUpController);

SignUpController.$inject = ['MenuService', 'SignUpService'];   
function SignUpController(MenuService, SignUpService) {
   var $ctrl = this;
   
   $ctrl.submit = function () {
      MenuService.getDishById($ctrl.dishShortName.toUpperCase())
         .then(function (dish) {
            $ctrl.dishName = dish.name;
            $ctrl.dishNotFound = false;

            var user = {
               first: $ctrl.firstName,
               last: $ctrl.lastName,
               email: $ctrl.email,
               phone: $ctrl.phone,
               dish: $ctrl.dishName
            };            
            SignUpService.save(user);

            $ctrl.saved = true;
         })
         .catch(function (error) {
            $ctrl.dishNotFound = true;
            $ctrl.saved = false;
         });
   }
}

})();