(function () {
'use strict';

angular.module('LunchCheck', [])
.controller('LunchCheckController', LunchCheckController);

function countDishes(dishes) {
    if(!dishes) {
        return 0;
    }
    else {
        return dishes.split(",")
                     .map(function(s) { return s.trim(); })
                     .filter(function(s) { return s.length > 0; })
                     .length;
    }
}

function messageHint(numberOfDishes) {
    var message = {};

    if(!numberOfDishes || numberOfDishes === 0) {
        message.text = 'Please enter data first';
        message.class = 'error';
    }
    else {
        message.text = (numberOfDishes > 3)? 'Too much!' : 'Enjoy!';
        message.class = 'note';
    }

    return message;
}

LunchCheckController.$inject = ['$scope'];
function LunchCheckController($scope) {

    $scope.checkDishes = function() {
        var numberOfDishes = countDishes($scope.dishes);
        var message = messageHint(numberOfDishes);

        $scope.messageText = message.text;
        $scope.messageClass = message.class;
    };
}

})();