import React from 'react';
import Greeting from './components/Greeting';

const Counter = () => {
  const num = 10;
  const visible = true;

  return (
    <>
      <Greeting name="김이름" />
      {/* 주석 */}
      <h1 style={{ backgroundColor: 'pink', color: 'red' }}>{num + 5}</h1>
      <button
        type="button" // 주석
      >
        -1
      </button>
      <button type="button">+1</button>
      {visible ? <h1>보일까?</h1> : ''}
      {/* 삼항조건 사용 */}
      {visible && <h1>안보일까?</h1>}
    </>
  );
};

export default Counter;
