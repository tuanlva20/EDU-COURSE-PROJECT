app.controller("thongke-ctrl",function($scope,$http){

    $scope.month = 1;
    $scope.year = 2000;
    $scope.countAccount = 1;
    $scope.newAccount = 1;

    $scope.initialize=function(){
       
        $http.get("/rest/accounts").then(resp =>{
            $scope.items=resp.data; 
        });
        id = null;
    }

    // Khởi đầu
    $scope.initialize();
   
});

