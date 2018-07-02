package com.example.webmagic.util;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;

public class EncryptionUtil {

    public static String generateSignature(List params, String secret) {
        StringBuffer buffer = new StringBuffer();
        Collections.sort(params);
        for (int i = 0; i < params.size(); i++) {
            buffer.append((String) params.get(i));
        }
        buffer.append(secret);
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            StringBuffer result = new StringBuffer();
            byte[] bs = md.digest(buffer.toString().getBytes("UTF-8"));
            for (int i = 0; i < bs.length; i++) {
                byte b = bs[i];
                result.append(Integer.toHexString((b & 0xf0) >>> 4));
                result.append(Integer.toHexString(b & 0x0f));
            }
            return result.toString();
        } catch (java.security.NoSuchAlgorithmException ex) {
            return "";
        } catch (UnsupportedEncodingException uee) {
            return "";
        }
    }
}
