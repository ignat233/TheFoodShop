              angular.module('userApp', []).controller('userCtrl',function($scope, $http) {

 $http({
    method : "GET",
      url : "/getUser"
  }).then(
            function(res) {
    var user = res.data;
$scope.login = user.username;
$scope.name = user.name;
$scope.number = user.number;
$scope.address = user.address;
            },
            function(res) {
                console.log("Error: " + res.status + " : " + res.data);
                }
  );

$scope.editData = function(name,number,address){
var data ={
"name": name,
"number": number,
"address": address
}
                           $http({
                                method: 'POST',
                                url: '/editData',
                                data: data,
                            }).then(function success(res){
                                    $scope.res = res.data;
                            },
                            function(res){
                            console.log('not cool');
                        }
                            );
}

});