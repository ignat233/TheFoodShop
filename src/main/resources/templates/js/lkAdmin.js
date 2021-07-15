      angular.module('adminApp', []).controller('adminCtrl',function($scope, $http) {

 $http({
    method : "GET",
      url : "/users"
  }).then(
            function(res) {
$scope.users = res.data;
            },
            function(res) {
                console.log("Error: " + res.status + " : " + res.data);
                }
  );

 $http({
    method : "GET",
      url : "/product"
  }).then(
            function(res) {
$scope.products = res.data;
            },
            function(res) {
                console.log("Error: " + res.status + " : " + res.data);
                }
  );

$scope.deleteUser = function(username){
 var data = username
       $http({
            method: 'POST',
            url: '/delete',
            data: data,
        }).then(function success(res){
        console.log(data);
        $scope.res = res.data;

        },
        function(res){
        console.log('not cool');
    }
        );
        }
});