const todo = {
  data: [],
   init() {
    //처음 돔 로딩 완료 후 실행될 메서드 - localStorage 저장된 데이터 조회
    // li 태그 완료
   }

  // 스케줄 추가
  add() {
    const subject = frmRegist.subject.value;
    // 입력한 값은 value에 들어간다
    const liEl = this.getItem(subject);
    const itemsEl = document.querySelector(".items");
    itemsEl.appendChild(liEl);

    this.data.push(subjuct);
    this.save();
  },

  save() {
    localStorage.setItem("todos", JSON.stringify(this.data));
  },

  getItem(subject) {
    const liEl = document.createElement("li");
    const textEl = document.createTextNode(subject);
    const buttonEl = document.createElement("button");
    buttonEl.type = "button";
    buttonEl.appendChild(document.createTextNode("삭제"));

    liEl.appendChild(textEl);
    liEl.appendChild(buttonEl);

    return liEl;
  },
};

window.addEventListener("DOMContentLoaded", function ()
     { todo.init();
  // 돔이 완성되고 난 뒤
  frmRegist.addEventListener("submit", function (e) {
    e.preventDefault();

    todo.add();
  });
}); // 값 입력 칸 만들기
