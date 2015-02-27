/**
 *	Business Application for Hello table.
 */
var helloApp = angular.module('helloApp', [ 'ngRoute', 'ui.bootstrap', 'helloControllers' ]);

// Routing
helloApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/item/:itemId', {
		title: 'メッセージ変更',
		templateUrl : 'helloUpdate.html',
		controller : 'HelloUpdateCtrl'
	}).when('/list', {
		title: 'メッセージ一覧',
		templateUrl : 'helloList.html',
		controller : 'HelloListCtrl'
	}).when('/insert', {
		title: 'メッセージ新規',
		templateUrl : 'helloInsert.html',
		controller : 'HelloInsertCtrl'
	}).when('/type/update', {
		title: 'Type Update',
		templateUrl : 'instanceTypeUpdate.html',
		controller : 'HelloInsertCtrl' //temporary, need to write anew later
	}).otherwise({
		redirectTo : '/list'
	});
} ]);

//change Page Title based on the routers
helloApp.run(['$location', '$rootScope', function($location, $rootScope) {
    $rootScope.$on('$routeChangeSuccess', function (event, current, previous) {
        $rootScope.title = current.$$route.title;
    });
}]);

// Define a variable for all controllers.
var helloControllers = angular.module('helloControllers', []);

// host
var host = 'http://localhost';
// port
var port = '30042';
// web service path
var service = '/jax-rs/m2m/';

//services
var insertWS = 'insert';
var updateWS = 'update';
var deleteWS = 'delete';
var searchWS = 'search';
var getWS = 'get';