/**
 * Created by shekhargulati on 10/06/14.
 */

var app = angular.module('todoapp', [
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngRoute'
]);

app.config(function ($routeProvider) {

    $routeProvider.when('/', {
        templateUrl: 'views/list.html',
        controller: 'ListCtrl'
    }).when('/create', {
        templateUrl: 'views/create.html',
        controller: 'CreateCtrl'
    }).when('/edit/:id', {
        templateUrl: 'views/edit.html',
        controller: 'EditCtrl'
    }).otherwise({
        redirectTo: '/'
    })
});

app.controller('ListCtrl', function ($scope, $http, $filter) {

    var orderBy = $filter('orderBy');

    $http.get('/api/v1/todos').success(function (data) {
        $scope.todos = data;

        $scope.order = function(predicate, reverse) {
            $scope.todos = orderBy($scope.todos, predicate, reverse);
        };
        $scope.order('name',false);

    }).error(function (data, status) {
        console.log('Error ' + data)
    })

});


app.controller('EditCtrl', function ($scope, $http, $location) {
    console.log($location.path())
    $http.get('/api/v1/todos' + $location.path()).success(function (data) {
        $scope.todo = data;
    }).error(function (data, status) {
        console.log('Error ' + data)
    })


    $scope.updateTodo= function () {
        var todo = $scope.todo;
        console.log();
        $http.put('/api/v1/todos/' + todo.id, todo).success(function (data) {
            console.log('status changed');
            $location.path('/');
        }).error(function (data, status) {
            console.log('Error ' + data)
        })
    }



});

app.controller('CreateCtrl', function ($scope, $http, $location) {

    $scope.starters = [
        { code: "Tandoori Trio", name: "Tandoori Trio" },
        { code: "Gilafi Seekh Kabab", name: "Gilafi Seekh Kabab" },
        { code: "Tandoori Gulnar", name: "Tandoori Gulnar" },
        { code: "Tandoori Vegetarian Salad", name: "Tandoori Vegetarian Salad" },
        { code: "I Bullets", name: "I Bullets" },
        { code: "Lal Mirch Ka Paneer", name: "Lal Mirch Ka Paneer" }
    ];

    $scope.mains = [
        { code: "Handi Chicken", name: "Handi Chicken" },
        { code: "Rogan Josh", name: "Rogan Josh" },
        { code: "Tandoori Murg Makhanwala", name: "Tandoori Murg Makhanwala" },
        { code: "Tawa ka Kukkad", name: "Tawa ka Kukkad" },
        { code: "Kadhai Sabji", name: "Kadhai Sabji" },
        { code: "Himalayan Hariyali", name: "Himalayan Hariyali" },
        { code: "Shahi Paneer Kofta", name: "Shahi Paneer Kofta" }
    ];

    $scope.spiciness = [
        { code: "Mild", name: "Mild" },
        { code: "Medium", name: "Medium" },
        { code: "Hot", name: "Hot" },
        { code: "Extra Hot", name: "Extra Hot" }
    ];

    $scope.createTodo = function () {
        console.log($scope.todo);
        $http.post('/api/v1/todos', $scope.todo).success(function (data) {
            $location.path('/');
        }).error(function (data, status) {
            console.log('Error ' + data)
        })
    }
});