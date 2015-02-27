/**
 *	Update Controller for Hello table
 */
helloControllers.controller('HelloUpdateCtrl', ['$scope', '$routeParams', '$http', '$location',
  function($scope, $routeParams, $http, $location) {
  $scope.rsmessage = '';
  $scope.itemId = $routeParams.itemId;

  // Call get web service to get current data
  $http.post(host + ':' + port + service + getWS, {
    GetItemRequest: {
      "id": $scope.itemId
    }
  }).success(function(data) {
    $scope.itemMessage = data.result.row.message;
  });

  // Event: update
  $scope.update = function(pmessage) {
    $http.post(host + ':' + port + service + updateWS, {
      UpdateItemRequest: {
        "message": pmessage,
        "id": $scope.itemId
      }
    }).success(function(data) {
      if (data.result.result) {
    	// Return to List Page if success
        $location.path("/helloHome.html");
      } else {
    	// Show error message if fail
        $scope.rsmessage = 'Update Error!';
      }
    });
  };
}]);
