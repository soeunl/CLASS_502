import Buttons from "./compnents/Buttons";

function App() {
  const name = '이소은';

  return (
    // 이 안에 출력할 내용을 정의한다.
    // 안에 출력할 내용이 하나일때는 소괄호 생략가능하지만,
    // 하나 이상일때는 소괄호를 생략할 수 없다.
    <>
      {name && <h1>안녕하세요👧, {name}</h1>}
      <h2>반갑습니다🧑</h2>
      <Buttons />
    </>
  );
}

export default App;
