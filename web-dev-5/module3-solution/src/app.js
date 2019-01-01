(function () {
'use strict';


angular.module('NarrowItDownApp', [])
.controller('NarrowItDownController', NarrowItDownController)
.service('MenuSearchService', MenuSearchService)
.constant('ApiBaseUrl', 'https://davids-restaurant.herokuapp.com')
.directive('foundItems', FoundItemsDirective);


function FoundItemsDirective() {
   return {
      templateUrl: 'views/foundItems.html',
      scope: {
         items: '<',
         onRemove: '&'
      },
      controller: FoundItemsDirectiveController,
      controllerAs: 'list',
      bindToController: true
   };
}


function FoundItemsDirectiveController() {
   var list = this;

   list.itemsExist = function() {
      return list.items.length > 0;
   };
}

NarrowItDownController.$inject = ['MenuSearchService'];
function NarrowItDownController(MenuSearchService) {
   var menuItems = this;

   menuItems.searchTerm = '';
   menuItems.found = [];

   menuItems.search = function() {
      if(menuItems.searchTerm === '')  {
         menuItems.found = [];
      }
      else {
         MenuSearchService.getMatchedMenuItems(menuItems.searchTerm)
            .then(function(result) {
               menuItems.found = result;
            });
      }
   }

   menuItems.removeItem = function(index) {
      menuItems.found.splice(index, 1);
   };
}

MenuSearchService.$inject = ['$http', 'ApiBaseUrl'];
function MenuSearchService($http, ApiBaseUrl) {

   this.getMatchedMenuItems = function(searchTerm) {
      return $http({
         method: 'GET',
         url: (ApiBaseUrl + '/menu_items.json')
      })
      .then(function(response) {
         var allItems = response.data['menu_items'];
         var foundItems = allItems.filter(item =>
            item.description.toLowerCase().search(searchTerm.toLowerCase()) != -1);

         return foundItems;
      })
      .catch(function(error) {
         console.error('Service error: ', error);
      });
   };
}



})();