/**
 * Created by KDMANDAWE on 2/2/2016.
 */
'use strict';

teknovApp.directive('initDirective',['$rootScope',function($rootScope) {
    return {
        restrict: 'A',
        link: function($scope) {
            var to;
            var listener = $scope.$watch(function() {
                clearTimeout(to);
                to = setTimeout(function () {
                    initFullPage();
                    console.log('initialised');
                    listener();
                    $rootScope.$broadcast('initialised');
                }, 500);
            });
        }
    };
}]);

var initFullPage = function(){
    $('#teknovHomepage').fullpage({
        anchors: ['home', 'services', 'aboutUs', 'todo'],
        afterLoad: function (anchorLink, index) {
            $(".nav").find(".active").removeClass("active");
            $('li[data-menuanchor="'+ anchorLink + '"]').addClass("active");
        }
    });

    //for navigation active class
    $(".nav a").on("click", function(){
        $(".nav").find(".active").removeClass("active");
        $(this).parent().addClass("active");
    });
}