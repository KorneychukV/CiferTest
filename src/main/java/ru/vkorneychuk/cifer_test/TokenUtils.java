package ru.vkorneychuk.cifer_test;

public class TokenUtils {

    public String createToken(User user){
        Crypto crypto = new Crypto();
        String lpr = String.format("%s_%s_%s", "vkorneychuk", "3333332", "admin");
        return crypto.encrypt(lpr);
    }

    public User parseToken(String token){
        Crypto crypto = new Crypto();
        String res = crypto.decrypt(token);
        String[] user_vals = res.split("_");
        return new User(user_vals[0], user_vals[1], user_vals[2]);
    }

}
