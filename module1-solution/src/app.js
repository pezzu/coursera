(function () {
'use strict';

angular.module('LunchCheck', [])
.controller('LunchCheckController', LunchCheckController);

function countDishes(dishes) {
    return dishes.split(",")
                 .map(function(s) { return s.trim(); })
                 .filter(function(s) { return s.length > 0; })
                 .length;
}

function messageHint(numberOfDishes) {
    if(!numberOfDishes || numberOfDishes === 0) {
        var message = 'Please enter data first';
    }
    else {
        var message = (numberOfDishes > 3)? 'Too much!' : 'Enjoy!';
    }

    return message;
}

LunchCheckController.$inject = ['$scope'];
function LunchCheckController($scope) {
    $scope.messageText = '';

    $scope.checkDishes = function() {
        var numberOfDishes = countDishes($scope.dishes);
        $scope.messageText = messageHint(numberOfDishes);
    };
}

})();