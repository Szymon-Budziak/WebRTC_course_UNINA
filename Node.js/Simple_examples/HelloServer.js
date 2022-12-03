var http = require('http');

var server = http.createServer(function (request, response) {
    response.writeHead(200, {"ContentType": "text/html"});
    response.end("<h1>Hello WebRTC class!</h1>\n");
})

server.listen(3000);
console.log("Listening on port 3000 ...");