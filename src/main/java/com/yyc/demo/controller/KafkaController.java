package com.yyc.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ycyin
 * @Classname KafkaController
 * @Description KafkaDemo
 * @Date 2023/3/10 19:33
 */
@RestController
public class KafkaController {
    @Autowired
    private KafkaTemplate<Object, Object> template;

    @GetMapping("/send/{input}")
    public void sendFoo(@PathVariable String input) {
        this.template.send("TOPIC_Y", input);
    }

    @KafkaListener(id = "webGroup", topics = "TOPIC_Y")
    public void listen(String input) {
        // logger.info("input value: {}" , input);
        System.out.println(input);
    }
}
