  const cancleModal = () => {
    document.querySelector(".modal_overlay").classList.add("hidden");
    document.body.classList.remove('modal-open');
  };

  const onClicked = () => {
    document.querySelector(".modal_overlay").classList.remove("hidden");
    document.body.classList.add('modal-open');
  };

  function init() {
    const modalButton = document.querySelector(".modal_button");
    const modalCancleButton = document.querySelector(".modal_cancle");
    if (modalButton) {
      modalButton.addEventListener("click", onClicked);
    }
  
    if (modalCancleButton) {
      modalCancleButton.addEventListener("click", cancleModal);
    }
  }


  function test(){
    var p1 = document.getElementById('password1').value;
    var p2 = document.getElementById('password2').value;

    if( p1 === p2 ) {
      document.getElementById('check').innerHTML='비밀번호가 일치합니다.'
      document.getElementById('check').style.color="#1a43fb";
    } else{
      document.getElementById('check').innerHTML='비밀번호가 일치하지 않습니다.';
      document.getElementById('check').style.color='#ff2121';
    }
  }

  function del(){
    document.getElementById('check').innerHTML='';
  }

  function passwordValid(){

   	var password = document.getElementById('password1').value;

   	var pattern = /^(?=.*[a-zA-Z])(?=.*[!@?#();\.])(?=.*[0-9]).{7,15}$/
   	
   	   if(password === ''){
   		   document.getElementById('pw_check').innerHTML='비밀번호는 필수정보입니다.'
   		   document.getElementById('pw_check').style.color="red";
   	   }else{
   		   if(pattern.test(password)){
   			   document.getElementById('pw_check').innerHTML='';
   		   }else{
   			   document.getElementById('pw_check').innerHTML= '7~15자의 영문 대 소문자, 숫자, 특수문자(!,@,?,#,(,),;,.)를 사용하세요';
   			   document.getElementById('pw_check').style.color="red";
   		   }
   	   }
   }
   
   	//현재 비밀번호 확인
	$(document).ready(function() {

		$("#password").blur(function() {
			
		 var password = document.getElementById('password').value;
         
         //아이디를 입력하지 않았을 경우
         if(password === ""){
            document.getElementById('check').innerHTML="보안을 위해 현재 비밀번호를 입력해주세요.";
            document.getElementById('check').style.color="red";
            document.getElementById('password').focus();
            return;
         }else{

         $.ajax({
            url : '/mypage/pwcheck',
            type : 'post',
            data : {
               'password' : password
            },
            dataType : 'json',
            success : function(result) {
               if (result === 0) {
                  document.getElementById('check').innerHTML="";
               } else {
                  document.getElementById('check').innerHTML="현재 비밀번호가 일치하지 않습니다.";
                  document.getElementById('check').style.color="red";
               }
            },
            error : function(req, status) {
               alert(status);
            }
         })
         }
		});

	});
	
	//회원정보 수정 전 비밀번호 확인
	function pwCheck(){
	
		var password = document.getElementById('ckpassword').value;
         
         console.log(password);
         
         //아이디를 입력하지 않았을 경우
         if(password === ""){
            document.getElementById('pwcheck').innerHTML="보안을 위해 현재 비밀번호를 입력해주세요.";
            document.getElementById('pwcheck').style.color="red";
            document.getElementById('password').focus();
            return;
         }else{

         $.ajax({
            url : '/mypage/pwcheck',
            type : 'post',
            data : {
               'password' : password
            },
            dataType : 'json',
            success : function(result) {
               if (result === 0) {
                  document.getElementById('pwcheck').innerHTML="";
                  window.location.href="/mypage?tabId=tab4";
               } else {
                  document.getElementById('pwcheck').innerHTML="현재 비밀번호가 일치하지 않습니다.";
                  document.getElementById('pwcheck').style.color="red";
               }
            },
            error : function(req, status) {
               alert(status);
            }
		});
		}
		}
		
		
	//회원탈퇴
	function checkdelete() {

		$.ajax({
			url : '/mypage/withdrawal',
			type : 'post',
			success: function(response) {
			
					if(response === "true"){
						alert("감사합니다");
						window.location.href="/";
					}else{
						alert("다시 한 번 시도해주세요");
					}
			    },
			    error: function(xhr, status, error) {
			      console.log('요청이 실패했습니다.');
			      alert('에러 메시지: ' + error);
			      // 필요한 작업 수행
			    }
			});
	}
	
	//탈퇴 전 비밀번호 확인
	function deleteCheck(){
	
		var password = document.getElementById('deletepassword').value;
         
         console.log(password);
         
         //아이디를 입력하지 않았을 경우
         if(password === " "){
            document.getElementById('deletecheck').innerHTML="보안을 위해 현재 비밀번호를 입력해주세요.";
            document.getElementById('deletecheck').style.color="red";
            document.getElementById('deletepassword').focus();
            return;
         }else{

         $.ajax({
            url : '/mypage/pwcheck',
            type : 'post',
            data : {
               'password' : password
            },
            dataType : 'json',
            success : function(result) {
               if (result === 0) {
                  document.getElementById('deletecheck').innerHTML="일치";
                  document.getElementById('deletecheck').style.color="red";
               } else {
                  document.getElementById('deletecheck').innerHTML="현재 비밀번호가 일치하지 않습니다.";
                  document.getElementById('deletecheck').style.color="red";
               }
            },
            error : function(req, status) {
               alert(status);
            }
		});
		}
		}
	
