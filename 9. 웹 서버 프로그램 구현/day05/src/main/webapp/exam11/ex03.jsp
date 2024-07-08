<%@ page contentType="text/html; charset=UTF-8" %>
<%
    Cookie cookie = new Cookie("key2", "value2");
    cookie.setMaxAge(60 * 60 * 24 * 7);
    // 계산해서 넣기보다는 곱하기로 해서 넣는 것이 관례
%>