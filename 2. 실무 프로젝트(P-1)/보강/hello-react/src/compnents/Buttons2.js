import React, { Component } from 'react';
import ProTypes from 'prop-types';

// 클래스형 컴포넌트는 render 함수만 호출된다
class Buttons2 extends Component {
  static ProTypes = {
    color: ProTypes.string.isRequired,
  };

  render() {
    const { color, children: c } = this.props; // 비구조화할당을 한다!
    // 값을 분해해서 사용한다

    // console.log('props', Object.getOwnPropertyDescriptor(this.props));
    // console.log('props');

    const styles = {
      background: color ?? 'orange',
    };

    const Click = () => {
      window.alert('클래스형 컴포넌트 버튼이 클릭되었습니다!');
    };

    return <button style={styles} onClick={Click}>{c}</button>;
    // 소괄호 안쪽이 모두 JSX 문법이 들어간다.
  }
}

// Buttons2.defaultProps = {
//   color: 'black',
// };

// Buttons2.ProTypes = {
//   color: ProTypes.string,
//   isRequired, // 문자열, 필수항목
// };
// 참고로만 알아두세요

export default Buttons2;
