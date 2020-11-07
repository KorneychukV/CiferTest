package ru.vkorneychuk.cifer_test;

import java.io.UnsupportedEncodingException;

public class main {

    public static void main(String[] args) throws UnsupportedEncodingException {
        TokenUtils tu = new TokenUtils();
        User user = new User("vkorneychuk", "3333332", "admin");

        String token = tu.createToken(user);
        User user_decrypt = tu.parseToken(token);

        String decrypt_lpr = String.format("%s_%s_%s", user_decrypt.getLogin(),
                user_decrypt.getPassword(), user_decrypt.getRole());
        System.out.println("Encrypted text :" + token);
        System.out.println("Text after decryption: '" + decrypt_lpr + "'");
    }
}
