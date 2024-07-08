import styled, { css } from 'styled-components';
import { BigButton } from '../components/commons/ButtonStyle';
import { useState, useCallback } from 'react';
import { FaCheckSquare, FaRegCheckSquare } from 'react-icons/fa';

const commonStyle = css`
  width: 100%;
`;

// 컴포넌트 앞글자는 대문자, 변수 앞글자는 소문자

const OuterBox = styled.div`
  ${commonStyle}
  /*
  position: fixed;
  height: 100%;
  */
  left: 0;
  top: 0;
  display: flex;
  align-items: center;
`;

const FormBox = styled.form`
  width: 420px;
  margin: 0 auto;

  h1 {
    text-align: center;
  }

  svg {
    color: #a566ff;
  }

  .chks {
    display: flex;
    justify-content: center;

    div {
      margin: 10px 0;
    }

    div + div {
      margin-left: 20px;
    }
  }
`;

const InputBox = styled.input`
  ${commonStyle}
  box-sizing: border-box;
  display: block;
  height: 45px;
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 0 10px;

  // 첫번째를 제외하고 나머지 요소들 모두 선택
  & + & {
    margin-top: 5px;
  }
`;

const LoginForm = () => {
  const [form, setForm] = useState({
    saveId: false,
    savePass: false,
  });

  const onSubmit = useCallback((e) => {
    e.preventDefault();
  }, []);

  const [selected, setSelected] = useState(false);

  return (
    <OuterBox>
      <FormBox autoComplete="off" onSubmit={onSubmit}>
        <h1>💜사용자 로그인💜</h1>
        <InputBox type="text" placeholder="아이디" />
        <InputBox type="password" placeholder="비밀번호" />
        <BigButton
          type="submit"
          selected={selected}
          onClick={() => setSelected(!selected)}
        >
          로그인
        </BigButton>
        <div className="chks">
          <div onClick={() => setForm({ ...form, saveId: !form.saveId })}>
            {form.saveId ? <FaCheckSquare /> : <FaRegCheckSquare />} 아이디 저장
          </div>
          <div onClick={() => setForm({ ...form, savePass: !form.savePass })}>
            {form.savePass ? <FaCheckSquare /> : <FaRegCheckSquare />} 비밀번호
            저장
          </div>
        </div>
      </FormBox>
    </OuterBox>
  );
};

export default LoginForm;
