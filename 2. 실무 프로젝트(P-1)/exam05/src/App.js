import { Routes, Route } from 'react-router-dom';
import loadable from '@loadable/component';
import LoginForm from './pages/LoginForm';

// import Home from './pages/Home';
// import About from './pages/About';
// import Board from './pages/Board';
// import BoardList from './pages/BoardList';
// import MainLayout from './layouts/MainLayout';
// import NotFound from './pages/NotFound';

const Home = loadable(() => import('./pages/Home'));
const About = loadable(() => import('./pages/About'));
const BoardList = loadable(() => import('./pages/BoardList'));
const MainLayout = loadable(() => import('./layouts/MainLayout'));
const NotFound = loadable(() => import('./pages/NotFound'));

const App = () => {
  return (
    <Routes>
      <Route element={<MainLayout />}>
        <Route index element={<Home />} /> {/* / */}
        <Route path="login" element={<LoginForm />} />
        <Route path="about" element={<About />} /> {/* /about */}
        <Route path="board" element={<BoardList />} />
        <Route path="*" element={<NotFound />} />{' '}
        {/* 없는페이지 구성은 하단 쪽에 작성 */}
      </Route>
    </Routes>
  );
};

export default App;
