var app = angular.module("app-ang", []);
app.controller("html-ctrl", function ($scope, $http) {
  console.log("Java")
  const user = document.getElementById("user").value;
  const idbaihoc = document.getElementById("idbaihoc").value;
  $scope.test = {};
  $scope.account={};
  $scope.baihoc = {};
  $scope.loadTestCodeByUser = {
      loadCode(){
        $http.get(`/rest/test/${user}/${idbaihoc}`).then((resp) =>{
            $scope.test = resp.data;
        }).catch(error =>{
          console.log(error);
        })
      }
      ,
      savecode(){
        var codes = document.getElementById("textareaCode").value;
        if($scope.test!=''){
          $scope.test = {
            testid : $scope.test.testid,
            codes: codes.trim(),
            account : $scope.test.account,
            baihoc : $scope.test.baihoc
          }
          $http.put(`/rest/test/run/${idbaihoc}/${user}`,$scope.test).then((resp)=>{
            $scope.test = resp.data;
          }).catch(error =>{
            alert(error);
          })
        }else{
            $scope.test = {
            codes: codes.trim(),
            account : {username:user},
            baihoc : {baihocid:idbaihoc}
          }
          console.log($scope.test);
          $http.post(`/rest/test/run`,$scope.test).then((resp)=>{
            $scope.test = resp.data;
          }).catch(error =>{
            alert(error);
          })
        }
      }
  }
  $scope.loadTestCodeByUser.loadCode();
});
