(function () {

angular.module('public')
.controller('SignUpController', SignUpController);

SignUpController.$inject = ['MenuService', 'SignUpService'];   
function SignUpController(MenuService, SignUpService) {
   var $ctrl = this;
   
   $ctrl.submit = function () {
      MenuService.getMenuItem($ctrl.dishShortName.toUpperCase())
         .then(function (menuItem) {
            $ctrl.menuItem = menuItem;

            var user = {
               first: $ctrl.firstName,
               last: $ctrl.lastName,
               email: $ctrl.email,
               phone: $ctrl.phone,
               menuItem: $ctrl.menuItem
            };            
            SignUpService.save(user);

            $ctrl.saved = true;
         })
         .catch(function (error) {
            $ctrl.saved = false;
         });
   }
}

})();