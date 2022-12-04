const express = require('express');
const http = require('http');
const {Server} = require('socket.io');

const app = express();
const httpServer = http.createServer(app);
const io = new Server(httpServer);
const port = 8181;

app.use(express.static('client'));

httpServer.listen(port, () => {
    console.log(`Listening on port ${port}...`);
})

io.on('connection', (socket) => {
    /*
       Handle 'message' messages
       The first parameter is the name of the event, which is 'message'
       The second parameter is the callback that takes as input the actual message object
       The message object contains both the actual message and the room it was sent to
   */
    socket.on('message', (message) => {
        // broadcast the message to all the clients in the room except the sender
        socket.to(message.room).emit('message', message.message);
    })

    // Handle 'create or join' messages
    socket.on('create or join', (room) => {
        // The fetchSockets returns a promise
        io.in(room).fetchSockets().then((sockets) => {
            numClients = sockets.length;
            console.log(`Number of client: ${numClients}`);

            // First client joining (the initiator)
            if (numClients === 0) {
                // rooms are a server-side concept, it's the server that has to join a client to a room
                // a room is created once at least a socket joins it
                socket.join(room);
                socket.emit('created', room);
            }
            // Second client joining
            else if (numClients === 1) {
                // Inform initiator that another client has joined the same room
                // The client hasn't joined the room yet, so we need to broadcast to all the clients currently in the room (in this case only the initiator)
                io.to(room).emit('remotePeerJoining', room);
                // Let the new peer join room
                socket.join(room);
                // broadcast the message to all the clients in the room except the sender (in this case only to the initiator)
                socket.to(room).emit('broadcast: joined', `client ${socket.id} joined room ${room}`);
                // send the message directly to the joiner
                socket.emit('joined', room);
            } else {
                // max two clients
                console.log("Room is full!");
                socket.emit('full', room);
            }
        });
    });

    // Handle 'Bye' messages
    socket.on('Bye', (room) => {
        // Notify other peer that a peer sent a Bye to end the chat
        // broadcast the message to all the clients in the room except the sender
        socket.to(room).emit('Bye');
        // Close socket from server's side
        socket.disconnect();
    });

    // Handle 'Ack' messages
    // The ack is sent from the client that receives the forwarded bye to acknowledge the end of the chat
    socket.on('Ack', () => {
        console.log('Got an Ack!');
        // Close socket from server's side
        socket.disconnect();
    });
})