import { useState } from 'react';

const Profile = () => {
  const [data, setData] = useState({
    // useState는 상태값을 변경할 때 사용
    // 0번째는 상태값
    // 1번째는 상태를 변경할 수 있는 함수
    // setData를 이용해지만 값을 변경할 수 있음
    name: '이이름',
    age: 40,
  });

  const changeProfile = () => {
    // data.name = '김이름';
    // data.age = 30;
    // setData({ name: '김이름', age: 30 });
    // setData({ ...data, name: '김이름' });

    setData((state) => {
      console.log('이전 상태값 : ', state);

      return { ...state, name: '김이름' };
    });
  };

  const { name, age } = data;
  return (
    <>
      <h1>{name}</h1>
      <h2>{age}</h2>
      <button type="button" onClick={changeProfile}>
        변경
      </button>
    </>
  );
};

export default Profile;
