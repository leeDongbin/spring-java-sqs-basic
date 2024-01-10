package com.example.sqs.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Slf4j
@Configuration
public class AmazonSqsConfig {

    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String region;

    /**
     * sqs 접근을 위한 위한 사용자 bean
     */
    @Primary
    @Bean
    public AmazonSQSAsync amazonSQSAws() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        return AmazonSQSAsyncClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();
    }

//    @Bean
//    public SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory(AmazonSQSAsync amazonSQSAsync) {
//        SimpleMessageListenerContainerFactory factory = new SimpleMessageListenerContainerFactory();
//        factory.setAmazonSqs(amazonSQSAsync);   // SQS API를 가지고 통신을 하는 container에 의해 사용되어지는 AmazonSQSAsync를 설정
//        factory.setMaxNumberOfMessages(10);     // 한 번 poll하는 동안 조회되어져야하는 메세지의 최대 개수, 높을 수록 poll request 요청을 줄여준다.
//        factory.setWaitTimeOut(20);             // queue에 메세지가 없을 때, queue로 들어오는 새로운 메세지에 대해 poll 요청 시 대기하는 timeout 시간
//        factory.setTaskExecutor(messageThreadPoolTaskExecutor());   // 메세지를 poll하고 처리하는 handler 함수 설정
//        return factory;
//    }
//
//    @Bean
//    public ThreadPoolTaskExecutor messageThreadPoolTaskExecutor() {
//        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
//        taskExecutor.setThreadNamePrefix("sqs-task-");
//        taskExecutor.setCorePoolSize(20);
//        taskExecutor.setMaxPoolSize(20);
//        taskExecutor.afterPropertiesSet();
//        return taskExecutor;
//    }

}