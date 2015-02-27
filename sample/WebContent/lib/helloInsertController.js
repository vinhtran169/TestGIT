/**
 *	Insert Controller for Hello table
 */
helloControllers.controller('HelloInsertCtrl', ['$scope', '$http', '$location',
  function($scope, $http, $location) {
  $scope.rsmessage = '';

  // Event: insert
  $scope.insert = function(message) {
    $http.post(host + ':' + port + service + insertWS, {
      InsertItemRequest: {
        "message": message
      }
    }).success(function(data) {
      if (data.result.result) {
    	// Return to List Page if success
        $location.path("/helloHome.html");
      } else {
    	// Show error message if fail
        $scope.rsmessage = 'Insert Error!';
      }
    });
  };
}]);
