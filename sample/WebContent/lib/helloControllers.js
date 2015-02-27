/**
 *
 */
var helloControllers = angular.module('helloControllers', []);
var port = '4000';
helloControllers.controller('HelloUpdateCtrl', ['$scope', '$routeParams', '$http', '$location',
  function($scope, $routeParams, $http, $location) {
  $scope.rsmessage = '';
  $scope.itemId = $routeParams.itemId;
  $http.post('http://localhost:' + port + '/jax-rs/m2m/get', {
    GetItemRequest: {
      "id": $scope.itemId
    }
  }).success(function(data) {
    $scope.itemMessage = data.result.row.message;
  });
  $scope.update = function(pmessage) {
    $http.post('http://localhost:' + port + '/jax-rs/m2m/update', {
      UpdateItemRequest: {
        "message": pmessage,
        "id": $scope.itemId
      }
    }).success(function(data) {
      if (data.result.result) {
        $location.path("/helloHome.html");
      } else {
        $scope.rsmessage = 'Update Error!';
      }
    });
  };
}]);
helloControllers.controller('HelloInsertCtrl', ['$scope', '$http', '$location',
  function($scope, $http, $location) {
  $scope.rsmessage = '';
  $scope.insert = function(message) {
    $http.post('http://localhost:' + port + '/jax-rs/m2m/insert', {
      InsertItemRequest: {
        "message": message
      }
    }).success(function(data) {
      if (data.result.result) {
        $location.path("/helloHome.html");
      } else {
        $scope.rsmessage = 'Insert Error!';
      }
    });
  };
}]);
helloControllers.controller('HelloListCtrl', ['$scope', '$http', '$location',
  function($scope, $http, $location) {
    $scope.items = [];
    $scope.title = 'List Page';
    $scope.message = '';
    $scope.numPerPage = 10;
    $scope.currentPage = 1;
    $scope.filItems = [];
    $scope.search = function(message) {
      $scope.message = message;
      $http.post('http://localhost:' + port + '/jax-rs/m2m/search', {
        SearchItemRequest: {
          "item": message
        }
      }).success(function(data) {
        $scope.filItems = [];
        $scope.data = data;
        $scope.items = data.result.rows;
        if (angular.isArray($scope.items)) {
          $scope.filItems = $scope.items.slice(0, $scope.numPerPage);
        } else {
          if ($scope.items != null) $scope.filItems.push($scope.items);
        }
      });
    };
    $scope.search($scope.message);
    $scope.del = function(pid) {
      $http.post('http://localhost:' + port + '/jax-rs/m2m/delete', {
        DeleteItemRequest: {
          "id": pid
        }
      }).success(function(data) {
        $scope.item = data;
        $scope.search($scope.message);
      });
    };
    $scope.$watch('currentPage + numPerPage', function() {
      var begin = (($scope.currentPage - 1) * $scope.numPerPage),
        end = begin + $scope.numPerPage;
      $scope.filItems = $scope.items.slice(begin, end);
    });
    $scope.link = function() {
      $location.path("/insert");
    };
    $scope.maxSize = 5;
  }
]);