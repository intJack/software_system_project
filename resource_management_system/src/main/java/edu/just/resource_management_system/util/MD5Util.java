package edu.just.resource_management_system.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    public static String encryptToMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : messageDigest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String password = "123";
        String encryptedPassword = encryptToMD5(password);
        System.out.println("Encrypted Password: " + encryptedPassword);
//        System.out.println("length:" + encryptedPassword.length());
        String pwd2 = new String("1234");
        String s = encryptToMD5(pwd2);
        System.out.println(s.equals(encryptedPassword));
    }
}