package com.example.productcatalogueproxy.Services;

import com.example.productcatalogueproxy.Clients.KafkaProducerClient;
import com.example.productcatalogueproxy.FakeStoreDtos.EmailDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {
    @Autowired
    private KafkaProducerClient kafkaProducerClient;

    @Autowired
    private ObjectMapper objectMapper;
    public void sendNotifcation(String from,String to,String body,String subject){
        try {
            EmailDto sendEmailMessageDto = new EmailDto();
            sendEmailMessageDto.setTo(to);
            if(from==null){sendEmailMessageDto.setFrom("admin@scaler.com");}
            else sendEmailMessageDto.setFrom(from);

            sendEmailMessageDto.setSubject("Welcome to this demonstration");
            sendEmailMessageDto.setBody("Have a pleasant stay");
            kafkaProducerClient.sendMessage("sendEmail", objectMapper.writeValueAsString(sendEmailMessageDto));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }



    }

}
