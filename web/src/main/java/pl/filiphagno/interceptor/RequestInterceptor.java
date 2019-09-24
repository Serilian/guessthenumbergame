package pl.filiphagno.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class RequestInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Pre handle called, handler = {}", handler);
        log.info("URL = {}", request.getRequestURL());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("Post handle called, handler = {}", handler);
        log.info("URL = {}", request.getRequestURL());
        log.info("model = {}", modelAndView.getModel());
        log.info("view = {}", modelAndView.getViewName());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("After completion handle called, handler = {}", handler);
        log.info("URL = {}", request.getRequestURL());
    }
}
