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
  $scope.putInLocalStorage = function(id,product,index) {
   var qtys = document.getElementById(index).value;
  if(qtys == '0'){ localStorage.removeItem(id);}
  else{product['quantity'] = qtys;
  localStorage.setItem(id,JSON.stringify(product));}
  }

  $scope.quantity = function(index){
  if(localStorage.getItem(index)==null) return 0;
  else return JSON.parse(localStorage.getItem(index)).quantity;
  }

 $scope.deleteInLocalStorage = function(id,product){
 localStorage.removeItem(id);
<!-- $('#block').html(entry)-->
 window.location.reload();
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

 $scope.total = function(quantity,price,index){
if(!isNaN(quantity)){ return quantity * price;}
 else if(localStorage.getItem(index)==null) return 0;
  else return JSON.parse(localStorage.getItem(index)).quantity * price;
 }



});