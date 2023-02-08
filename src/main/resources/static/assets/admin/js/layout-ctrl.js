app.controller("layout-ctrl", function($scope, $http){

    $scope.items;

    $scope.initialize = function (){
        $http.get("/rest/layout").then(resp => {
            $scope.items = resp.data;
            $scope.products = Object.values($scope.items.products);
            $scope.earns = Object.values($scope.items.totalPriceOfOrderByOrderId);
            var arrayObjects = [];
            for (var i = 0; i < $scope.products.length; i++) {
                $scope.object = {
                    product : $scope.products[i],
                    earn : $scope.earns[i]
                };
                arrayObjects.push($scope.object);
            }
            $scope.arrayItems = arrayObjects;
        })
    }

    $scope.initialize();

})