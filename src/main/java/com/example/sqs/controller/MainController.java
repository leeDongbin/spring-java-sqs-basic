package com.example.sqs.controller;

import com.example.sqs.dto.EcmDto;
import com.example.sqs.sender.AmazonSqsSender;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MainController {

    private final AmazonSqsSender amazonSQSSender;

    @PostMapping("/send")
    public String send(@RequestBody EcmDto message) throws JsonProcessingException {
        amazonSQSSender.sendMessage(message);

        return "OK";
    }

}


// 프론트 다니는 Event
// 야놀자 (이벤트기반)
// API 기반