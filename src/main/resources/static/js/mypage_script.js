console.log("start");
const modalButtons = document.querySelectorAll('.modal_button');
const modalOverlays = document.querySelectorAll('.modal_overlay');

// 이벤트 위임을 통해 각 버튼에 대한 클릭 이벤트 처리
modalButtons.forEach((button, index) => {
  button.addEventListener('click', () => {
    // 해당 버튼에 대한 모달 표시 로직
    modalOverlays[index].classList.remove('hidden');
    document.body.classList.add('modal-open');
    console.log("go");
  });
});

// 모달 취소 버튼에 대한 클릭 이벤트 처리
const modalCancelButtons = document.querySelectorAll('.modal_cancle');

modalCancelButtons.forEach((cancelButton) => {
  cancelButton.addEventListener('click', () => {
    // 모달 닫기 로직
    cancelButton.closest('.modal_overlay').classList.add('hidden');
    document.body.classList.remove('modal-open');
  });
});





