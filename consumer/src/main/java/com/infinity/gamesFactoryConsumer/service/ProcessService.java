package com.infinity.gamesFactoryConsumer.service;


import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ErrorHandler;

import java.io.IOException;

@Component
public class ProcessService implements ErrorHandler {
    @Autowired
    private ProcessService processService;

    private Logger logger = LoggerFactory.getLogger(getClass());
    String receiveNumber = System.getProperty("receiveNumber");
    String sendNumber = System.getProperty("sendNumber");



    @Override
    public void handleError(Throwable t) {
        logger.error("Error in listener", t);
    }

    @JmsListener(destination = "infinity-standard-queue")
    public void processMessage(String msg) throws IOException
    {
        System.out.println("infinity-standard-queue: "+msg);

        MessageCreator messageCreator = processService.SMS(receiveNumber,sendNumber,"Your order has been confirmed!");
        processService.twilioSMSSend(messageCreator);
    }


    @JmsListener(destination = "infinity-standard-queue2")
    public void processMessage2(String msg) throws IOException
    {
        System.out.println("infinity-standard-queue2: "+msg);
    }

    public MessageCreator SMS(String receiveNumber, String sendNumber, String messageBody) {
        MessageCreator message = Message.creator(
                new com.twilio.type.PhoneNumber(receiveNumber),
                new com.twilio.type.PhoneNumber(sendNumber),
                messageBody);
        return message;
    }

    public void twilioSMSSend(MessageCreator creator){
        creator.create();
    }

}
