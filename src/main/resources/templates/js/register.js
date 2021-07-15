angular.module('registerApp', []).controller('registerCtrl',function($scope, $http) {

$scope.sendData = function(){
 var data = {
                name: $scope.name,
                login: $scope.login,
                password: $scope.password,
                number: $scope.number,
                address: $scope.address,
            };
       $http({
            method: 'POST',
            url: '/register',
            data: data,
             headers: {
                'Content-Type': 'application/json'
            }
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