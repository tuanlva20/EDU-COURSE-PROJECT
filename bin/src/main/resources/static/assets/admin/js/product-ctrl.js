app.controller("product-ctrl",function($scope,$http){
    $scope.items=[];
    var item={};
    $scope.form={
        image:'upload-img.jpeg'
    };
    $scope.categories=[];

    $scope.initialize=function(){
        // load products
        $http.get("/rest/products").then(resp =>{
            $scope.items=resp.data;
        });
        //load category
        $http.get(`/rest/categories`).then(resp =>{
            $scope.categories=resp.data;
        });
    }

    // Hiển thị lên form
    $scope.edit=function(item){
        $scope.form=angular.copy(item);
        $(".nav-tabs button:eq(0)").tab('show');
    }
    // Xóa form
    $scope.reset=function(){
        $scope.form={
            available:true,
            image:'upload-img.jpeg'
        }
    }
    //Tạo mới
    $scope.create=function(){
        $scope.form.discount.id=1;
        item = angular.copy($scope.form);
        console.log(item);
        $http.post(`/rest/products`,item).then(resp =>{
            $scope.items.push(resp.data);
            this.reset();
            alert('Thêm mới thành công!')
        }).catch(error =>{
            alert('Thêm mới thất bại')
            console.log('Error: ',error);
        })
    }
    //Cập nhật
    $scope.update=function(){
        item=angular.copy($scope.form);
        $http.put(`/rest/products/${item.id}`,item).then(resp =>{
            var index=$scope.items.findIndex(p => p.id == item.id);
            $scope.items[index]=item;
            alert('Cập nhật thành công');
        }).catch(error =>{
            alert('Cập nhật thất bại');
            console.log('Error: ',error);
        })
    }
    //Xóa
    $scope.delete=function(item){
        $http.delete(`/rest/products/${item.id}`).then(resp =>{
            var index=$scope.items.findIndex(p => p.id==item.id);
            $scope.items.splice(index, 1);
            $scope.reset();
            alert('Xóa thành công');
        }).catch(error =>{
            console.log('Error: ',error);
            alert('Xóa thất bại');
        })
    }
    //Khởi đầu
    // $scope.initialize();
    //Upload hình
    $scope.imageChanged=function(files){
        var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/images', data, {
			transformRequest: angular.identity,
			headers: {'Content-Type': undefined}
        }).then(resp => {
			$scope.form.image = resp.data.name;
		}).catch(error => {
			alert("Lỗi upload hình ảnh");
			console.log("Error", error);
		})
    }

    $scope.status=function(){
        var item=angular.copy($scope.form)
        if(item.id != null){
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
