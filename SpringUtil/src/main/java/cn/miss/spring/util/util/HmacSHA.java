package cn.miss.spring.util;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/7/2.
 */
public class HmacSHA {

    private static final String ENCODING = "UTF-8";

    public static String HmacSHA256Encrypt(String prefix, Map<String, String> text, String join, String encryptKey) throws Exception {
        if (text instanceof TreeMap) {
            return HmacSHA256Encrypt(prefix, (TreeMap<String, String>) text, join, encryptKey);
        }
        TreeMap<String, String> treeMap = new TreeMap<>();
        if (text != null) {
            text.forEach(treeMap::put);
        }
        return HmacSHA256Encrypt(prefix, treeMap, join, encryptKey);
    }

    /**
     * sha256加密
     *
     * @param prefix     消息的前缀
     * @param text       消息中间键值对
     * @param join       消息连接符
     * @param encryptKey 密钥
     * @return
     * @throws Exception
     */
    public static String HmacSHA256Encrypt(String prefix, TreeMap<String, String> text, String join, String encryptKey) throws Exception {
        StringBuilder prefixBuilder;
        if (prefix == null) {
            prefixBuilder = new StringBuilder();
        } else {
            prefixBuilder = new StringBuilder(prefix);
        }
        if (text != null) {
            for (String s : text.keySet()) {
                prefixBuilder.append(join).append(s).append("=").append(text.get(s));
            }
        }
        return HmacSHA256Encrypt(prefixBuilder.toString(), encryptKey);
    }

    public static String HmacSHA256Encrypt(String encryptText, String encryptKey) throws Exception {
        byte[] data = encryptKey.getBytes(ENCODING);
        SecretKey secretKey = new SecretKeySpec(data, "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKey);
        byte[] text = encryptText.getBytes(ENCODING);
        byte[] result = mac.doFinal(text);
        return byteArrayToHexString(result);
    }

    private static String byteArrayToHexString(byte[] b) {
        StringBuilder hs = new StringBuilder();
        for (int n = 0; b != null && n < b.length; n++) {
            String temp = Integer.toHexString(b[n] & 0XFF);
            if (temp.length() == 1) {
                hs.append('0');
            }
            hs.append(temp);
        }
        return hs.toString().toLowerCase();
    }


}
