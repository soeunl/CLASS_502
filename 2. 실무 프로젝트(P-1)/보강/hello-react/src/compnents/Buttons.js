import React from 'react';

// 함수형 컴포넌트는 여기있는 모든게 다 실행된다.
function Buttons({color, children}) { // 속성값은 여기의 매개변수로 넘겨준다.
  // 설정한 속성값이 매개변수로 들어옴
  // 여기에 props 대신 직접 속성값을 입력하면 된다.

  // const {color, children} = props; // 비구조화 할당
  
  const styles = {
    background: color,
  };

  const Click = () => {
    window.alert('함수형 컴포넌트 버튼이 클릭되었습니다!');
  };

  return <button style={styles} onClick={Click}>{children}</button>;
}

export default Buttons;
