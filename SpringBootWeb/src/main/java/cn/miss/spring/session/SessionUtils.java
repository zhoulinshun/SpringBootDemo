package cn.miss.spring.session;

import com.google.common.collect.Lists;
import org.apache.catalina.Session;
import org.apache.catalina.core.ApplicationContext;
import org.apache.catalina.core.ApplicationContextFacade;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.session.StandardManager;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/25.
 */
public class SessionUtils {

    public static HttpSession get(ServletContext servletContext, String sessionId) {
        final StandardManager manager = getManager(servletContext);
        if (manager == null) {
            return null;
        }
        try {
            return Optional.ofNullable(manager.findSession(sessionId)).map(Session::getSession).orElse(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static List<HttpSession> getAllSession(ServletContext servletContext) {
        final ArrayList<HttpSession> sessions = Lists.newArrayList();
        final StandardManager manager = getManager(servletContext);
        if (manager != null) {
            final String[] ids = manager.listSessionIds().split(" ");
            for (String id : ids) {
                try {
                    Optional.ofNullable(manager.findSession(id)).map(Session::getSession).ifPresent(sessions::add);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return sessions;
    }

    /**
     * ((StandardManager) ((ApplicationContextFacade) servletContext).context.context.manager).sessions
     *
     * @param servletContext
     * @return
     */
    public static StandardManager getManager(ServletContext servletContext) {
        if (!(servletContext instanceof ApplicationContextFacade)) {
            return null;
        }
        try {
            ApplicationContextFacade facade = (ApplicationContextFacade) servletContext;
            final Field applicationContextFiled = facade.getClass().getDeclaredField("context");
            applicationContextFiled.setAccessible(true);
            final ApplicationContext applicationContext = (ApplicationContext) applicationContextFiled.get(facade);
            final Field standardContextField = ApplicationContext.class.getDeclaredField("context");
            standardContextField.setAccessible(true);
            //org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedContext
            final StandardContext standardContext = (StandardContext) standardContextField.get(applicationContext);
            return (StandardManager) standardContext.getManager();
        } catch (Exception e) {

        }
        return null;
    }

}
