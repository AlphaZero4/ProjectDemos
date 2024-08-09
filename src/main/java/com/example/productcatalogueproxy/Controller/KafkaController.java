package com.example.productcatalogueproxy.Controller;


import com.example.productcatalogueproxy.Clients.KafkaProducerClient;
import com.example.productcatalogueproxy.FakeStoreDtos.EmailDto;
import com.example.productcatalogueproxy.Services.KafkaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka/register")
public class KafkaController {
@Autowired
    private KafkaService service;


    @PostMapping
    ResponseEntity<String> sendNotification(@RequestBody EmailDto dto){
try{

service.sendNotifcation(dto.getFrom(), dto.getTo(), dto.getBody(), dto.getSubject());
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<String>("welcome! Email has been sent", headers, HttpStatus.OK);

}

      catch(Exception e)  {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<String>("failed, there is an error", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
