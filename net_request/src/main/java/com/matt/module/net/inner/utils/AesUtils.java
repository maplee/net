package com.matt.module.net.inner.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Author:Created by matt on 2020/3/23.
 * Email:jiagfone@163.com
 */

public class AesUtils {

    public static final String logalrithm = "AES/CBC/PKCS5Padding";
    public static final String bm = "utf-8";
    private static byte[] keyValue = new byte[]{
            17, -35, -45, 25, 54, -55, -45, 40, 35, -45, 35, 26, -95, 25, -35, 76
    };
    private static byte[] iv = new byte[]{
            -13, 35, -25, 22, 54, -87, 34, -15, -22, 55, 45, -66, 28, 5 - 4, 67, 43
    };

    private static Key keySpec;
    private static IvParameterSpec ivSpec;

    static {
        keySpec = new SecretKeySpec(keyValue, "AES");
        ivSpec = new IvParameterSpec(iv);
    }

    /**
     * @param msg 加密的数据
     * @return
     * @throws
     * @Title: encrypt
     * @Description: 加密，使用指定数据源生成密钥，使用用户数据作为算法参数进行AES加密
     */
    public static String encrypt(String msg) {
        byte[] encryptedData = null;
        try {
            Cipher ecipher = Cipher.getInstance(logalrithm);
            ecipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            encryptedData = ecipher.doFinal(msg.getBytes(bm));
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return asHex(encryptedData);
    }


    /**
     * @param value
     * @return
     * @throws
     * @Title: decrypt
     * @Description: 解密，对生成的16进制的字符串进行解密
     */
    public static String decrypt(String value) {
        try {
            Cipher ecipher = Cipher.getInstance(logalrithm);
            ecipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            return new String(ecipher.doFinal(asBin(value)));
        } catch (BadPaddingException e) {
            System.out.println("解密错误：" + value);
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            System.out.println("解密错误：" + value);
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * @param buf
     * @return
     * @throws
     * @Title: asHex
     * @Description: 将字节数组转换成16进制字符串
     */
    private static String asHex(byte[] buf) {
        StringBuffer strbuf = new StringBuffer(buf.length * 2);
        int i;
        for (i = 0; i < buf.length; i++) {
            if (((int) buf[i] & 0xff) < 0x10) {
                strbuf.append("0");
            }
            strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
        }
        return strbuf.toString();
    }


    /**
     * @param src
     * @return
     * @throws
     * @Title: asBin
     * @Description: 将16进制字符串转换成字节数组
     */
    private static byte[] asBin(String src) {
        if (src.length() < 1) {
            return null;
        }
        byte[] encrypted = new byte[src.length() / 2];
        for (int i = 0; i < src.length() / 2; i++) {
            int high = Integer.parseInt(src.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(src.substring(i * 2 + 1, i * 2 + 2), 16);
            encrypted[i] = (byte) (high * 16 + low);
        }
        return encrypted;
    }

}
