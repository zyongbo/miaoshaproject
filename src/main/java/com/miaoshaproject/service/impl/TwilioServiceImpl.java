package com.miaoshaproject.service.impl;

import com.miaoshaproject.service.TwilioService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URI;

public class TwilioServiceImpl implements TwilioService {

    private static final String ACCOUNT_ID = "account id";

    private static final String AUTH_ID = "auth id";

    static {
        Twilio.init(ACCOUNT_ID, AUTH_ID);
    }

    @Override
    public void sendMessage(String message) throws Exception {
        Message.creator(new PhoneNumber("to-number"), new PhoneNumber("from-number"),
                "Message from Spring Boot Application").create();
    }

    // voiceUri = "http://demo.twilio.com/docs/voice.xml"
    @Override
    public void sendVoice(String voiceUri) throws Exception {
        Call.creator(new PhoneNumber("to-number"), new PhoneNumber("from-number"),
                new URI(voiceUri)).create();
    }
}
