package cn.miss.spring.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/9/21.
 */
public class ResponseUtil {
    private static String SUCCESS = "success";
    private static String MESSAGE = "message";
    private static String DATA = "data";

    private static final String SUCCESS_MESSAGE = "success";
    private static final String FAIL_MESSAGE = "fail";


    public static Map<String, Object> fail() {
        return fail(FAIL_MESSAGE);
    }

    public static Map<String, Object> fail(String message) {
        return fail(message, null);
    }

    public static Map<String, Object> fail(String message, Object data) {
        return response(false, message, data);
    }


    public static Map<String, Object> success() {
        return success(SUCCESS_MESSAGE);
    }

    public static Map<String, Object> success(String message) {
        return success(message, null);
    }

    public static Map<String, Object> success(String message, Object data) {
        return response(true, message, data);
    }

    public static Map<String, Object> response(boolean success, String message, Object data) {
        Map<String, Object> map = new HashMap<>(2 << 4);
        map.put(SUCCESS, success);
        map.put(MESSAGE, message);
        map.put(DATA, data);
        return map;
    }

}
