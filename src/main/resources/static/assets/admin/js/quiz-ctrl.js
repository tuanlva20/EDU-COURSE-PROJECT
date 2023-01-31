app.controller("quiz-ctrl", function($scope,$http){
    $scope.items=[];
    var item={};
    var id;
    $scope.form={};
    $scope.itemtemp=[];

    //lặp mảng và gán lại giá trị
    function myFunction(item, index, array) {
        array[index] = {
            noidung : item.noidung,
            baihocid : item.baihoc.baihocid
        };
        
    }

    $scope.initialize=function(){
        // load discount
        $http.get("/rest/quiz").then(resp =>{
            $scope.items=resp.data;
        });
        $http.get("/rest/baihoc").then(resp =>{
            $scope.baihoc=resp.data;
        });
    }
    // Hiển thị lên form
    $scope.edit=function(item){
        $scope.form=angular.copy(item);
        $(".nav-tabs button:eq(0)").tab('show');
        id = item.idquiz;
    }
    // Xóa form
    $scope.reset=function(){
        $scope.form={
        }
        id = null;
    }
    //Tạo mới
    $scope.create=function(){
        
        item = angular.copy($scope.form);
        var bo = false;
        $scope.itemtemp = $scope.items;
        $scope.itemtemp.forEach(myFunction);
        var validElement = true;
        var selectorElement = document.getElementsByClassName('form-control')
            for (var i = 0; i < selectorElement.length; i++) {
                if (selectorElement[i].value == "") {
                    validElement = false;
                }
            }
            if (validElement == true) {
                for (var index = 0; index < $scope.itemtemp.length; index++) {
                    if ($scope.itemtemp[index].noidung == item.noidung.trim() && $scope.itemtemp[index].baihocid == item.baihoc.baihocid) {
                        bo = false;
                        break;
                    }
                    bo = true;
                }
                if (bo) {
                    $http.post("/rest/quiz",item).then(resp =>{
                        $scope.items.push(resp.data);
                        $scope.initialize();
                        this.reset();
                        alert('Thêm mới thành công!')
                    }).catch(error =>{
                        alert('Thêm mới thất bại')
                        console.log('Error: ',error);
                    })
                }else alert("Bài học đã có câu hỏi này");
            } else {
                alert("không được bỏ trống dữ liệu");
            }
        
    }
    //Cập nhật
    $scope.update=function(){
        item=angular.copy($scope.form);
        var selectorElement = document.getElementsByClassName('form-control')
            for (var i = 0; i < selectorElement.length; i++) {
                if (selectorElement[i].value == "") {
                    validElement = false;
                }
            }
            if (validElement == true) {
                $http.put("/rest/quiz",item).then(resp =>{
                    var index=$scope.items.findIndex(p => p.idquiz == item.idquiz);
                    $scope.items[index]=item;
                    alert('Cập nhật thành công');
                }).catch(error =>{
                    alert('Cập nhật thất bại');
                    console.log('Error: ',error);
                })
            } else {
                alert("không được bỏ trống dữ liệu");
            }
        
        
    }
    //Xóa
    $scope.delete=function(item){
        $http.delete(`/rest/quiz/${item.idquiz}`).then(resp =>{
            var index=$scope.items.findIndex(p => p.idquiz==item.idquiz);
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