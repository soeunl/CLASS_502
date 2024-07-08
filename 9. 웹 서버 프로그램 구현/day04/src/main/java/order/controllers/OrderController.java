package order.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/order")
public class OrderController extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 서비스 처리.. Servletd을 통해서 처리함
        req.setAttribute("message", "처리완료");
        
        // Servlet이 컨트롤러가 되고, JSP가 뷰가 되어 역할을 분담함

        // 출력 처리.. Servletd을 통해서 처리한 결과를 JSP가 처리함
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/templates/order/order.jsp");
        rd.forward(req, resp);
    }
}
