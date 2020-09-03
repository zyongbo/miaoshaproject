package com.miaoshaproject.service;

public interface TwilioService {
    public void sendMessage(String message) throws Exception;
    public void sendVoice(String voiceUri) throws Exception;
}
