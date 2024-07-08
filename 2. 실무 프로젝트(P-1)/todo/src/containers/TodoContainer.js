import { useState } from 'react';
import AddTodo from '../components/AddTodo';
import TodoList from '../components/TodoList';

const initialValue = [
  { id: 1, title: '할일1', done: false },
  { id: 2, title: '할일2', done: true },
  { id: 3, title: '할일3', done: false },
];

const TodoContainer = () => {
  const [items, setIiems] = useState(initialValue); // 할일 목록
  const [todo, setTodo] = useState(''); // 목록을 쓰는 칸

  return (
    <>
      <AddTodo />
      <TodoList todos={items} />
    </>
  );
};

export default TodoContainer;
