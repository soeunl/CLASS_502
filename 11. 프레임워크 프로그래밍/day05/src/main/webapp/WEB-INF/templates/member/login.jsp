<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    <h2>❤로그인❤</h2>
<h2>${commonValue}</h2>
    <form>
        <dl>
            <dt>이메일</dt>
            <dd>
                <input type="text" name="email">
            </dd>
        </dl>
        <dl>
            <dt>비밀번호</dt>
            <dd>
                <input type="password" name="password">
            </dd>
        </dl>
        <div>
            <input type="checkbox" name="saveEmail" value="true" id="saveEmail">
            <label for="saveEmail">❤이메일 기억하기❤</label>
        </div>
        <button type="submit">♡로그인♡</button>
    </form>