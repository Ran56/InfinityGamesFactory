package com.infinity.gamesFactoryConsumer.config;


import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.infinity.gamesFactoryConsumer.service.ProcessService;
import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.destination.DynamicDestinationResolver;

import javax.jms.Session;
import java.io.IOException;

@Configuration
@EnableJms
public class JmsConfig {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public AmazonSQS getAmazonSQS()
    {
        return AmazonSQSClientBuilder
                .standard()
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .withRegion(Regions.US_EAST_1)
                .build();
    }

    @Bean(name = "connectionFactory")
    public SQSConnectionFactory getSQSConnectionFactory(@Autowired AmazonSQS amazonSQSClient)
    {
        SQSConnectionFactory factory =
                new SQSConnectionFactory(new ProviderConfiguration(),amazonSQSClient);
        return factory;
    }

    @Bean
    public JmsTemplate getJmsTemplate(@Autowired SQSConnectionFactory connectionFactory)
    {
        JmsTemplate jmsTemplate = new JmsTemplate((connectionFactory));
        return jmsTemplate;
    }

    @Bean("dynamicResolver")
    public DynamicDestinationResolver getDynamicDestinationResolver()
    {
        return new DynamicDestinationResolver();
    }


    @Bean(name = "jmsListenerContainerFactory")
    @DependsOn({"connectionFactory","dynamicResolver"})
    public DefaultJmsListenerContainerFactory getDefaultJmsListenerContainerFactory
            (@Autowired SQSConnectionFactory connectionFactory,@Autowired DynamicDestinationResolver dynamicDestinationResolver){
        DefaultJmsListenerContainerFactory jmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        jmsListenerContainerFactory.setSessionTransacted(false);
        jmsListenerContainerFactory.setConnectionFactory(connectionFactory);
        jmsListenerContainerFactory.setDestinationResolver(dynamicDestinationResolver);
        jmsListenerContainerFactory.setConcurrency("1");
        jmsListenerContainerFactory.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
        return jmsListenerContainerFactory;}


    @Bean
    public ProcessService getProcessService() throws IOException {
        String ACCOUNT_SID = System.getProperty("ACCOUNT_SID");
        String AUTH_TOKEN = System.getProperty("AUTH_TOKEN");
        Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
        ProcessService processService = new ProcessService();
        return processService;
    }

}
