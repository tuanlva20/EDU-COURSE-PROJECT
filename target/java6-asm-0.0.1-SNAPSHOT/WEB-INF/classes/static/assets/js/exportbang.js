var app = angular.module("bang", []);
app.controller("export", function ($scope, $http) {
  const username = document.getElementById("username").value;
  const productid = document.getElementById("idproduct").value;
  $scope.dataPDF = {};

  $scope.exportPDF = function () {
    $http
      .get(`/rest/export/${productid}/${username}`)
      .then((resp) => {
        $scope.dataPDF = resp.data;
        if ($scope.dataPDF.username!="") {
          document.getElementById("name").innerHTML = $scope.dataPDF.username;
          document.getElementById("khoahoc").innerHTML =$scope.dataPDF.tenkhoahoc;
          document.getElementById("t6_1").innerHTML =$scope.dataPDF.ngayhoanthanh;
          var bang = document.getElementById("p1");
          html2canvas(bang).then((canvas) => {
            const imgData = canvas.toDataURL("image/png", 1);
            const pdf = new jsPDF({
              orientation: "landscape",
              unit: "px",
            });
            const imgProps = pdf.getImageProperties(imgData);
            const pdfWidth = pdf.internal.pageSize.getWidth();
            const pdfHeight = (imgProps.height * pdfWidth) / imgProps.width;
            pdf.addImage(imgData, "PNG", 0, 0, pdfWidth, pdfHeight);
            pdf.save("bang.pdf");
          });
        } else {
        }
      })
      .catch((err) => {
        console.log(err);
      });
  };
  $scope.exportPDF();
});
