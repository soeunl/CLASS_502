import styled, { css } from 'styled-components';

export const BigButton = styled.button`
  box-sizing: border-box;
  width: 100%;
  height: 45px;
  border: 0;
  background: ${({ bgcolor }) => bgcolor ?? '#a566ff'};
  border-radius: 5px;
  margin-top: 5px;
  font-size: 1.2rem;
  font-weight: bold;
  color: white;

  ${({ selected, bgcolor }) =>
    selected &&
    css`
      border: 3px solid ${bgcolor ?? '#a566ff'};
      background: #fff;
      color: ${bgcolor ?? '#a566ff'};
    `}
`;
// 리액트 스타일은 비구조화할당
// background-color: ${({ bgcolor }) => bgcolor ?? '#a566ff'};



export const SmallButton = styled.button`
  box-sizing: border-box;
  width: 50%;
  height: 45px;
  border: 0;
  background: ${({ bgcolor }) => bgcolor ?? '#a566ff'};
  border-radius: 5px;
  margin: 5px 25%;
  font-size: 1.2rem;
  font-weight: bold;
  color: #551a8b;
`;
