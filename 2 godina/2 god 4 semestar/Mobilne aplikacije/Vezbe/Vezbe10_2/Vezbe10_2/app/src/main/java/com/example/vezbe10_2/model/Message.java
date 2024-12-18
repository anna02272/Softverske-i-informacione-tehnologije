package com.example.vezbe10_2.model;

public class Message {
    private Long _id;
    private String text;
    private Boolean sender = true;

    public Message(Long id, String text, Boolean sender) {
        this._id = id;
        this.text = text;
        this.sender = sender;
    }

    public Message() {
    }

    public Long getId() {
        return _id;
    }

    public void setId(Long id) {
        this._id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getSender() {
        return sender;
    }

    public void setSender(Boolean sender) {
        this.sender = sender;
    }
}
