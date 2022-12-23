app.controller("chuonghoc-ctrl", function($scope,$http){
    $scope.items=[];
    var item={};
    var id;
    $scope.form={
        chuonghocimg:'upload-img.jpeg'
    };

    // Config account Cloud
    var CLOUDINARY_URL= 'https://api.cloudinary.com/v1_1/otakukayn/upload'; // url
    var CLOUDINARY_UPLOAD_PRESET = 'iy0pkiy7'; // key
    var formData = new FormData();

    $scope.initialize=function(){
        // load discount
        $http.get("/rest/chuonghoc").then(resp =>{
            $scope.items=resp.data;
        });
        $http.get("/rest/products").then(resp =>{
            $scope.products=resp.data;
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

         // Config account Cloud
    	var fileUpload = document.getElementById("avatarproduct"); // get input type file
    	// var imgPreview = document.getElementById("blah"); // xem trước hình ảnh
    	// var uploadFiles_imganhmau = document.getElementById("uploadFiles");
    	var file = fileUpload.files[0]; //  lấy ra hình ảnh
        		 // tạo form data
        formData.append('file',file); 		 // key : value 
        formData.append('upload_preset', CLOUDINARY_UPLOAD_PRESET); // Chọn khóa

        axios({
            url : CLOUDINARY_URL,
            method : 'POST',
            headers : {
                'Content-Type' : 'application/x-www-form-urlencoded'
            },
            data : formData
            
        }).then(function(res){
            $http.post(`/rest/chuonghoc`,item).then(resp =>{
                $scope.items.push(resp.data);
                $scope.initialize();
                $scope.reset();
                alert('Thêm mới thành công!')
            }).catch(error =>{
                alert('Thêm mới thất bại')
                console.log('Error: ',error);
            })
        }).catch(function(err){
            console.log(err);
        });

        
    }
    //Cập nhật
    $scope.update=function(){
        item=angular.copy($scope.form);

         // Config account Cloud
    	var fileUpload = document.getElementById("avatarproduct"); // get input type file
    	// var imgPreview = document.getElementById("blah"); // xem trước hình ảnh
    	// var uploadFiles_imganhmau = document.getElementById("uploadFiles");
    	var file = fileUpload.files[0]; //  lấy ra hình ảnh
        		 // tạo form data
        formData.append('file',file); 		 // key : value 
        formData.append('upload_preset', CLOUDINARY_UPLOAD_PRESET); // Chọn khóa

        axios({
            url : CLOUDINARY_URL,
            method : 'POST',
            headers : {
                'Content-Type' : 'application/x-www-form-urlencoded'
            },
            data : formData
            
        }).then(function(res){
            $http.put(`/rest/chuonghoc`,item).then(resp =>{
                var index=$scope.items.findIndex(p => p.id == item.id);
                $scope.items[index]=item;
                alert('Cập nhật thành công');
                $scope.reset();
            }).catch(error =>{
                alert('Cập nhật thất bại');
                console.log('Error: ',error);
            })  
        }).catch(function(err){
            console.log(err);
        });
      
    }
    //Xóa
    $scope.delete=function(item){
        $http.delete(`/rest/chuonghoc/${item.id}`).then(resp =>{
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

    //Upload hình
    $scope.imageChanged=function(files){
        var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/images', data, {
			transformRequest: angular.identity,
			headers: {'Content-Type': undefined}
        }).then(resp => {
			$scope.form.chuonghocimg = resp.data.name;
		}).catch(error => {
			alert("Lỗi upload hình ảnh");
			console.log("Error", error);
		})
    }
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