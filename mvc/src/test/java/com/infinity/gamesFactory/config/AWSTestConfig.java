package com.infinity.gamesFactory.config;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Configuration
@Profile("unit")
public class AWSTestConfig {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public AmazonS3 getAmazonS3()
    {
        AmazonS3 amazonS3 = mock(AmazonS3.class);
//        when(amazonS3.doesBucketExistV2(anyString())).thenReturn(true);
        return amazonS3;
    }


    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public AmazonSQS getAmazonSQS()
    {
        AmazonSQS amazonSQS = mock(AmazonSQS .class);
        GetQueueUrlResult stubResult = mock(GetQueueUrlResult.class);
        when(amazonSQS.getQueueUrl(anyString())).thenReturn(stubResult);
        return amazonSQS;
    }
}
