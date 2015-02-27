/**
 *	List Controller for Hello table
 */
helloControllers.controller('HelloListCtrl', ['$scope', '$http', '$location',
  function($scope, $http, $location) {
    $scope.items = [];
    $scope.message = '';
    $scope.rsmessage = '';

    // variables for paging
    $scope.numPerPage = 10;
    $scope.currentPage = 1;
    $scope.filItems = [];
    $scope.maxSize = 5;

    // Search function
    $scope.search = function(message) {
      $scope.message = message;

      // Save current page before search
      var oldPage = $scope.currentPage;
      $scope.currentPage = 0;

      // Call web service
      $http.post(host + ':' + port + service + searchWS, {
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

        // back to page before search
        $scope.currentPage = oldPage;
        $scope.selectedId = -1;
        $scope.rsmessage = '';
      });
    };

    // Search on page load
    $scope.search($scope.message);

    // Event: delete    
    $scope.selectedId = -1;
    $scope.check = function(pid) {
    	$scope.selectedId = pid;
      };

      $scope.delSelected = function() {
    	  if($scope.selectedId!=-1){
          $http.post(host + ':' + port + service + deleteWS, {
            DeleteItemRequest: {
              "id": $scope.selectedId
            }
          }).success(function(data) {
            $scope.item = data;
            $scope.search($scope.message);
            $scope.selectedId = -1;
            $scope.rsmessage = '';
          });
    	  }else {
    	        $scope.rsmessage = 'Delete Error!';
          }
        };
        
     // Event: update     
        $scope.updateSelected = function() {
        	  if($scope.selectedId!=-1){
        		$location.path('/item/'+$scope.selectedId);
        	  }else {
      	        $scope.rsmessage = 'Update Error!';
              }
            };
            
    // handling paging
    $scope.$watch('currentPage + numPerPage', function() {
      var begin = (($scope.currentPage - 1) * $scope.numPerPage),
        end = begin + $scope.numPerPage;
      $scope.filItems = $scope.items.slice(begin, end);
      $scope.selectedId = -1;
      $scope.rsmessage = '';
    });

    // Event: Insert button click
    $scope.link = function() {
      $location.path("/insert");
    };
  }
]);