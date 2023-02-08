app.controller("thongke-ctrl",function($scope,$http){

    $scope.items;
    $scope.chartName;

    function lineGraph(object) {
        var xValues = Object.keys(object);
        var yValues = Object.values(object);
        
        new Chart("lineGraph", {
        type: "line",
        data: {
            labels: xValues,
            datasets: [{
            fill: false,
            lineTension: 0,
            backgroundColor: "rgba(0,0,255,1.0)",
            borderColor: "rgba(0,0,255,0.1)",
            data: yValues
            }]
        },
        options: {
            legend: {display: false},
            scales: {
            yAxes: [{ticks: {min: 0, max:100}}],
            }
        }
        });        
    }

    function barChar(object) {
        var xValues = Object.keys(object);
        var yValues = Object.values(object);
        var barColors = ["red", "green","blue","orange","brown", "yellow", "deeppink", "purple", "pink", "aqua", "cadetblue", "cornflowerblue"];
        
        new Chart("barChart", {
          type: "bar",
          data: {
            labels: xValues,
            datasets: [{
              backgroundColor: barColors,
              data: yValues
            }]
          },
          options: {
            legend: {display: false},
            title: {
              display: true,
              text: "Biểu đồ tỉ lệ đăng ký tài khoản trong năm 2022"
            }
          }
        });
    }

    $scope.initialize=function(){
        $http.get("/rest/thongke").then(resp =>{
            $scope.items=resp.data; 
            lineGraph($scope.items.chart);
            barChar($scope.items.monthInYear);
        });
        
        
    }

    // Khởi đầu
    $scope.initialize();
});

