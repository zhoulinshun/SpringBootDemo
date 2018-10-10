package cn.miss.spring.config.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author zhoulinshun
 * @create 2018-04-25 下午12:45
 */
@Component
public class UrlFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(UrlFilter.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filter) throws IOException, ServletException {
        String url = request.getRequestURI();

        //when swagger skip
        if (url.contains("swagger") || url.contains("/api/v2/api-docs")) {
            filter.doFilter(request, response);
            return;
        }

        ResponseWrapper responseWrapper = new ResponseWrapper(response);
        RequestWrapper requestWrapper = new RequestWrapper(request);

        final String uuid = UUID.randomUUID().toString();
        final String urlParams = request.getQueryString();
        final String headers = objectMapper.writeValueAsString(getHeaders(request));
        final String method = request.getMethod();
        final String postParams = objectMapper.writeValueAsString(request.getParameterMap());
        final String requestBody = new String(requestWrapper.getBody(), StandardCharsets.UTF_8);

        Date start = new Date();
        logger.info("{}-----------------start url: [{}], " +
                "\nurlParams:[{}] " +
                "\nrequestHeader: [{}] " +
                "\nrequestMethod: [{}] " +
                "\npostParams: [{}] " +
                "\nrequestBody: [{}]", uuid, url, urlParams, headers, method, postParams, requestBody);


        filter.doFilter(requestWrapper, responseWrapper);
        byte[] result = responseWrapper.getResponseData();

        //解决可能在运行的过程中页面只输出一部分
        response.setContentLength(-1);
        response.setCharacterEncoding("UTF-8");
        final ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(result);
        outputStream.flush();

        final String contentType = responseWrapper.getContentType();
        String jsonResult = "非json响应";
        final boolean jsonAdjust = contentType != null && (contentType.contains("application/json") || contentType.contains("text/plain"));
        if (jsonAdjust) {
            jsonResult = new String(result, response.getCharacterEncoding());
        }
        Date end = new Date();
        logger.info("{}-----------------end url:[{}],  \nhttpstatus: [{}]  \ntime consuming: [{}ms] \nresult:[{}]", uuid, url, response.getStatus(), end.getTime() - start.getTime(), jsonResult);
    }

    private void replaceParameters(Map<String, String[]> parametersMap) {
        if (parametersMap.containsKey("password")) {
            parametersMap.remove("password");
            parametersMap.put("password", new String[]{"******"});
        }
    }

    private Map<String, String> getHeaders(HttpServletRequest httpServletRequest) {
        Map<String, String> headers = new HashMap<>();
        final Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            final String name = headerNames.nextElement();
            final String value = httpServletRequest.getHeader(name);
            headers.put(name, value);
        }
        return headers;
    }
}
