app.controller("phuongan-ctrl", function($scope,$http){
    $scope.items=[];
    var item={};
    var id;
    $scope.form={};
    $scope.initialize=function(){
        // load discount
        $http.get("/rest/phuongan").then(resp =>{
            $scope.items=resp.data;
        });
        $http.get("/rest/quiz").then(resp =>{
            $scope.quizs=resp.data;
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
            available:true,
        }
        id = null;
    }
    //Tạo mới
    $scope.create=function(){
        item = angular.copy($scope.form);
        console.log(item);
        $http.post(`/rest/phuongan`,item).then(resp =>{
            $scope.items.push(resp.data);
            $scope.initialize();
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
        $http.put(`/rest/phuongan`,item).then(resp =>{
            var index=$scope.items.findIndex(p => p.id == item.id);
            $scope.items[index]=item;
            $scope.reset();
            $scope.initialize();
            alert('Cập nhật thành công');
        }).catch(error =>{
            alert('Cập nhật thất bại');
            console.log('Error: ',error);
        })
        
    }
    //Xóa
    $scope.delete=function(item){
        $http.delete(`/rest/phuongan/${item.id}`).then(resp =>{
            var index=$scope.items.findIndex(p => p.id==item.id);
            $scope.items.splice(index, 1);
            $scope.initialize();
            $scope.reset();
            alert('Xóa thành công');
        }).catch(error =>{
            console.log('Error: ',error);
            alert('Xóa thất bại');
        })
    }
    //Khởi đầu
    // $scope.initialize();
    $scope.status=function(){
        // var item=angular.copy($scope.form)
        // console.log(item);
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
})