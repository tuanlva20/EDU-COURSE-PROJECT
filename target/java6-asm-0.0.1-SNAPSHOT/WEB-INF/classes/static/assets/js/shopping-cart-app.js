var app=angular.module("shopping-cart-app",[]);
app.controller("shopping-cart-ctrl",function($scope,$http,$window){
    // QUẢN LÝ GIỎ HÀNG
    var payAmount=0;
    $scope.usernameString;

    $scope.cart={
        items: [],
        //Thêm sản phẩm vào giỏ hàng
        add(id){
            var item=this.items.find(c => c.id==id);
            if(item){
                item.quantity==1;
            }else{
                $http.get(`/rest/products/${id}`).then(resp =>{
                    resp.data.quantity=1;
                    this.items.push(resp.data);
                    this.saveToLocalStorage();
                }).catch(error =>{
                    console.log('Error: '+error);
                });
            }
        },
        //Xóa sản phẩm khỏi giỏ hàng
        remove(id){
            var index=this.items.findIndex(item => item.id==id);
            this.items.splice(index,1);
            this.saveToLocalStorage();
        }, 
        //Xóa sạch các mặt hàng trong giỏ
        clear(){
            this.items=[];
            this.saveToLocalStorage();
        },
        //Tính thành viên của 1 sản phẩm
        amt_of(item){
            
        },
        //Tính số lượng sản phẩm có trong giỏ
        get count(){
            return this.items
                .map(item => item.quantity)
                .reduce((total,quantity)=>total+=quantity,0);
        },
        //Tổng thành tiền các mặt hàng có trong giỏ
        get amount(){
            return this.items
                .map(item => item.quantity * item.price)
                .reduce((total,quantity)=>total+=quantity,0);
        },
        //Lưu giỏ hàng vào local storage
        saveToLocalStorage(){
            var json=JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cart",json);
        },
        //Đọc giỏ hàng từ local storage
        loadFromLocalStorage(){
            var json=localStorage.getItem('cart');
            this.items=json ? JSON.parse(json):[];
        },
    }
    $scope.cart.loadFromLocalStorage();

    

    $scope.account={
        loadInfoAccount(username){
            $http.get('/rest/accounts/user?name='+username).then(resp =>{
                $scope.account = resp.data;   
            })
            if($scope.account == null){
                $http.get('/rest/accounts/'+username).then(resp =>{
                    $scope.account = resp.data;   
                })
            }
        },
    }

    $scope.account.loadInfoAccount($('#username').text());

    $scope.order={
        createdate:new Date(),
        fullname:"",
        email:"",
        address:"",
        phone:"",
        account:{username:""},
        // account:{username:$('#username').text()},

        get orderdetails(){
            return $scope.cart.items.map(item =>{
                return {
                    product:{id:item.id},
                    price:item.price,
                    quantity:item.quantity,
                }
            });
        },

        purchase(){
            this.fullname=$scope.account.fullname;
            this.email=$scope.account.email;
            this.address=$scope.account.address;
            this.phone=$scope.account.phone;
            this.account.username=$scope.account.username;
            var order=angular.copy(this);
            var amount=$scope.cart.amount;
            // Thực hiện đặt hàng
            $http.post("/rest/order",order).then(resp =>{
                $scope.cart.clear();
                var payment={
                    idService:resp.data.id,
                    amount: amount,
                    description:"Thanh toan khoa hoc online",
                    bankcode:"",
                }
                var json=JSON.stringify(payment);
                $http.post(`/create-payment/${resp.data.id}`,json).then(resp =>{
                    $window.location.href=resp.data.url;
                }).catch(error =>{
                    console.log(error);
                })
            }).catch(error =>{
                alert('Thanh toán không thành công!');
                console.log("Error: "+error);
            })
        },
    }

    
    
});
