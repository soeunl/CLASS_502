import React from 'react';

const Greeting = (props) => {
  console.log('props', props);
  console.log(Object.getOwnPropertyDescriptor(props));
  console.log('isExtensible', Object.isExtensible(props));
  console.log('isFrozen', Object.isFrozen(props));
  return (
    <div>
      <h1>이소은님, 안녕하세요^^</h1>
    </div>
  );
};

export default Greeting;
