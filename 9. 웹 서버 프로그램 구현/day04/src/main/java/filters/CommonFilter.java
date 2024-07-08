package filters;

import jakarta.servlet.*;

import java.io.IOException;

public class CommonFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 객체가 만들어진 직후에 단 한번만 호출
        System.out.println("init()");
        String key1 = filterConfig.getInitParameter("key1");
        String key2 = filterConfig.getInitParameter("key2");
        System.out.printf("key1=%s, key2=%s%n,", key1, key2);
    }

    @Override
    public void destroy() {
        // 소멸 직전에 호출
        // 자원 해제나 정리 작업에 활용
        System.out.println("destroy()");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // System.out.println("CommonFilter - 요청 전");

        chain.doFilter(new CommonRequestWrapper(request), response);

        // System.out.println("CommonFilter - 응답 후");
    }
}
