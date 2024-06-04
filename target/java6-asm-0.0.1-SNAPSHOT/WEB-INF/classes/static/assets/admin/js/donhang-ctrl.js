app.controller("donhang-ctrl",function($scope,$http){
    $scope.items=[];
    var item={};
    $scope.products=[];
    var prod;
    $scope.form={};
    $scope.donhang={};
    var id = null;
    $scope.initialize=function(){
        // load donhang
        $http.get("/rest/donhang").then(resp =>{
            $scope.items=resp.data;
        });
        $http.get("/rest/products").then(resp =>{
            $scope.products=resp.data;
        });
        $http.get("/rest/accounts").then(resp =>{
            $scope.accounts=resp.data;
        });
    }
    // Hiển thị lên form
    $scope.edit=function(item){
        $scope.form=angular.copy(item);
        $scope.donhang=angular.copy(item);
        $(".nav-tabs button:eq(0)").tab('show');
        id = item.order.username;
    }
    // Xóa form
    $scope.reset=function(){

        $scope.form={
            available:true,
        }
    }
    // product
    $scope.product = function(id){
        if (id > 0) {
            $http.get('/rest/products/'+id).then(resp =>{
                prod = resp.data;
                console.log($scope.form);
                $scope.form.orderdetail.price = prod.price;
            });
        }
    }
    //Tạo mới
    //Cập nhật
    $scope.update=function(){
        donhang=angular.copy($scope.form);
        $http.put(`/rest/donhang`,donhang).then(resp =>{
            var index=$scope.items.findIndex(p => p == donhang);
            $scope.items[index]=donhang;
            this.initialize();
            this.reset();
            alert('Cập nhật thành công');
        }).catch(error =>{
            alert('Cập nhật thất bại');
            console.log('Error: ',error);
        })
    }
    //Xóa
    $scope.delete=function(id){
        console.log(id);
        // item=angular.copy($scope.form);
        console.log(id);
        $http.delete(`/rest/donhang/${id}`).then(resp =>{
            var index=$scope.items.findIndex(p => p == item);
            $scope.items.splice(index, 1);
            $scope.reset();
            this.initialize();
            alert('Xóa thành công');
        }).catch(error =>{
            console.log('Error: ',error);
            alert('Xóa thất bại');
        })
    }

    $scope.status=function(){
        // var item=angular.copy($scope.form.order)
        //console.log(item.id);
        if(id != null){
            return true;
        }else return false;
    }
    $scope.initialize();
    $scope.pager={
        page:0,
        size:8,
        get items(){
            if(this.page < 0){
				this.last();
			}
			if(this.page >= this.count){
				this.first();
			}
			var start = this.page*this.size;
			return $scope.items.slice(start, start + this.size);
        },
        get count(){
            return Math.ceil(1.0 * $scope.items.length/this.size);
        },
		first(){
			this.page = 0;
		},
		last(){
			this.page = this.count - 1;
		},
		next(){
			this.page++;
		},
		prev(){
			this.page--;
		}
    }
});
