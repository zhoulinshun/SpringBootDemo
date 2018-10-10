package cn.miss.spring.config;

import cn.miss.spring.util.util.ResponseUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.StringJoiner;

/**
 * @Author: 周林顺
 * @Description:
 * @Date: Created in 2018/3/22.
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static String param_error = "The param you submit may be problematic ,server can not normally read all params";
    private static String PARAM_ERROR = "param: %s has error";

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ResponseEntity<Object> objectResponseEntity = super.handleExceptionInternal(ex, body, headers, status, request);
        return new ResponseEntity<>(getResponse(request, body == null ? ex : body), objectResponseEntity.getHeaders(), objectResponseEntity.getStatusCode());
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final StringJoiner stringJoiner = new StringJoiner(",", "[", "]");
        ex.getFieldErrors().stream().map(FieldError::getField).forEach(stringJoiner::add);
        return handleExceptionInternal(ex, String.format(PARAM_ERROR,stringJoiner.toString()), headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, param_error, headers, status, request);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object illegalArgumentExceptionHandler(Exception e, WebRequest webRequest) {
        return getResponse(webRequest, e.getMessage());
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object handler(Exception e, WebRequest webRequest) {
        e.printStackTrace();
        return getResponse(webRequest, e);
    }

    private Object getResponse(WebRequest request, Object o) {
        if (o instanceof String) {
            return ResponseUtil.fail(request.getDescription(true) + " : " + o);
        }
        if (o instanceof Exception) {
            return ResponseUtil.fail(request.getDescription(true) + " : " + ((Exception) o).getMessage());
        }

        return ResponseUtil.fail(o.toString());
    }
}
