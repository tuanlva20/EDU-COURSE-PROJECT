app.controller("product-ctrl",function($scope,$http){
    $scope.items=[];
    var item={};
    var defaultImg = 'upload-img.jpeg';
    $scope.form={
        available:true,
        image:defaultImg
    };
    $scope.categories=[];
    $scope.ck = true;
    $scope.itemtemp=[];

    // Config account Cloud
    var CLOUDINARY_URL= 'https://api.cloudinary.com/v1_1/otakukayn/upload'; // url
    var CLOUDINARY_UPLOAD_PRESET = 'iy0pkiy7'; // key
    var formData = new FormData();

    //lặp mảng và gán lại giá trị
    function myFunction(item, index, array) {
        array[index] = item.name;
        console.log(item.name)
    }

    $scope.initialize=function(){
        // load products
        $http.get("/rest/products").then(resp =>{
            $scope.items=resp.data;
        });
        //load category
        $http.get(`/rest/categories`).then(resp =>{
            $scope.categories=resp.data;
        });
        $http.get(`/rest/discount`).then(resp =>{
            $scope.discounts=resp.data;
        });
    }

    // Hiển thị lên form
    $scope.edit=function(item){
        $scope.form=angular.copy(item);
        $(".nav-tabs button:eq(0)").tab('show');
        $scope.ck = false;
    }
    // Xóa form
    $scope.reset=function(){
        $scope.form={
            available:true,
            image:defaultImg
        }
        $scope.ck = true;
    }
    //Tạo mới
    $scope.create=function(){
        item = $scope.form;
        var bo = false;
        $scope.itemtemp = $scope.items;
        $scope.itemtemp.forEach(myFunction);
        var validElement = true;
        var selectorElement = document.getElementsByClassName('form-control')
            for (var i = 1; i < selectorElement.length; i++) {
                if (selectorElement[i].value == "") {
                    validElement = false;
                }
            }
            if (validElement == true) {
                if ($scope.form.image != defaultImg) {
                    for (var index = 0; index < $scope.itemtemp.length; index++) {
                        if ($scope.itemtemp[index] == item.name) {
                            bo = false;
                            break;
                        }
                        bo = true;
                    }
                    if (bo) {
                        // Config account Cloud
                        var fileUpload = document.getElementById("avatarproduct"); // get input type file
                        // var imgPreview = document.getElementById("blah"); // xem trước hình ảnh
                        // var uploadFiles_imganhmau = document.getElementById("uploadFiles");
                        var file = fileUpload.files[0]; //  lấy ra hình ảnh
                                // tạo form data
                        formData.append('file',file); 		 // key : value 
                        formData.append('upload_preset', CLOUDINARY_UPLOAD_PRESET); // Chọn khóa
            
                        item = angular.copy($scope.form);
                        console.log(item);
            
                        axios({
                            url : CLOUDINARY_URL,
                            method : 'POST',
                            headers : {
                                'Content-Type' : 'application/x-www-form-urlencoded'
                            },
                            data : formData
                            
                        }).then(function(res){
                            item.image = res.data.secure_url;
                            $http.post(`/rest/products`,item).then(resp =>{
                                $scope.items.push(resp.data);
                                alert('Thêm mới thành công!');
                                $scope.reset();
                                $scope.initialize();
                            }).catch(error =>{
                                alert('Thêm mới thất bại')
                                console.log('Error: ',error);
                            })
                        }).catch(function(err){
                            console.log(err);
                        });
                    }else alert("Tên khóa học đã tồn tại");
                } else {
                    alert('Bạn chưa chọn ảnh');
                }
            } else {
                alert("không được bỏ trống dữ liệu");
            }
           
         
    }
    //Cập nhật
    $scope.update=function(){
        var validElement = true;
        var selectorElement = document.getElementsByClassName('form-control')
        for (var i = 1; i < selectorElement.length; i++) {
            if (selectorElement[i].value == "") {
                validElement = false;
            }
        }
        if (validElement == true) {
            if ($scope.form.image != defaultImg) {
                // Config account Cloud
                var fileUpload = document.getElementById("avatarproduct"); // get input type file
                // var imgPreview = document.getElementById("blah"); // xem trước hình ảnh
                // var uploadFiles_imganhmau = document.getElementById("uploadFiles");
                var file = fileUpload.files[0]; //  lấy ra hình ảnh
                        // tạo form data
                formData.append('file',file); 		 // key : value 
                formData.append('upload_preset', CLOUDINARY_UPLOAD_PRESET); // Chọn khóa
    
                item = angular.copy($scope.form);
                console.log(item);
    
                axios({
                    url : CLOUDINARY_URL,
                    method : 'POST',
                    headers : {
                        'Content-Type' : 'application/x-www-form-urlencoded'
                    },
                    data : formData
                    
                }).then(function(res){
                    item.image = res.data.secure_url;
                $http.put(`/rest/products/${item.id}`,item).then(resp =>{
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
            } else {
                alert('Bạn chưa chọn ảnh');
            }
        } else {
            alert("không được bỏ trống dữ liệu");
        }

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
        // console.log(item);
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
     // lọc dữ liệu và fill lại search
     $scope.search = function () {
        var input, filter,td , tr, i, tbody
        input = document.getElementById("myInput");
        filter = input.value.toUpperCase();
        tbody = document.getElementById("myTbody");
        tr = tbody.getElementsByTagName("tr");
        for (var index = 0; index < tr.length; index++) {
            td = tr[index].getElementsByTagName("td");
            for (i = 0; i < td.length; i++) {
                var  tempc = false;
                txtValue = td[i].textContent || td[i].innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    tempc = true;
                    tr[index].style.display = "";
                    break;
                } else if(i == td.length-1 && tempc == false) {
                    tr[index].style.display = "none";
                }
            }   
        }
        
    }
});
