var express = require('express');
var bodyParser = require('body-parser');
var app = express()
  .use(bodyParser.urlencoded({
    extended: true
  }))
  .use(bodyParser.json())
  .use(function(req, res) {
    if (req.body.foo) {
      res.end('Body parsed! Value of foo: ' + req.body.foo);
    } else {
      res.end('Body does not have foo!');
    }
  })
  .use(function(err, req, res, next) {
    res.end('Invalid body!');
  })
  .listen(3000);
console.log('Server is running on port 3000...')