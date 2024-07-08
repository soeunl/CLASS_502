import classNames from 'classnames';
import React from 'react';
import { Outlet, NavLink } from 'react-router-dom';
// 중첩된 레이아웃 안쪽 부분을 나타내줌(바뀌는 부분)
import { RiStarSmileFill, RiStarSmileLine } from 'react-icons/ri';
import { BigButton } from '../components/commons/ButtonStyle';

const MainLayout = () => {
  return (
    <>
      <header>
        <h1>
          <RiStarSmileFill />
          <RiStarSmileLine />
          <RiStarSmileFill />
          <RiStarSmileLine />
          <RiStarSmileFill />
          <RiStarSmileLine />
          <RiStarSmileFill />
          <RiStarSmileLine />
          <RiStarSmileFill />
          <RiStarSmileLine />
          <RiStarSmileFill />
          <RiStarSmileLine />
          <RiStarSmileFill />
          <RiStarSmileLine />
        </h1>
        <BigButton type="button">
          <NavLink
            to="/about"
            className={({ isActive }) => classNames('memu', { on: isActive })}
          >
            ABOUT
          </NavLink>
        </BigButton>
        <BigButton type="button">
          <NavLink
            to="/board"
            className={({ isActive }) => classNames('memu', { on: isActive })}
          >
            BOARD
          </NavLink>
        </BigButton>
      </header>
      <main>
        <Outlet />
      </main>
      <footer>
        <h1>
          <RiStarSmileFill />
          <RiStarSmileLine />
          <RiStarSmileFill />
          <RiStarSmileLine />
          <RiStarSmileFill />
          <RiStarSmileLine />
          <RiStarSmileFill />
          <RiStarSmileLine />
          <RiStarSmileFill />
          <RiStarSmileLine />
          <RiStarSmileFill />
          <RiStarSmileLine />
          <RiStarSmileFill />
          <RiStarSmileLine />
        </h1>
      </footer>
    </>
  );
};

export default React.memo(MainLayout);
