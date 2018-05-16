package com.jingling.basic.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Scanner;

/**
 * @Auther: ZhangYuanYou
 * @Description: 使用ES对属性值进行加密
 * <p>属于MD5加密</p>
 * @Date: 上午10:17 17-5-24
 */
public class DESUtils {

    //指定DES加密解密所用的密钥
    private static Key key;
    private static String KEY_STR = "myKey";

    static {
        try{
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            keyGenerator.init(new SecureRandom(KEY_STR.getBytes()));
            key = keyGenerator.generateKey();
            keyGenerator = null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Auther: ZhangYuanYou
     * @Description: 对字符串进行DES加密，返回BASE64编码加密的字符串
     * @Date: 上午10:32 17-5-24
     */
    public static String getEncryptString(String str){
        BASE64Encoder base64Encoder = new BASE64Encoder();
        try {
            byte[] strBytes = str.getBytes();
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptStrBytes = cipher.doFinal(strBytes);
            return base64Encoder.encode(encryptStrBytes);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Auther: ZhangYuanYou
     * @Description: 对BASE64编码的加密字符进行解密，返回解密后的字符串
     * @Date: 上午10:37 17-5-24
     */
    public static String getDecryptString(String str){
        BASE64Decoder base64Decoder = new BASE64Decoder();
        try {
            byte[] strBytes = base64Decoder.decodeBuffer(str);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptStrBytes = cipher.doFinal(strBytes);
            return new String(decryptStrBytes, "UTF8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("input:");
        String str = scanner.nextLine();
        System.out.println("加密后:");
        String after = getEncryptString(str);
        System.out.println(after);
        System.out.println("加密前后是否相等");
        String decode = getDecryptString(after);
        System.out.println(decode);
        System.out.println(decode.equals(str));

    }
}
