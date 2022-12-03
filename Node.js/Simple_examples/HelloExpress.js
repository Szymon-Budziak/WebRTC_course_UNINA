var app = require('express')();
var http = require('http').Server(app);

app.get('/', function (req, res) {
    res.send("Hello WebRTC class from express!");
})

http.listen(3000, function () {
    console.log("Listening on port 3000 ...");
})