(function () {
'use strict';

const ShoppingListItems = [
   { name: "cookies", quantity: 10 },
   { name: "cookies", quantity: 10 },
   { name: "cookies", quantity: 10 },
   { name: "cookies", quantity: 10 },
   { name: "cookies", quantity: 10 },
   { name: "cookies", quantity: 10 }
];

angular.module('ShoppingList', [])
.controller('ToBuyShoppingController', ToBuyShoppingController)
.controller('AlreadyBoughtShoppingController', AlreadyBoughtShoppingController)
.service('ShoppingListCheckOffService', ShoppingListCheckOffService)


ToBuyShoppingController.$inject = ['ShoppingListCheckOffService'];
function ToBuyShoppingController(ShoppingListCheckOffService) {
    var buyList = this;

    buyList.items = ShoppingListCheckOffService.getBuyItems();
}


AlreadyBoughtShoppingController.$inject = ['ShoppingListCheckOffService'];
function AlreadyBoughtShoppingController(ShoppingListCheckOffService) {
    var bougthList = this;

    bougthList.items = ShoppingListCheckOffService.getBoughtItems();
}


function ShoppingListCheckOffService() {
    var service = this;

    var buyItems = ShoppingListItems;
    var boughtItems = [];

    service.chechOffItem = function(index) {
        boughtItems.push(buyItems[index]);
        buyItems.splice(index, 1);
    }

    service.getBuyItems = function() {
        return buyItems;
    }

    service.getBoughtItems = function() {
        return boughtItems;
    }
}


})();