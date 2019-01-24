package com.multi.thread.guide.core.chapter6.section4;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/24 下午3:06
 */
public class ThreadLocalCleanupFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
        ThreadLocalMemoryLeak.counterHolder.remove();
    }

    @Override
    public void destroy() {

    }
}
