const todos = {
  // 스케줄 추가
  add(todo) {
    todo = todo ?? ""; // null 병합 연산자 (null이나 undefined 일때 대체된다)
    todo = todo.trim();

    if (!todo) {
      throw new Error("스케줄을 입력하세요");
    }

    const button = document.createElement("button");
    button.append("삭제");

    const liEl = document.createElement("li");
    liEl.append(todo, button);

    const schedulesEl = document.getElementById("schedules");
    schedulesEl.append(liEl); // 부모 요소를 선택하지 않고 바로 추가 가능

    // 스케줄 삭제
    button.addEventListener("click", function () {
      if (confirm("정말 삭제하시겠습니까?")) {
        liEl.remove(); // 부모 요소를 선택하지 않고 바로 삭제 가능
      }
    });
  },
};

window.addEventListener("DOMContentLoaded", function () {
  // 돔이 로드된 후 폼이 제출될 때 기본 동작을 막고 지정한 작업 수행
  // DOMContentLoaded 이후에는 문제 없이 선택이 가능하다
  frmRegist.addEventListener("submit", function (e) {
    e.preventDefault(); // 기본 동작을 막는다.

    const todoEl = this.todo;

    try {
      todos.add(this.todo.value); // 스케줄 등록

      todoEl.value = "";
    } catch (e) {
      alert(e.message);
    }
    this.todo.focus();
  });
});

// console.log(this.todo.value);
// this는 이벤트가 발생한 form 그 자체가 된다.

const todo = this.todo.value.trim();
// this.todo: 폼 요소 또는 입력 필드
// .value: 해당 입력 필드에 입력된 값
// .trim: 입력된 문자열의 앞뒤에 있는 불필요한 공백을 제거
// 입력한 내용에서 앞뒤 공백이 제거된 상태의 값을 담음

const liEl = document.createElement("li"); // 새로운 <li> 요소를 생성하여 liEl 변수에 저장

liEl.appendChild(document.createTextNode(todo));
// todo라는 변수를 텍스트로 변환하여 새로운 텍스트 노드를 생성함
// 생성된 텍스트 노드가 <li> 요소의 자식으로 추가
// todo에 입력한 값으로 텍스트 노드를 생성한 다음,
// 그것을 liEl 요소의 자식으로 추가하는 작업을 수행 (새로운 <li> 요소를 생성)

const schedulesEl = document.getElementById("schedules");
// DOM에서 ID가 "schedules"인 요소를 찾아 schedulesEl 변수에 저장

schedulesEl.appendChild(liEl);
// ID가 "schedules"인 요소 안에 liEl를 추가
