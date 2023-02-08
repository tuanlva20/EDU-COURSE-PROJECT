var app = angular.module("myapp", []);
app.controller("lesson", function ($scope, $http) {
  $scope.idbaihoc = document.getElementById("idBaihoc").value;
  $scope.listquiz = [];
  console.log($scope.idbaihoc);
  $http
    .get(`/rest/baihoc/listquiz/${$scope.idbaihoc}`)
    .then((result) => {
      console.log("haha");
      $scope.baihoc = result.data;

      $scope.listquiz = $scope.baihoc.quizs;
      $scope.progressSize = $scope.listquiz.length;

      for(var i =0;i< $scope.progressSize;i++){
        for(var j = 0;j<$scope.listquiz[i].phuongans.length;j++){
          $scope.shuffleArray($scope.listquiz[i].phuongans);
        }
      }
    })
    .catch((err) => {});

  var user = document.getElementById("username").value;
  
  
  $http.get("/rest/accounts/" + user).then((resp) => {
    $scope.account = resp.data;
    if($scope.account.heart>0){
      clearInterval($scope.resetIntervalHeart);
    }else{
      $scope.callTimeoutheart();
    }
  });



  $scope.callTimeoutheart= function(){
    $scope.resetIntervalHeart = setInterval(timeoutHeart,1000);
      function timeoutHeart(){
        $scope.now = new Date();
        $scope.dateFaild = new Date($scope.account.recoveryheart);
        $scope.nowtoSeconds = Math.floor($scope.now.getTime()/1000);
        $scope.dateFaildtoSeconds = Math.floor($scope.dateFaild.getTime()/1000);
        $scope.timerecoveryheart = 14000 -  ($scope.nowtoSeconds - $scope.dateFaildtoSeconds);
        $scope.toHHMMSS($scope.timerecoveryheart);

        document.getElementById("timeout").innerHTML = $scope.toHHMMSS($scope.timerecoveryheart);
        if($scope.timerecoveryheart <=0){
          clearInterval($scope.resetIntervalHeart);
          $scope.resetHeart();
          $(document).ready(function() {
            $("#closemodalheart").click();
          });
        }
      }
  }

  // $scope.clearTimeout= ()

  $scope.toHHMMSS = (secs) => {
    var sec_num = parseInt(secs, 10)
    var hours   = Math.floor(sec_num / 3600)
    var minutes = Math.floor(sec_num / 60) % 60
    var seconds = sec_num % 60

    return [hours,minutes,seconds]
        .map(v => v < 10 ? "0" + v : v)
        .filter((v,i) => v !== "00" || i > 0)
        .join(":")
  }

  $scope.shuffleArray =function(array){ // random arrays Phuong an
    for (let i = array.length - 1; i > 0; i--) {
      const j = Math.floor(Math.random() * (i + 1));
      [array[i], array[j]] = [array[j], array[i]];
    }
  }

  $scope.inputCheck = function(){
    var text =  $scope.listquiz[$scope.countQues].noidung
    console.log(text);
  }
  $scope.checkvideotrue = 0;
  $scope.countQues = 0;
  $scope.pointprogress = 0;


  $scope.allowDrop = function(ev) {
    var classname = ev.target.getAttribute("class");
    if(classname=="form-check-label"){
      return false
    }else{
      ev.preventDefault();
    }
  }

  $scope.drag = function(ev) {
    ev.dataTransfer.effectAllowed='move';
    ev.dataTransfer.setData("Text", ev.target.id);
  
  }

  $scope.drop = function(ev) {
    var data = ev.dataTransfer.getData("Text");
    ev.target.appendChild(document.getElementById(data));
  }
  $scope.selectType3 = function(event){
    // console.log(event.target.id);
    var drop =  document.getElementById("drop1");
    if(drop.textContent.length <=0){
      drop.appendChild(document.getElementById(`${event.target.id}`));
    }
    
  }
  $scope.dragType3 = function(event){
    var drop1 = document.getElementById(`${event.target.id}`);
    console.log(drop1)
    document.getElementById("drop1").removeChild(drop1);
    document.getElementById("drop2").appendChild(drop1);
  }



  $scope.cash = function(){
    if($scope.account.diem >= 30){
      $scope.account.diem = $scope.account.diem - 30;
      $scope.account.heart = 3;
      clearInterval($scope.resetIntervalHeart);
      $http.put(`/rest/quiz/update/${user}`, $scope.account).then((resp) => {
        $scope.account = resp.data;
      });
      $(document).ready(function() {
        $("#closemodalheart").click();
      });
      alert("Đổi thành công");
    }else{
      alert("Điểm của bạn không đủ để đổi");
    }
   
  }
  
  $scope.resetHeart = function(){
    $scope.account.heart = 3;
    $http.put(`/rest/quiz/update/${user}`, $scope.account).then((resp) => {
      $scope.account = resp.data;
    });
  }

  $scope.kiemtra = function () {


    if($scope.account.heart == 0){
        $scope.account.recoveryheart = new Date();
        $http.put(`/rest/quiz/update/${user}`, $scope.account).then((resp) => {
          $scope.account = resp.data;
        });


        $(document).ready(function() {
            $("#buyheart").click();
        });
    }else{
    $scope.dungsai = 0;
    $scope.quiz = $scope.listquiz[$scope.countQues];
    $scope.dapanchon = document.querySelector("input[name=dapan]:checked").value;
      for (var i = 0; i < $scope.quiz.phuongans.length; i++) {
        console.log($scope.quiz.phuongans[i].dungsai)
        if ($scope.quiz.phuongans[i].dungsai == true) {
          $scope.dapandung = $scope.quiz.phuongans[i];
        }
      }
      if($scope.dapanchon == $scope.dapandung.id){
        $scope.diemcong = $scope.diemcong + 1;
        $scope.lambai = {
          startat: new Date(),
          endat: new Date(),
          account: $scope.account,
          active: "Hoàn thành",
          phuongan: $scope.dapandung,
          quiz: $scope.quiz,
        };
        $http
          .post("/rest/lambai", $scope.lambai)
          .then((resp) => {
            $scope.dungsai = 1;
            document.getElementById("next").style.display = "unset";
            document.getElementById("kiemtra").style.display = "none";
          })
          .catch((error) => {
            alert("lỗi!");
            console.log("Error: " + error);
          });

          $scope.account.diem = $scope.account.diem + 10;
          $http.put(`/rest/quiz/update/${user}`, $scope.account).then((resp) => {
          $scope.account = resp.data;
        });
      }else{
        $scope.dungsai ==0;
        document.getElementById("answer-bad").classList.add("animate__animated","animate__heartBeat");
        document.getElementById("answer-bad").addEventListener('animationend', () => {
        document.getElementById("answer-bad").classList.remove("animate__animated","animate__heartBeat");
      });
        $scope.account.heart = $scope.account.heart - 1;
        if($scope.account.heart==0){
          $scope.account.recoveryheart = new Date();
          $scope.callTimeoutheart();
        }
        $http.put(`/rest/quiz/update/${user}`, $scope.account).then((resp) => {
          $scope.account = resp.data;
        });
      }
    }
     
  };

  $scope.checkQuizSuccess = function () {
    $scope.lambaibyuser ={};
    var quizid = $scope.listquiz[$scope.countQues].idquiz;
    $http.get("/rest/lambai/"+user+"/"+quizid).then((resp) => {
      $scope.lambaibyuser = resp.data;
      $scope.dapanchonn = document.getElementsByName("dapan");
      if($scope.lambaibyuser!=""){
        for(var i=0;i<$scope.dapanchonn.length;i++){
          if($scope.dapanchonn[i].value==$scope.lambaibyuser.phuongan.id){
              $scope.dapanchonn[i].checked =true;
              $scope.dungsai = 1;
              document.getElementById("kiemtra").style.display = "none";
              document.getElementById("next").style.display = "unset";
              return;
          }
        }
      }else{
        $scope.dungsai = 2;
        document.getElementById("kiemtra").style.display = "unset";
        document.getElementById("next").style.display = "none";
      }
    });
  };
  $scope.next = function () {
    document.getElementById("kiemtra").style.display = "unset";
    if ($scope.countQues < $scope.progressSize) {
      $scope.countQues++;
      $scope.pointprogress++;
      
      $scope.nowProgress($scope.pointprogress);
      
      if ($scope.countQues == $scope.listquiz.length) {
        document.getElementById("succes-question").style.display = "unset";
        document.getElementById("success-lesson").style.display = "unset";
        document.getElementById("next").style.display = "none";
        // document.getElementById("prev").style.display = "none";
        document.getElementById("kiemtra").style.display = "none";
      }
      $scope.checkQuizSuccess();
      document.getElementById("next").style.display = "none";
    }
  };

  $scope.nowProgress = function(point){
    
    $scope.quiz_step =
        (100 / ($scope.progressSize + 1)) * point;
      document.getElementById("dot-quiz-starting").style.left =
        $scope.quiz_step + "%";
    document.getElementById("answer-bad").classList.remove("animate__animated","animate__heartBeat");
  }
  // 2 button teen giong nhau
  $scope.back = function () {
    if ($scope.countQues >= 0) {
      $scope.countQues--;
      $scope.pointprogress--;
      console.log($scope.countQues,$scope.pointprogress)
    }
    if ($scope.countQues < 0) {
      document.getElementById("myVideo").style.display = "unset";
      $scope.checkvideotrue = 0;
      document.getElementById("next").style.display = "none";
      document.getElementById("prev").style.display = "none";
      document.getElementById("nextvideo").style.display = "unset";
      document.getElementById("kiemtra").style.display = "none";
    }
    
    if ($scope.countQues <= $scope.listquiz.length) {
      console.log($scope.countQues+"vo"+$scope.progressSize);
      document.getElementById("success-lesson").style.display = "none";
      document.getElementById("succes-question").style.display = "none";
      // document.getElementById("next").style.display = "unset";
    }

    $scope.checkQuizSuccess();
    $scope.nowProgress($scope.pointprogress);
  };

  // document.getElementById("question-quiz").style.display="none";
  $scope.nextvideo = function () {
    
    $scope.pointprogress++;
    $scope.nowProgress($scope.pointprogress);
    document.getElementById("myVideo").style.display = "none";
    document.getElementById("myVideo").pause();
    document.getElementById("prev").style.display = "unset";
    $scope.checkvideotrue = 1;
    // document.getElementById("question-quiz").style.display="unset"
    document.getElementById("nextvideo").style.display = "none";
    document.getElementById("kiemtra").style.display = "unset";
    $scope.countQues = 0;
    $scope.checkQuizSuccess();
  };

  $scope.comment = {
    datecomment : new Date(),
    noidung : "",
    account : {username:user},
    baihoc : null,
    listcomments :[],
    clearComment(){
      document.getElementById("comments").value = "";
    },
    getComment(){
      $http.get(`/rest/thaoluan/baihoc/${$scope.idbaihoc}`).then(resp =>{
          this.listcomments = resp.data;
      })
    },
    postComment(baihocid){
      document.getElementById("post-comment").disabled = true;
      this.baihoc = {baihocid : baihocid}
      this.noidung = document.getElementById("comments").value;
      var comment = angular.copy(this);
      var myTimeout = setTimeout(waitingpost, 2500);
      function waitingpost(){
        $http.post(`/rest/thaoluan/${user}/${baihocid}`,comment).then(resp =>{
          document.getElementById("post-comment").disabled = false;
          $scope.comment.clearComment();
          $scope.comment.getComment();
        })
      }
     
    },
    reply(){
       document.getElementById("comments").value = "";
    }
  }
  $scope.comment.getComment()

  $scope.shortcuts_quiz = function(index){
    document.getElementById("kiemtra").style.display = "unset";
    $scope.countQues = index;
    $scope.pointprogress = index+1;
    $scope.nowProgress($scope.pointprogress);
        if ($scope.countQues < 0) {
          document.getElementById("myVideo").style.display = "unset";
          $scope.checkvideotrue = 0;
          document.getElementById("next").style.display = "none";
          document.getElementById("prev").style.display = "none";
          document.getElementById("nextvideo").style.display = "unset";
          document.getElementById("kiemtra").style.display = "none";
        }
    $scope.checkQuizSuccess();
  }
  $scope.shortcuts_quizmax = function(max){
    $scope.countQues = max;
    $scope.pointprogress = max+1;
    $scope.nowProgress($scope.pointprogress);
        if ($scope.countQues == $scope.listquiz.length) {
          document.getElementById("succes-question").style.display = "unset";
          document.getElementById("success-lesson").style.display = "unset";
          document.getElementById("next").style.display = "none";
          // document.getElementById("prev").style.display = "none";
          document.getElementById("kiemtra").style.display = "none";
        }
        if ($scope.countQues > $scope.listquiz.length) {
          document.getElementById("succes-question").style.display = "none";
          document.getElementById("success-lesson").style.display = "none";
        }
        $scope.checkQuizSuccess();
    
  }


  $scope.checkSkipVideo = function () {
    if ($scope.countQues == 0) {
      var time = 0;
      var prevTime = 0;
      document
        .querySelector("video")
        .addEventListener("timeupdate", function () {
          const skipThreshold = 2;
          if (this.currentTime - prevTime > skipThreshold) {
            prevTime = this.currentTime;
            document.getElementById("nextvideo").disabled = true;
            const times = this.currentTime;
            time = times;
          } else {
            prevTime = this.currentTime;
            if (prevTime - time >= (this.duration / 100) * 0) {
              document.getElementById("nextvideo").disabled = false;
            } else {
              if (prevTime - time < 0) {
                const times = this.currentTime;
                time = times;
                document.getElementById("nextvideo").disabled = true;
              }
            }
          }
        });
    }
  };

  $scope.checkSkipVideo();
});
