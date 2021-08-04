          angular.module('userOrderApp', []).controller('userOrderCtrl',function($scope, $http) {

 $http({
    method : "GET",
      url : "/showUserOrder"
  }).then(
            function(res) {
$scope.orders = res.data;
            },
            function(res) {
                console.log("Error: " + res.status + " : " + res.data);
                }
  );

});