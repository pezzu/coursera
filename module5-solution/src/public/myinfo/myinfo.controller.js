(function () {

angular.module('public')
.controller('MyInfoController', MyInfoController);

MyInfoController.$inject = ['userInfo'];
function MyInfoController(userInfo) {
   var $ctrl = this;

   $ctrl.userInfo = userInfo;
}  

})();