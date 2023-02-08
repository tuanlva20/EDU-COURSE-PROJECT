
var app = angular.module("app-ang", []);
app.controller("html-ctrl", function ($scope, $http) {
    console.log("haha");


    $scope.exportPDF = function(){
        const doc = new jsPDF();
        console.log(doc);
        doc.text("Hello world!", 10, 10);
        doc.save("a4.pdf");
    }
});