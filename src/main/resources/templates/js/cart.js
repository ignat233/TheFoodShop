 function allStorage(){
 var values = [];
 keys = Object.keys(localStorage);
 i = keys.length;

 while ( i-- ) {
 values.push( JSON.parse(localStorage.getItem(keys[i])) );
 }
 return values;
 }

 function allProductInOrder(){
 var product = [];
  n =allStorage().length -1;
  while ( i-- ) {
  product.push(allStorage()[i].id);
  }
  return product;
 }

  function qtyProductInOrder(){
  var qty = [];
   n =allStorage().length -1;
   while ( i-- ) {
   qty.push(allStorage()[i].qty);
   }
   return qty;
  }



 angular.module('basketApp', []).controller('basketCtrl',function($scope, $http) {
        $scope.products = allStorage();
        $scope.sum = function(){
 var sum = 0;
 n =allStorage().length -1;
 while(n>=0){
 sum += allStorage()[n].price * allStorage()[n].qty;
 n--;
 }
 return sum;
 }

   $scope.putNewInLocalStorage = function(id,product,qtys) {
   if( !isNaN(qtys)) {
   product.qty = qtys;
   localStorage.setItem(id,JSON.stringify(product));}
   }

  $scope.deleteInLocalStorage = function(id,product){
  localStorage.removeItem(id);
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

  $scope.total = function(qty,price){
  if(isNaN(qty)) return 0;
  else return qty * price;
  }

  $scope.addOrder = function(){
   var data = {
                  product: allProductInOrder(),
                  qty: qtyProductInOrder
                };
                 $http({
                      method: 'POST',
                      url: '/addOrder',
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