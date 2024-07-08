import React, { Suspense, useState } from 'react';
import { Link, Navigate } from 'react-router-dom';
import styled from 'styled-components';
import loadable from '@loadable/component';
import { BigButton } from '../components/commons/ButtonStyle';
import { SmallButton } from '../components/commons/ButtonStyle2';

// const MessageBox = React.lazy(() => import('../components/commons/MessageBox'));
const MessageBox = loadable(() => import('../components/commons/MessageBox'), {
  fallback: <div>로딩중</div>,
});

const MessageBox2 = styled(MessageBox)`
  background: #faf4c0;
  width: 200px;
  height: 30px;
  text-align: center;
  font-size: 20px;
  font-weight: bold;
  margin: auto;
`;

const Home = () => {
  const [visible, setVisible] = useState(false);
  // const result = true;
  // if (result) {
  //   return <Navigate to="/about" replace={true} />;
  // }

  return (
    <div>
      <h1>HOME</h1>
      <BigButton type="button">
        <Link to="/">메인 페이지</Link>
      </BigButton>
      <BigButton type="button">
        <Link to="/about">회사 소개</Link>
        {/* <Suspense fallback={<div>로딩중...</div>}>
          {visible && <MessageBox>메세지!</MessageBox>}
        </Suspense> */}
      </BigButton>
      <BigButton type="button">
        <Link to="/login">로그인</Link>
      </BigButton>
      <SmallButton type="button" onClick={() => setVisible(!visible)}>
        버튼
      </SmallButton>
      {visible && (
        <MessageBox2>
          <MessageBox>💜안녕하세요💜</MessageBox>
        </MessageBox2>
      )}
    </div>
  );
};

export default React.memo(Home);
