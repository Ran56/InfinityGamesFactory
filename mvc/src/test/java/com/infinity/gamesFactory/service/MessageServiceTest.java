package com.infinity.gamesFactory.service;


import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.infinity.gamesFactory.ApplicationBootstrap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBootstrap.class)
public class MessageServiceTest {

    @Autowired
    private MessageService messageService;

    @Autowired
    private AmazonSQS sqsClient;

    @Before
    public void setUp(){
    }

    @After
    public void tearDown()
    {
        reset(sqsClient);
    }

    @Test
    public void sendMessageTest()
    {
        messageService.sendMessage("test",1);
//        assertTrue(false);
    }

    @Test
    public void getQueueUrlTest()
    {
//        GetQueueUrlResult stubResult = mock(GetQueueUrlResult.class);
//        when(sqsClient.getQueueUrl(anyString())).thenReturn(stubResult);
        messageService.getQueueUrl("123");
        verify(sqsClient,times(1)).getQueueUrl("123");
    }


}
