package com.loiane.controller;

import com.loiane.model.Message;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldRestController {

    @RequestMapping("/hello/{name}")
    public Message message(@PathVariable String name) {

        Message msg = new Message(name, "Hello " + name);
        return msg;
    }
}
