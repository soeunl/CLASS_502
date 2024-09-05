import React from 'react';

const Greeting = ({ name, age }) => {
  age = age ?? 20;
  // console.log('props', props);
  // console.log(Object.getOwnPropertyDescriptor(props));
  // console.log('isExtensible', Object.isExtensible(props));
  // console.log('isFrozen', Object.isFrozen(props));
  return (
    <div>
      <h1>{name}님, 안녕하세요^^</h1>
      <h1>나이는: {age} 입니다.</h1>
    </div>
  );
};

/*
Greeting.defaultProps = {
  age: 20,
}
  이제는 이렇게 쓰기 보다는  age = age ?? 20; 이렇게 쓴다!
  default값 설정
*/

export default Greeting;
