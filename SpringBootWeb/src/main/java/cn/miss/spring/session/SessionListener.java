package cn.miss.spring.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/25.
 */
@Component
public class SessionListener implements HttpSessionListener, ServletContextAware {

    private ServletContext servletContext;

    private Map<String, HttpSession> httpSessionMap = new ConcurrentHashMap<>(1 << 10);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        final HttpSession session = se.getSession();
        httpSessionMap.put(session.getId(), session);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        httpSessionMap.remove(se.getSession().getId());
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
