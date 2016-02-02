angular.module("teknov2", ["teknov2.services"]).
    config(function ($routeProvider) {
        $routeProvider
            .when('/people', {templateUrl: 'views/people/list.html', controller: PeopleListController});
    });

angular.module("teknov2.services", ["ngResource"]).
    factory('PeopleFactory', function ($resource) {
        var People = $resource('api/people');
        return People;
    });

function PeopleListController($scope, PeopleFactory) {
    $scope.persons = PeopleFactory.query();
    
}

