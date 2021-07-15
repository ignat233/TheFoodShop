angular.module('indexApp', []).controller('indexCtrl',function($scope, $http) {

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
  $scope.putInLocalStorage = function(id,product,qtys) {
  if( !isNaN(qtys)){
  product['qty'] = qtys;
  localStorage.setItem(id,JSON.stringify(product));}
  }

 $scope.deleteInLocalStorage = function(id,product){
 localStorage.removeItem(id);
 }

 $scope.dis = function(prd){
               keys = Object.keys(localStorage);
            n = keys.length - 1;
        while(n>=0){
        if(JSON.parse(localStorage.getItem(keys[n])).id == prd.id){
return true;
        }
         n--;
         }
return false;
 }

 $scope.total = function(qty,price){
 if(isNaN(qty)) return 0;
 else return qty * price;
 }



});