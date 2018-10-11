package com.example.webmagic.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Arrays;

public class AESUtil {

    /** AES加密算法 */
    private static final String AES_ALGORITHM = "AES";

    /** Base64 编码 */
    private static final Base64 B64 = new Base64();

    /** 安全的随机数源 */
    private static final SecureRandom RANDOM = new SecureRandom();


    /** AES密钥 */
    private static SecretKeySpec aesKey(String key) {
        byte[] bs = key.getBytes();
        if (bs.length != 16) {
            bs = Arrays.copyOf(bs, 16);// 处理数组长度为16
        }
        return new SecretKeySpec(bs, AES_ALGORITHM);
    }

    /**
     * AES加密
     *
     * @param str 需要加密的明文
     * @param key 密钥
     * @param urlSafety 密文是否需要Url安全
     * @return 加密后的密文(str/key为null返回null)
     */
    public static String aesEncrypt(String str, String key, boolean urlSafety) {
        if (null != str && null != key) {
            try {
                Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
                c.init(Cipher.ENCRYPT_MODE, aesKey(key), RANDOM);
                byte[] bytes = c.doFinal(str.getBytes("utf-8"));// 加密
                if (urlSafety) {
                    return Base64.encodeBase64URLSafeString(bytes);
                } else {
                    return new String(B64.encode(bytes));
                }
            } catch (Exception e) {
                System.out.println("AES加密失败, 密文：" + str + ", key：" + key + ", 错误：" + e.getMessage());
            }
        }
        return null;
    }

    /**
     * AES解密
     *
     * @param str 需要解密的密文(base64编码字符串)
     * @param key 密钥
     * @return 解密后的明文
     */
    public static String aesDecrypt(String str, String key) {
        if (null != str && null != key) {
            try {
                Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
                c.init(Cipher.DECRYPT_MODE, aesKey(key), RANDOM);
                return new String(c.doFinal(B64.decode(str)), "utf-8");// 解密
            } catch (BadPaddingException e) {
                System.out.println("AES解密失败, 密文：" + str + ", key：" + key + ", 错误：" + e.getMessage());
            } catch (Exception e) {
                System.out.println("AES解密失败, 密文：" + str + ", key：" + key + ", 错误：" + e.getMessage());
            }
        }
        return null;
    }
}
