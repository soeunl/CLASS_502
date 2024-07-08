import { useEffect } from 'react';
import { MdOutlineAdd } from 'react-icons/md';

const AddTodo = ({ onSubmit, onChange, todo, message }) => {
  useEffect(() => {
    console.log('todo, message 값 변경 - 렌더링 후');
  }, [todo, message]); // [...] 변화 감지 기준

  useEffect(() => {
    // componentDidMount(), compoenetDidUpdate() - todo값의 변화에 대한 렌더링
    console.log('마운트시 한번만 호출'); // componentDidMount
    //DOMContentLoaded 비슷 (동일 X)
  }, []);

  return (
    <form onSubmit={onSubmit} autoComplete="off">
      {/* 자동완성끄기 */}
      <input type="text" value={todo ?? ''} onChange={onChange} />
      <button type="submit">
        <MdOutlineAdd />
      </button>
      {message && <div>{message}</div>}
    </form>
  );
};

export default AddTodo;
