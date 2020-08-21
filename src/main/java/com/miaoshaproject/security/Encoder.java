package com.miaoshaproject.security;

import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
public class Encoder {
    public String encodeByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        Base64.Encoder base64 = Base64.getEncoder();
        String encodedStr = base64.encodeToString(md5.digest(str.getBytes("utf-8")));
        return encodedStr;
    }
}
