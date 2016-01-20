angular.module("teknov", ["teknov.services"]).
    config(function ($routeProvider) {
        $routeProvider
            .when('/people', {templateUrl: 'views/people/list.html', controller: PeopleListController});
    });

angular.module("teknov.services", ["ngResource"]).
    factory('PeopleFactory', function ($resource) {
        var People = $resource('api/people');
        return People;
    });

function PeopleListController($scope, PeopleFactory) {
    $scope.persons = PeopleFactory.query();
    
}

