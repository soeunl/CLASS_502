import React from 'react';
import styled from 'styled-components';

const box = styled.div`

`;

const MessageBox = ({ className, children }) => {
  return <div className={className}>{children}</div>;
};

export default React.memo(MessageBox);
