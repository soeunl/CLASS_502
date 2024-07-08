<%@ page contentType="text/html; charset=UTF-8" %>
<%
    int num1 = 100;
    int num2 = 200;
    int result = num1 + num2;
    out.write("result=" + result);
    String name = "이이름";

    out.write("<br>이름:" + name);
    print();
    // 주석1
%>

<br>
<%-- 클래스명 바로 아래쪽 - 멤버 변수 --%>
// 주석2
<%=num1%> + <%=num2%> = <%=result%>

<%!
    // 주석3
    String name = "안녕";
    String name2 = "하세요";
    void print() {
    System.out.println(name);
    System.out.println(name2);
    }
%>