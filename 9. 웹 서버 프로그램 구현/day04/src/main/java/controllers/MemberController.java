package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String url = req.getRequestURI();
//        System.out.println(url);
//        이 방법 대신 아래에 메서드를 만들어서 사용하겠음
        
        String mode = getMode(req);
        if (mode.equals("join")) {
            joinForm(req, resp);
        } else if (mode.equals("login")) {
            loginform(req, resp);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mode = getMode(req);
        if (mode.equals("join")) {
            joinProcess(req, resp);
        } else if (mode.equals("login")) {
            loginProcess(req, resp);
        }
    }
    private String getMode(HttpServletRequest req) {
        String url = req.getRequestURI();
        String[] urls = url.split("/"); // / 뒤에 있는 문자열로 분리해서 쓰겠다는 의미
        String mode = urls.length > 0 ? urls[urls.length - 1] : ""; // null로 인한 오류발생을 방지하기 위해 뒤에 ""을 넣었음

        return mode;
    }
    private void joinForm(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 회원가입 양식을 보여줌
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<h1>회원가입</h1>");
    }
    private void loginform(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 로그인 양식을 보여줌
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<h1>로그인</h1>");
    }
    private void joinProcess(HttpServletRequest req, HttpServletResponse resp) {
        // 회원 가입 처리
    }
    private void loginProcess(HttpServletRequest req, HttpServletResponse resp) {
        // 로그인 처리
    }
}
