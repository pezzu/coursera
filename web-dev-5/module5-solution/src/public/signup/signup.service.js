(function () {

"use strict";  

angular.module('public')
.service('SignUpService', SignUpService);
 
function SignUpService() {
   var service = this;
   
   service.save = function (user) {
      service.user = user;
   }

   service.load = function () {
      return service.user;
   }   
}
   
})();