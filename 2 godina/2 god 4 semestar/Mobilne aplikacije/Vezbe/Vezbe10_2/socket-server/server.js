const express = require('express');
const socket = require('socket.io');
const fs = require('fs');
const app = express();
const port = 3000;

const server = app.listen(port);
app.use(express.static('public'));
console.log('Server is running');
const io = socket(server);
var count = 0;

var List = require("collections/list");

class Message {
    constructor(_id, text, sender) {
      this._id = _id;
      this.text = text;
      this.sender = sender;
    }
  }

var messages = new List([]);


io.on('connection', (socket) => {
    console.log("New socket connection: " + socket.id);

    socket.on('counter', () => {
        count++;
        console.log("counter " + count);
        io.emit('counter', count);
    })

    socket.on('message', (messageText) => {
        var randomBoolean = Math. random() >= 0.5; 
        messages.add(new Message(messages.length, messageText, randomBoolean));
        console.log(messages.toJSON());
        io.emit('message', messages.toJSON());
    })
})
