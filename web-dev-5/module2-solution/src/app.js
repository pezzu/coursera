(function () {
'use strict';

const ShoppingListItems = [
   { name: "bananas", quantity: 3 },
   { name: "apples", quantity: 8 },
   { name: "mangos", quantity: 2 },
   { name: "lettuce", quantity: 1 },
   { name: "tomatoes", quantity: 4 },
   { name: "cucumbers", quantity: 6 }
];

angular.module('ShoppingList', [])
.controller('ToBuyShoppingController', ToBuyShoppingController)
.controller('AlreadyBoughtShoppingController', AlreadyBoughtShoppingController)
.service('ShoppingListCheckOffService', ShoppingListCheckOffService)


ToBuyShoppingController.$inject = ['ShoppingListCheckOffService'];
function ToBuyShoppingController(ShoppingListCheckOffService) {
    var buyList = this;

    buyList.items = ShoppingListCheckOffService.getBuyItems();

    buyList.buy = function(index) {
        ShoppingListCheckOffService.chechOffItem(index);
    };
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