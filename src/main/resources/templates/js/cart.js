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
  while ( n>=0) {
  product.push(allStorage()[n].id);
  product.push(allStorage()[n].qty);
  n--;
  }
  return product;
 }

  function qtyProductInOrder(){
  var qty = [];
   n =allStorage().length -1;
   while ( n>=0 ) {
   qty.push(allStorage()[n].qty);
   n--;
   }
   return qty;
  }

  function quantity(){
    var quantity = [];
   n =allStorage().length -1;
   while ( n>=0 ) {
   qty = allStorage()[n].quantity;
   var product = allStorage()[n]
   delete product["quantity"];
   var productQty = new Object();
   productQty["product"] = product;
   productQty["quantity"] =  qty;
   quantity.push(productQty);
   n--;
   }
   return quantity;
  }


 angular.module('basketApp', []).controller('basketCtrl',function($scope, $http) {




        $scope.products = allStorage();
        $scope.sum = function(){
 var sum = 0;
 n =allStorage().length -1;
 while(n>=0){
 sum += allStorage()[n].price * allStorage()[n].quantity;
 n--;
 }
 return sum;
 }

   $scope.putNewInLocalStorage = function(id,product,qtys) {
   if( !isNaN(qtys)) {
   if(qtys==0){
    localStorage.removeItem(id);
  window.location.reload();
   }
   else{
   product.quantity = qtys;
   localStorage.setItem(id,JSON.stringify(product));}}
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

  $scope.total = function(quantity,price){
  if(isNaN(quantity)) return 0;
  else return quantity * price;
  }





         $scope.addOrder = function(){

            if(allProductInOrder().length == 0)  alert("Корзина пустая, добавьте товар");

              else{
               $http({
              method : "GET",
                url : "/getUser"
            }).then(
                      function(res) {
          var user = res.data;
          if(!user.active) alert("Войдите в систему,чтобы сделать заказ");
          else{
             var data =
          {
                            "user": user,
                            "quantity": quantity(),
                            "createDate": null
                            }

                           $http({
                                method: 'POST',
                                url: '/addOrder',
                                data: data ,
                            }).then(function success(res){
                            localStorage.clear();
                            location.reload();
                            alert("Заказ оформлен");
                            $scope.res = res.data;

                            },
                            function(res){
                            console.log('not cool');
                        }
                            );
                            }
                      },
                      function(res) {
                          console.log("Error: " + res.status + " : " + res.data);
                          }
            );


                            }
                            }

 });