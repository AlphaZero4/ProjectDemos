package com.example.productcatalogueproxy.Services;

import com.example.productcatalogueproxy.FakeStoreDtos.EmailDto;
import com.example.productcatalogueproxy.Utils.EmailUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;


import java.util.Properties;

@Service
public class KafkaConsumer {
    @Autowired
    ObjectMapper objectMapper;
    @KafkaListener(topics="sendEmail",groupId = "KafkaService")
    public void handleSendEmail(String message){
        try {
            EmailDto dto = objectMapper.readValue(message, EmailDto.class);
            Properties props = new Properties();
          /*for gmail  props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
            props.put("mail.smtp.port", "587"); //TLS Port
            props.put("mail.smtp.auth", "true"); //enable authentication
            props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
*/
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587"); // Or 465
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");

            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            //props.put("mail.smtp.ssl.trust", "smtp.mail.yahoo.com");
            props.put("mail.smtp.ssl.protocols", "TLSv1.2");
          //  props.put("mail.smtp.ssl.enable", "true");
            //create Authenticator object to pass in Session.getInstance argument
          /*  props.put("retries","2700000");
            props.put("retry.backoff.ms","100" );

            props.put("batch.size","131072");
            props.put("buffer.memory","66554432");
            props.put("bootstrap.servers","Ipserver:9094");
            props.put("compression.type","snappy");
            props.put("acks","all");

            props.put("linger.ms","100");
            props.put(" request.timeout.ms","60000");
            props.put("enable.idempotence","true");
            props.put(" delivery.timeout.ms","180300");
            props.put(" metadata.max.idle.ms","180000");
*/


            Authenticator auth = new Authenticator() {
                //override the getPasswordAuthentication method
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("rajda1943@gmail.com", "gzeu zsvt uscz gqit");
                }
            };
            System.out.println("works till auth");
            Session session = Session.getInstance(props, auth);
            EmailUtil.sendEmail(session, dto.getFrom(),dto.getTo(), dto.getSubject(), dto.getBody());
        }

        catch(JsonProcessingException e){
            throw new RuntimeException(e);
        }




    }
}
