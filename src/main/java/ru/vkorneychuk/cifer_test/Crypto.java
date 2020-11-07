package ru.vkorneychuk.cifer_test;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Crypto {

    private Cipher cipher;
    private final String transformation = "AES/ECB/PKCS5Padding";
    private final String charset = "UTF-8";
    private SecretKey secretKey;

    public Crypto() {
        String defPass = "sdfsdfsdfsdfsdf";
        try {
            MessageDigest digester = MessageDigest.getInstance("SHA-256");
            digester.update(defPass.getBytes(StandardCharsets.UTF_8));
            byte[] key = digester.digest();
            this.secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String encrypt(String lpr) {
        try {
            cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.ENCRYPT_MODE, this.secretKey);
            byte[] encrypted = cipher.doFinal(lpr.getBytes(charset));
            return URLEncoder.encode(Base64.getEncoder().encodeToString(encrypted), charset);
        } catch (Exception ignored) { }
        return null;
    }

    public String decrypt(String token) {
        try {
            cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.DECRYPT_MODE, this.secretKey);
            byte [] prep_token = Base64.getDecoder().decode(URLDecoder.decode(token, charset));
            byte[] decrypted = cipher.doFinal(prep_token);
            return new String(decrypted, charset);
        } catch (Exception ignored) {}
        return null;
    }
    
}
