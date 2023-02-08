app.controller("bill-ctrl",function($scope,$http){
    $scope.items=[];
    var item={};
    $scope.products=[];
    $scope.form={};
    $scope.initialize=function(){
        // load bill
        $http.get("/rest/bill").then(resp =>{
            $scope.items=resp.data;
        });
        $http.get("/rest/products").then(resp =>{
            $scope.products=resp.data;
        });
        $http.get("/rest/accounts").then(resp =>{
            $scope.accounts=resp.data;
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
        }
    }

    //Tạo mới
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
            item=angular.copy($scope.form);
            $http.put(`/rest/bill`,item).then(resp =>{
                var index=$scope.items.findIndex(p => p.id == item.id);
                $scope.items[index]=item;
                this.initialize();
                this.reset();
                alert('Cập nhật thành công');
            }).catch(error =>{
                alert('Cập nhật thất bại');
                console.log('Error: ',error);
            })
        } else {
            alert('Không được bỏ trống dữ liệu');
        }
    }
    //Xóa
    $scope.delete=function(id){
        $http.delete(`/rest/bill/${id}`).then(resp =>{
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
        if($scope.form.id != null){
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
