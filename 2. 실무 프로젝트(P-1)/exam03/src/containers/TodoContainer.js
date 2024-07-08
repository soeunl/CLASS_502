import { useState, useRef, useCallback } from 'react';
import AddTodo from '../components/AddTodo'; //상위경로로 이동하여 불러오기
import TodoList from '../components/Todolist';
import { produce } from 'immer';

const initialValue = [
  { id: 1, title: '할일1', done: true },
  { id: 2, title: '할일2', done: false },
  { id: 3, title: '할일3', done: false },
];

let submitFunc;

const TodoContainer = () => {
  // 업데이트 시, 매번 호출
  const [items, setItems] = useState(initialValue);
  const [todo, setTodo] = useState('');
  const [message, setMessage] = useState('');

  let id = useRef(4); // 할일 id

  // 할일 등록 처리
  const onSubmit = useCallback(
    // 매번 useCallback으로 감싸야 한다
    (e) => {
      e.preventDefault();

      if (!todo.trim()) {
        setMessage('할일을 입력하세요');
        return; // return undefined
      } // useCallback으로 인해 함수가 이 형태로 고정된다

      // setItems((prevItems) => {
      //   return prevItems.concat({
      //     id: id.current,
      //     title: todo.trim(),
      //     done: false,
      //   }); // 주소가 달라져야 하기 때문에 새로운 배열을 반환하는 concat()을 사용해야 함
      // });

      setItems(
        produce((draft) => {
          draft.push({
            id: id.current,
            title: todo.trim(),
            done: false,
          });
        }),
      );

      id.current++;

      setTodo('');
      setMessage('');
    },
    [todo], // 변화 감지의 기준
  );

  console.log('같은 함수 : ? ', submitFunc === onSubmit);
  submitFunc = onSubmit;

  // 할일 입력시 todo 상태값 변경
  const onChange = useCallback((e) => setTodo(e.currentTarget.value), []);

  // 할일 목록 완료 여부 토글(true -> false, false -> true)
  const onToggle = useCallback((id) => {
    /*
      const newItems = items.map((item) =>
        item.id === id ? { ...item, done: !item.done } : item,
      );
      setItems(newItems);
    */

    //   setItems((prevItems) => {
    //     // setItems에서 prevItems라는 현재 상태값을 매개변수로 넣어서 호출
    //     return prevItems.map((item) =>
    //       item.id === id ? { ...item, done: !items.done } : item,
    //     );
    //   });
    // }, []); // 내부에서 호출될때마다 현재 상태값이 넘어온다

    setItems(
      produce((draft) =>
        draft.forEach((item) => item.id === id && (item.done = !item.done)),
      ),
    );
  }, []);

  // 할일 목록 제거
  const onRemove = useCallback((id) => {
    /*
      const newItems = items.filter((item) => item.id !== id);
      setItems(newItems);
      */
    setItems((prevItems) => {
      return prevItems.filter((item) => item.id !== id);
    });
  }, []);

  return (
    <>
      <AddTodo
        onSubmit={onSubmit}
        onChange={onChange}
        todo={todo}
        message={message}
      />
      <TodoList items={items} onToggle={onToggle} onRemove={onRemove} />
    </>
  );
};

export default TodoContainer;
