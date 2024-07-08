import { useState, useRef } from 'react';

const Counter = () => {
  let num = useRef(10);

  console.log('렌더링!', 'num', num);

  //   const data = useState(0);
  //   const number = data[0];
  //   const setNumber = data[1];
  const [number, setNumber] = useState(0);

  const plus = () => {
    num.current++;
    setNumber(number + 1);
  };
  const minus = () => setNumber(number - 1);

  return (
    <>
      <h1>{number}</h1>
      <button type="button" onClick={plus}>
        +1
      </button>
      <button type="button" onClick={minus}>
        -1
      </button>
    </>
  );
};

export default Counter;
