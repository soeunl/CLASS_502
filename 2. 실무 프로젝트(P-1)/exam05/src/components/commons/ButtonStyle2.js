import styled, { css } from 'styled-components';

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
