package com.example.serverdemo.controller;

import com.example.serverdemo.model.Message;
import com.example.serverdemo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
@CrossOrigin
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping
    public ResponseEntity<?> getMessage(){
        return new ResponseEntity<>(messageService.getMessages(), HttpStatus.OK);
    };

    @PostMapping
    public Message sendMessage(@RequestBody String message){
        return messageService.sendMessages(message);
    };
}
