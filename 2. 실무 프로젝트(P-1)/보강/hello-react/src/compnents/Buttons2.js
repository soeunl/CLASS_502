import React, { Component } from 'react';

class Buttons2 extends Component {
  render() {
    const {color, children} = this.props; // 비구조화할당을 한다!
    // 값을 분해해서 사용한다

    console.log('props', Object.getOwnPropertyDescriptor(this.props));
    console.log('props')

    const styles = {
      background: color,
    };

    return <button style={styles}>{children}</button>;
    // 소괄호 안쪽이 모두 JSX 문법이 들어간다.
  }
}

export default Buttons2;
