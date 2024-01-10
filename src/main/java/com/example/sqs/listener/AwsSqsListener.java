package com.example.sqs.listener;

import com.example.sqs.dto.EcmDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.Acknowledgment;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class AwsSqsListener {
    private final ObjectMapper objectMapper;

//    @SqsListener(value = "${cloud.aws.sqs.queue.name}", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
//    public void listen(@Payload String info, @Headers Map<String, String> headers, Acknowledgment ack) {
//        log.info("-------------------------------------start SqsListener");
//        log.info("-------------------------------------info {}", info);
//        log.info("-------------------------------------headers {}", headers);
//        //수신후 삭제처리
//        ack.acknowledge();
//    }

    @SqsListener(value = "${cloud.aws.sqs.queue.name}", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
    public void listen(@Payload String info, @Headers Map<String, String> headers, Acknowledgment ack) throws JsonProcessingException {
        EcmDto readValue = objectMapper.readValue(info, EcmDto.class);

        log.info("-------------------------------------start SqsListener");
        log.info("-------------------------------------ecmId {}", readValue.getEcmId());
        log.info("-------------------------------------info {}", info);
        log.info("-------------------------------------headers {}", headers);
        //수신후 삭제처리
        ack.acknowledge();
    }

}
