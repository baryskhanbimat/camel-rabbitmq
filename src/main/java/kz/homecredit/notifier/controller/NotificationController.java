package kz.homecredit.notifier.controller;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v2")
public class NotificationController {
    @Autowired
    private ProducerTemplate producerTemplate;

    @SuppressWarnings("rawtypes")
    @RequestMapping(method = RequestMethod.POST, value = "/notification")
    public ResponseEntity sendMessage() {
        producerTemplate.sendBody("direct:notification", "Test message");
        return ResponseEntity.ok().build();
    }
}
