const App = () => {
  const name = "js";

  const style = {
    backgroundColor : "orange",
    color : "white",
    height : "100px",
  };  // 스타일속성은 객체형태로 정의하며 -는 들어갈 수 없다. 카멜표기법으로 표기. 꼭 {}가 있어야만 인식할 수 있다.

  return (
    <>
      <div style={style}>첫번째 컴포넌트!</div>
      <div style={{color : "blue"}}>신나는, {name && name} 공부</div>
      {name === 'React' && <h1>아! 재미있다!</h1>} {/* === 를 쓰는게 권장사항 */}
      <input type="text"/>
    </>
  );
};

export default App;