package cyl.cframe.android.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5类
 *
 * @author Richard.Ma
 */
public class MD5Utils {
    private static final String HASH_ALGORITHM = "MD5";
    /**
     * 10 digits + 26letters
     */
    private static final int RADIX = 36;

    public static String Md5(byte[] bytes) {
        byte[] md5 = getMD5(bytes);
        BigInteger bi = new BigInteger(md5).abs();
        return bi.toString(RADIX);
    }

    public static String Md5(byte[] bytes, int radix) {
        byte[] md5 = getMD5(bytes);
        BigInteger bi = new BigInteger(md5).abs();
        return bi.toString(radix);
    }

    private static byte[] getMD5(byte[] data) {
        byte[] hash = null;
        try {
            MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
            digest.update(data);
            hash = digest.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return hash;
    }

    public static String getMD5(String info) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(info.getBytes("UTF-8"));
            byte[] encryption = md5.digest();

            StringBuffer strBuf = new StringBuffer();
            for (int i = 0; i < encryption.length; i++) {
                if (Integer.toHexString(0xff & encryption[i]).length() == 1) {
                    strBuf.append("0").append(Integer.toHexString(0xff & encryption[i]));
                } else {
                    strBuf.append(Integer.toHexString(0xff & encryption[i]));
                }
            }

            return strBuf.toString();
        } catch (NoSuchAlgorithmException e) {
            return "";
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
