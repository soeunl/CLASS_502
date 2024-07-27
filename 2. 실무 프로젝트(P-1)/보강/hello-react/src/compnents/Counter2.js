import React, { useState } from 'react';

const Counter2 = () => {
  const [data, setData] = useState({
    number: 0,
    name: '이이름',
  });

  const { number, name } = data;

  const decrease = () => {
    // data.number--;
    // setData(data);
    // 주소를 바꿀 수 있는 새로운 객체로 만들어야 한다.
    // 기존 객체는 두고 새로운 객체로 만들어야 함!
    setData({ ...data, number: number - 1 });
    // 전개연산자
    // 매개변수에 투입할 때 일괄적으로 할 수 있다.
    // data 객체의 모든 속성을 새 객체로 복사
  };

  const increase = () => {
    setData({ ...data, number: number + 1 });
  };

  console.log('호출', data);

  return (
    <>
      <h1>{number}</h1>
      <h2>{name}</h2>
      <button type="button" onClick={decrease}>
        {' '}
        -1
      </button>
      <button type="button" onClick={increase}>
        +1
      </button>
    </>
  );
};

export default Counter2;
