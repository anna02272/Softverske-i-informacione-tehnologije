package com.example.serverdemo.service;

import com.example.serverdemo.model.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class MessageService {
    private static List<Message> messages = new ArrayList<>();
    private static Integer iterator = 0;

    public List<Message> getMessages(){
        return messages;
    }

    public Message sendMessages(String message){
        iterator += 1;
        Random rd = new Random();
        Message m = Message.builder()._id(Long.valueOf(iterator)).text(message).sender(rd.nextBoolean()).build();
        messages.add(m);
        System.out.println(m);
        return m;
    }
}
