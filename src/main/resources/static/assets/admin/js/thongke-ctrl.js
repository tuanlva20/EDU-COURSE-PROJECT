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

    $scope.exportHTML = function (){
        var header = "<html xmlns:o='urn:schemas-microsoft-com:office:office' "+
                "xmlns:w='urn:schemas-microsoft-com:office:word' "+
                "xmlns='http://www.w3.org/TR/REC-html40'>"+
                "<head><meta charset='utf-8'><title>Export HTML to Word Document with JavaScript</title></head><body>";
        var footer = "</body></html>";
        var sourceHTML = header+document.getElementById("source-html").innerHTML + document.getElementById("myChart").CDATA_SECTION_NODE+footer;
        
        var source = 'data:application/vnd.ms-word;charset=utf-8,' + encodeURIComponent(sourceHTML);
        var fileDownload = document.createElement("a");
        document.body.appendChild(fileDownload);
        fileDownload.href = source;
        fileDownload.download = 'Thống kê.doc';
        fileDownload.click();
        document.body.removeChild(fileDownload);
    }
   
});

