app.controller("accounts-ctrl",function($scope,$http){
    $scope.items=[];
    var item={};
    var defaultImg = 'upload-img.jpeg';
    $scope.form={
        photo: defaultImg
    };
    var id;
    $scope.itemtemp=[];
    var ind = 0;
    $scope.ck = true;

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
        $scope.ck = false;
    }
    // Xóa form
    $scope.reset=function(){
        $scope.form={
            available:true,
            photo : defaultImg
        }
        id = null;
        $scope.ck = true;
    }
    //Tạo mới
    $scope.create=function(){
        var validElement = true;
        var selectorElement = document.getElementsByClassName('form-control')
        for (var i = 1; i < selectorElement.length; i++) {
            if (selectorElement[i].value == "") {
                validElement = false;
            }
        }
        if (validElement == true) {
            var bo = false
            $scope.itemtemp = $scope.items;
            $scope.itemtemp.forEach(myFunction);
            item = angular.copy($scope.form);
            console.log($scope.form.photo)
            if ($scope.form.photo != defaultImg) {
                // Config account Cloud
                var fileUpload = document.getElementById("avatarproduct"); // get input type file
                // var imgPreview = document.getElementById("blah"); // xem trước hình ảnh
                // var uploadFiles_imganhmau = document.getElementById("uploadFiles");
                var file = fileUpload.files[0]; //  lấy ra hình ảnh
                        // tạo form data
                formData.append('file',file); 		 // key : value 
                formData.append('upload_preset', CLOUDINARY_UPLOAD_PRESET); // Chọn khóa
                
                for (let index = 0; index < $scope.itemtemp.length; index++) {
                    if ($scope.itemtemp[index] == item.username) {
                        bo = false;
                        break;
                    }
                    bo = true;
                }
                // if (item.username != null) {
                    if (bo) {
                        axios({
                            url : CLOUDINARY_URL,
                            method : 'POST',
                            headers : {
                                'Content-Type' : 'application/x-www-form-urlencoded'
                            },
                            data : formData
                            
                        }).then(function(res){
                            item.photo = res.data.secure_url; // trả về cái url của ảnh xong ông lưu cái địa chỉ vào database 
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
                // }else alert("Tài khoản không được bỏ trống");
            $scope.initialize();
            } else {
                alert('Vui lòng chọn ảnh');
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
            if ($scope.form.photo != defaultImg) {
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
                    item.photo = res.data.secure_url; // trả về cái url của ảnh xong ông lưu cái địa chỉ vào database 
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
            } else {
                alert('Bạn chưa chọn ảnh');
            }
        } else {
            alert('Không được bỏ trống dữ liệu');
        }
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

    // lọc dữ liệu và fill lại search
    $scope.search = function () {
        var input, filter,td , tr, i, tbody
        input = document.getElementById("myInput");
        filter = input.value.toUpperCase();
        tbody = document.getElementById("myTbody");
        tr = tbody.getElementsByTagName("tr");
        for (var index = 0; index < tr.length; index++) {
            td = tr[index].getElementsByTagName("td");
            for (i = 1; i < td.length - 1; i++) {
                var  tempc = false;
                txtValue = td[i].textContent || td[i].innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    tempc = true;
                    tr[index].style.display = "";
                    break;
                } else if(i == td.length-2 && tempc == false) {
                    tr[index].style.display = "none";
                }
            }   
        }
        
    }
    
    $scope.exportHTMLToWord = function (){
        var header = "<html xmlns:o='urn:schemas-microsoft-com:office:office' "+
                "xmlns:w='urn:schemas-microsoft-com:office:word' "+
                "xmlns='http://www.w3.org/TR/REC-html40'>"+
                "<head><meta charset='utf-8'><title>Export HTML to Word Document with JavaScript</title></head><body>";
        var footer = "</body></html>";
        var sourceHTML = header+document.getElementById("myTbody").innerHTML+footer;
        
        var source = 'data:application/vnd.ms-word;charset=utf-8,' + encodeURIComponent(sourceHTML);
        var fileDownload = document.createElement("a");
        document.body.appendChild(fileDownload);
        fileDownload.href = source;
        fileDownload.download = 'Tai khoan.doc';
        fileDownload.click();
        document.body.removeChild(fileDownload);
    }

    $scope.exportHTMLToExcel = function () {
        var downloadLink;
        var dataType = 'application/vnd.ms-excel';
        var tableSelect = document.getElementById("myTbl");
        var tableHTML = tableSelect.outerHTML.replace(/ /g, '%20');

        // Specifiy file name
        var filename = "taikhoan";
        filename = filename?filename+'.xls':'excel_data.xls';
        
        //create download link element
        downloadLink = document.createElement("a");

        document.body.appendChild(downloadLink);

        if (navigator.msSaveOrOpenBlod) {
            var blod  = new Blob(['\ufeff', tableHTML], {
                type: dataType
            })
            navigator.msSaveOrOpenBlod(blod, filename);
        } else {
            //create a link to the file
            downloadLink.href = 'data:' + dataType + ',' + tableHTML;

            //setting the file name
            downloadLink.download = filename;

            //triggering the function
            downloadLink.click();
        }
    }
});

