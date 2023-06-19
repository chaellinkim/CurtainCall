package com.cc.model.cypher;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*복호화 불가능 - 비밀번호에서만 사용하기*/
public class SHA256 {

    public String encrypt(String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(text.getBytes());

        return bytesToHex(md.digest());
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
    
    public static void main(String[] args) throws NoSuchAlgorithmException {
    	 SHA256 sha256 = new SHA256();
         
         //비밀번호
         String password = "ds530494";
         //SHA256으로 암호화된 비밀번호
         String cryptogram = sha256.encrypt(password);
         
         System.out.println(cryptogram);
         
         //비밀번호 일치 여부
         System.out.println(cryptogram.equals(sha256.encrypt(password)));
    }

}
