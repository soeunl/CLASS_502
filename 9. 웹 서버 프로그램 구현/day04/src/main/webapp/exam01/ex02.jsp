<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="java.time.LocalDateTime" %>

<%
    List<String> names = new ArrayList<>();
    names.add("이름1");
    names.add("이름2");
    out.write(names.toString());

    LocalDateTime now = LocalDateTime.now();
    out.write("<br>" + now.toString());
%>