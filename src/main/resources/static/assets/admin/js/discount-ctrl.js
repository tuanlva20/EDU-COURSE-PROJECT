app.controller("discount-ctrl", function($scope,$http){
    $scope.items=[];
    var item={};
    var id;
    var date = new Date();
    $scope.form={
        createdate : date.getUTCFullYear()+"-"+date.getMonth() +"-"+date.getDate()
    }
    $scope.initialize=function(){
        // load discount
        $http.get("/rest/discount").then(resp =>{
            $scope.items=resp.data;
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
        console.log($scope.form);
        date = new Date(item.createdate);
        var startdate = new Date(''+item.startdate);
        var enddate = new Date(''+item.enddate);


        if (date < startdate && startdate < enddate) {
            
            $http.post(`/rest/discount`,item).then(resp =>{
                $scope.items.push(resp.data);
                $scope.initialize();
                this.reset();
                alert('Thêm mới thành công!')
            }).catch(error =>{
                alert('Thêm mới thất bại')
                console.log('Error: ',error);
            })
        }else alert('Ngày tạo không được lớn hơn ngày bắt đầu và ngày bắt đầu không được lớn hơn ngày kết thúc');
        
    }
    //Cập nhật
    $scope.update=function(){
        item=angular.copy($scope.form);
        date = new Date(item.createdate);
        var startdate = new Date(''+item.startdate);
        var enddate = new Date(''+item.enddate);
        if (item.createdate < item.startdate && item.startdate < item.enddate) {
            $http.put(`/rest/discount`,item).then(resp =>{
                var index=$scope.items.findIndex(p => p.id == item.id);
                $scope.items[index]=item;
                alert('Cập nhật thành công');
            }).catch(error =>{
                alert('Cập nhật thất bại');
                console.log('Error: ',error);
            })
        }else alert('Ngày tạo không được lớn hơn ngày bắt đầu và ngày bắt đầu không được lớn hơn ngày kết thúc');
        
    }
    //Xóa
    $scope.delete=function(item){
        $http.delete(`/rest/discount/${item.id}`).then(resp =>{
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