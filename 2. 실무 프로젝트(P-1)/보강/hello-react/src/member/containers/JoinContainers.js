// 컨테이커 컴포넌트
// 값을 가공하고 처리하고 이벤트 처리 같은 것을 한다
// props를 넘겨준다

import React, {useState} from 'react';
import JoinForm from '../components/JoinForm';

const JoinContainer = () => {

    const [form, setForm] = useState({});
    console.log('container', Object.getOwnPropertyDescriptors(form));

    return <JoinForm form={form} />;
};

export default JoinContainer;
