          angular.module('adminOrderApp', []).controller('adminOrderCtrl',function($scope, $http) {

 $http({
    method : "GET",
      url : "/orders"
  }).then(
            function(res) {
$scope.orders = res.data;
            },
            function(res) {
                console.log("Error: " + res.status + " : " + res.data);
                }
  );

});