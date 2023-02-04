app.controller("layout-ctrl", function($scope, $http){

    $scope.items;

    $scope.initialize = function (){
        $http.get("/rest/layout").then(resp => {
            $scope.items = resp.data;
        })
        console.log($scope.items);
        alert("aaaaa");
    }

    $scope.initialize();

})