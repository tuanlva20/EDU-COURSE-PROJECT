app.controller("accounts-ctrl",function($scope,$http){
    $scope.items=[];
    var item={};
    $scope.form={
        photo:'upload-img.jpeg'
    };
    var id;
    itemtemp=[];

    // Config account Cloud
    var CLOUDINARY_URL= 'https://api.cloudinary.com/v1_1/otakukayn/upload'; // url
    var CLOUDINARY_UPLOAD_PRESET = 'iy0pkiy7'; // key
    var formData = new FormData();

    function myFunction(item, index, array) {
        array[index] = item.username;
    }
    $scope.initialize=function(){
        // load accounts
        $http.get("/rest/accounts").then(resp =>{
            $scope.items=resp.data; 
        });
        id = null;
    }
    
    // Hiển thị lên form
    $scope.edit=function(item){
        $scope.form=angular.copy(item);
        $(".nav-tabs button:eq(0)").tab('show');
        id = item.username;
    }
    // Xóa form
    $scope.reset=function(){
        $scope.form={
            available:true,
            photo:'upload-img.jpeg'
        }
        id = null;
    }
    //Tạo mới
    $scope.create=function(){
        var bo = false
        itemtemp = $scope.items;
        itemtemp.forEach(myFunction);
        item = angular.copy($scope.form);

        // Config account Cloud
    	var fileUpload = document.getElementById("avatarproduct"); // get input type file
    	// var imgPreview = document.getElementById("blah"); // xem trước hình ảnh
    	// var uploadFiles_imganhmau = document.getElementById("uploadFiles");
    	var file = fileUpload.files[0]; //  lấy ra hình ảnh
        		 // tạo form data
        formData.append('file',file); 		 // key : value 
        formData.append('upload_preset', CLOUDINARY_UPLOAD_PRESET); // Chọn khóa
		
        for (let index = 0; index < itemtemp.length; index++) {
            if (itemtemp[index] == item.username) {
                bo = false;
                break;
            }
            bo = true;
        }
        if (item.username != null) {
            if (bo) {
                axios({
                    url : CLOUDINARY_URL,
                    method : 'POST',
                    headers : {
                        'Content-Type' : 'application/x-www-form-urlencoded'
                    },
                    data : formData
                    
                }).then(function(res){
                    // var photo = res.data.secure_url; // trả về cái url của ảnh xong ông lưu cái địa chỉ vào database 
                    // console.log("");
                    $http.post(`/rest/accounts`,item).then(resp =>{
                        $scope.items.push(resp.data);
                        alert('Thêm mới thành công!')
                        }).catch(error =>{
                            alert('Thêm mới thất bại')
                            console.log('Error: ',error);
                        });
                }).catch(function(err){
                    console.log(err);
                });
               
            }else alert("Tài khoản đã tồn tại");
        }else alert("Tài khoản không được bỏ trống");
        $scope.initialize();
        
    }
    //Cập nhật
    $scope.update=function(){
        item=angular.copy($scope.form);

        // Config account Cloud
    	var fileUpload = document.getElementById("avatarproduct"); // get input type file
    	// var imgPreview = document.getElementById("blah"); // xem trước hình ảnh
    	// var uploadFiles_imganhmau = document.getElementById("uploadFiles");
    	var file = fileUpload.files[0]; //  lấy ra hình ảnh	 // tạo form data
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
            // var photo = res.data.secure_url; // trả về cái url của ảnh xong ông lưu cái địa chỉ vào database 
            // // console.log(photo, $scope.form, item.photo = photo);
            $http.put("/rest/accounts/${item.username}",item).then(resp => {
                var index=$scope.items.findIndex(p => p.username == item.username);
                $scope.items[index]=item;
                alert('Cập nhật thành công');
            }).catch(error =>{
                alert('Cập nhật thất bại');
                console.log('Error: ',error);
            })
        })
    }
    //Xóa
    $scope.delete=function(item){
        $http.delete(`/rest/accounts/${item.username}`).then(resp =>{
            var index=$scope.items.findIndex(p => p.username==item.username);
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
			$scope.form.photo = resp.data.name;
		}).catch(error => {
			alert("Lỗi upload hình ảnh");
			console.log("Error", error);
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
