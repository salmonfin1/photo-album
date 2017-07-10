package com.photoalbum.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MessageController {

    @RequestMapping("/message")
    public @ResponseBody
    Map<String, Object> getMessage() {
        return Collections.singletonMap("success", Boolean.TRUE);
    }
}
