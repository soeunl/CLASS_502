const todos = {
  // 템플릿 가져오기
  getTpl() {
    const tplEl = document.getElementById("tpl");

    return tplEl.innerHTML;
  },
  // 스케줄 추가
  add(todo) {
    todo = todo ?? "";
    todo = todo.trim();
    if (!todo) {
      throw new Error("스케줄을 입력하세요");
    }

    let html = this.getTpl();
    html = html.replace(/#todo#/g, todo); // g = global을 뜻하며 모든 값을 교체해준다

    const domParser = new DOMParser();
    const dom = domParser.parseFromString(html, "text/html");
    const liEl = dom.querySelector("li");
    const schedulesEl = document.getElementById("schedules");
    schedulesEl.append(liEl);

    const buttonEl = liEl.querySelector("button");
    buttonEl.addEventListener("click", function () {
      if (confirm("정말 삭제하시겠습니까?")) {
        liEl.remove();
      }
    });
  },
};

window.addEventListener("DOMContentLoaded", function () {
  frmTodo.addEventListener("submit", function (e) {
    e.preventDefault(); // 페이지의 기본 동작을 막는다

    const todoEl = this.todo; // this는 이벤트가 발생한 요소이고 여기서는 frmTodo와 같다

    try {
      todos.add(todoEl.value);
      todoEl.value = "";
    } catch (err) {
      alert(err.message);
    }

    todoEl.focus();
  });
});
