<%@ page contentType="text/html; charset=UTF-8" %>
<%
    application.setAttribute("num", 100);
    request.setAttribute("num", 200);
    pageContext.setAttribute("num", 300);
    // 범위가 좁은게 우선 순위가 높다
    // 범위가 좁은게 화면에 먼저 나온다
    // 자바 코드는 무조건 큰따옴표를 써야 합니다

    pageContext.setAttribute("max-num", 1000);
    // 변수명 규칙과 맞지 않을 때 대괄호를 쓴다
%>

pageContext.num : ${pageScope.num}<br>
request.num : ${requestScope.num}<br>
application.num : ${applicationScope['num']}<br>

max-num : ${pageScope['max-num']}<br>

