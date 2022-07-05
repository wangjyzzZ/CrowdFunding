package com.wood.crowd.util;

import com.wood.crowd.constant.Constants;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CommonUtils {

    public static String md5(String source) {
        if (source == null || source.length() == 0) {
            throw new RuntimeException(Constants.MESSAGE_STRING_INVALID);
        }
        String algorithm = "md5";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            byte[] input = source.getBytes();
            byte[] output = messageDigest.digest(input);
            int signum = 1;
            BigInteger bigInteger = new BigInteger(signum, output);
            int radix = 16;
            return bigInteger.toString(radix).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String accept = request.getHeader("Accept");
        String xRequestInfo = request.getHeader("X-Requested-With");
        return (accept != null && accept.contains("application/json"))
                || (xRequestInfo != null && xRequestInfo.contains("XMLHttpRequest"));
    }
}
