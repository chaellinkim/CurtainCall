// const modalOverlay = document.querySelector(".modal_overlay");

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

  init();


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