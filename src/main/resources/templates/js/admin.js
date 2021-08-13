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

$scope.blockUser = function(username){
 var data = username
       $http({
            method: 'POST',
            url: '/block',
            data: data,
        }).then(function success(res){
        console.log(data);
        $scope.res = res.data;
        location.reload();

        },
        function(res){
        console.log('not cool');
    }
        );
        }

        $scope.value = function(active){
        if(active == true) return "заблокировать пользователя";
        else return "разблокировать пользователя";
        }
});