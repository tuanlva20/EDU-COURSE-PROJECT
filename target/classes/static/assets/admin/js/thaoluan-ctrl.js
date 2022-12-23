app.controller("thaoluan-ctrl",function($scope,$http){
    $scope.items=[];
    var item={};
    $scope.form={};
    var id;
    $scope.initialize=function(){
        // load thaoluan
        $http.get("/rest/thaoluan").then(resp =>{
            $scope.items=resp.data;
        });
        $http.get("/rest/baihoc").then(resp =>{
            $scope.baihoc=resp.data;
        });
        $http.get("/rest/accounts").then(resp =>{
            $scope.account=resp.data;
        });
    }
    // Hiển thị lên form
    $scope.edit=function(item){
        $scope.form=angular.copy(item);
        $(".nav-tabs button:eq(0)").tab('show');
        id = item.id;
    }
    // Xóa form
    $scope.reset=function(){
        $scope.form={
        }
        id = null;
    }
    //Tạo mới
    $scope.create=function(){
        item=angular.copy($scope.form);
        $http.post(`/rest/thaoluan`,item).then(resp =>{
            var index=$scope.items.findIndex(p => p.id == item.id);
            $scope.items[index]=item;
            this.initialize();
            this.reset();
            alert('Thêm mới thành công');
        }).catch(error =>{
            alert('Thêm mới thất bại');
            console.log('Error: ',error);
        })
    }
    //Cập nhật
    $scope.update=function(){
        item=angular.copy($scope.form);
        console.log(item);
        $http.put(`/rest/thaoluan`,item).then(resp =>{
            var index=$scope.items.findIndex(p => p.id == item.id);
            $scope.items[index]=item;
            this.initialize();
            this.reset();
            alert('Cập nhật thành công');
        }).catch(error =>{
            alert('Cập nhật thất bại');
            console.log('Error: ',error);
        })
    }
    //Xóa
    $scope.delete=function(form){
        item=angular.copy($scope.form);
        $http.delete(`/rest/thaoluan/${form.id}`).then(resp =>{
            var index=$scope.items.findIndex(p => p.id == item.id);
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
