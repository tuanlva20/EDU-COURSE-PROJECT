app.controller("chuonghoc-ctrl", function($scope,$http){
    $scope.items=[];
    var item={};
    var id;
    var defaultImg = 'upload-img.jpeg';
    $scope.form={
        chuonghocimg:defaultImg
    };
    $scope.ck = true;
    $scope.itemtemp=[];
    // Config account Cloud
    var CLOUDINARY_URL= 'https://api.cloudinary.com/v1_1/otakukayn/upload'; // url
    var CLOUDINARY_UPLOAD_PRESET = 'iy0pkiy7'; // key
    var formData = new FormData();

    //lặp mảng và gán lại giá trị
    function myFunction(item, index, array) {
        array[index] = item.tieude;
    }

    $scope.initialize=function(){
        // load discount
        $http.get("/rest/chuonghoc").then(resp =>{
            $scope.items=resp.data;
            console.log($scope.items.length);
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
        $scope.ck = false;
    }
    // Xóa form
    $scope.reset=function(){
        $scope.form={
            available:true,
            chuonghocimg:defaultImg
        }
        id = null;
        $scope.ck = true;
    }
    //Tạo mới
    $scope.create=function(){
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
            if ($scope.form.chuonghocimg != defaultImg) {
                item = angular.copy($scope.form);
                for (var index = 0; index < $scope.itemtemp.length; index++) {
                    if ($scope.itemtemp[index] == item.tieude) {
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
        
                    axios({
                        url : CLOUDINARY_URL,
                        method : 'POST',
                        headers : {
                            'Content-Type' : 'application/x-www-form-urlencoded'
                        },
                        data : formData
                        
                    }).then(function(res){
                        item.chuonghocimg = res.data.secure_url;
                        $http.post(`/rest/chuonghoc`,item).then(resp =>{
                            // $scope.items.push(resp.data);
                            $scope.reset();
                            alert('Thêm mới thành công!')
                        }).catch(error =>{
                            alert('Thêm mới thất bại')
                            console.log('Error: ',error);
                        })
                    }).catch(function(err){
                        console.log(err);
                    });
                }else alert("Tiêu đề đã tồn tại");
            } else {
                alert('Bạn chưa chọn ảnh');
            }
        } else {
            alert("không được bỏ trống dữ liệu");
        }
        $scope.initialize();
        
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
            if ($scope.form.chuonghocimg != defaultImg) {
                item = angular.copy($scope.form);
    
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
                    item.chuonghocimg = res.data.secure_url;
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
            } else {
                alert('Bạn chưa chọn ảnh');
            }
        } else {
            alert("không được bỏ trống dữ liệu");
        }
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
})