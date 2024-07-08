package filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;

import java.io.IOException;
/*
@WebFilter(value = "/board", initParams = {@WebInitParam(name="k1", value="value1"),
        @WebInitParam(name="k2", value="value2")})

 */
public class BoardFilter implements Filter { // 설정에서 중요한 부분들을 강제하기 위해 추상메서드가 정의되어 있고, 이것을 구현해야 함!


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String k1 = filterConfig.getInitParameter("k1");
        String k2 = filterConfig.getInitParameter("k2");
        System.out.printf("k1=%s, k2=%s%n", k1, k2);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 필터링을 하는 핵심적인 기능 doFilter
        System.out.println("BoardFilter - 요청 전");

        chain.doFilter(request, response);
        // 다음 필터 또는 서블릿의 처리 메서드 실행

        System.out.println("BoardFilter - 응답 후");
    }
}
