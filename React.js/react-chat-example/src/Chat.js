import {useState, useEffect, useRef} from "react";
import io from 'socket.io-client';
import toast from "react-hot-toast";
// Icons
import {RiGroup2Fill} from 'react-icons/ri';
import {FaUserCircle} from 'react-icons/fa';

/*
const fakeMessages = [
  {
    data: "Lorem ipsum False",
    isMine: false,
  },
  {
    data: "Lorem ipsum False",
    isMine: false,
  },
  {
    data: "Lorem ipsum",
    isMine: true,
  },
  {
    data: "Lorem ipsum",
    isMine: true,
  },
  {
    data: "Lorem ipsum False",
    isMine: false,
  },
  {
    data: "Lorem ipsum",
    isMine: true,
  },
]
*/

const socket = io("http://localhost:8181");

const Chat = () => {
    const [isConnected, setIsConnected] = useState(socket.connected);
    const [room, setRoom] = useState();
    const roomNameRef = useRef();
    const chatMessageRef = useRef();
    /*
      Every message in the 'messages' array is an object with the following keys:
      data: a string containing the message to be displayed
      isMine: a boolean value to check if the current user is the sender of the message
    */
    const [messages, setMessages] = useState([]);

    useEffect(() => {
        socket.on('connect', () => {
            setIsConnected(true);
        });

        socket.on('disconnect', () => {
            setIsConnected(false);
        });

        //Handle 'created' message
        socket.on('created', (room) => {
            console.log(`room ${room} has been created!`);
            console.log('This peer is the initiator...');
            toast.success(`Room ${room} has been created!`);
            toast.success("This peer is the initiator...")
        });

        //Handle 'full' message
        socket.on('full', (room) => {
            console.log(`room ${room} is too crowded!`);
            toast.error(`Room '${room}' is too crowded!`)
            setRoom(); // This is not production-ready!
        });

        //Handle 'remotePeerJoining' message
        socket.on('remotePeerJoining', (room) => {
            console.log(`Request to join ${room}`);
            console.log('You are the initiator!');

            toast.success(`Message from server: request to join room '${room}'`)
        });

        //Handle 'broadcast: joined' message
        socket.on('broadcast: joined', (msg) => {
            console.log(`Broadcast message from server: ${msg}`);
            toast.success(`Broadcast message from server: ${msg}`)
        });

        //Handle 'message' message
        socket.on('message', (message) => {
            console.log(`Got message from other peer: ${message}`);
            const _message = {
                data: message, isMine: false,
            }
            setMessages((_messages) => [..._messages, _message])
        });

        return () => {
            socket.off('connect');
            socket.off('disconnect');
            socket.off('pong');
        };
    }, []);

    const joinOrCreate = () => {
        if (!roomNameRef.current.value || !isConnected) {
            return
        }

        const _room = roomNameRef.current.value;
        console.log(`Trying to create or join room: ${_room}`);
        // Send 'create or join' to the server
        socket.emit('create or join', _room);
        setRoom(_room);
    }

    const sendMessage = () => {
        if (!chatMessageRef.current.value || !isConnected) {
            return
        }

        const chatMessage = chatMessageRef.current.value;
        socket.emit('message', {
            room: room, message: chatMessage
        });
        chatMessageRef.current.value = ''

        // Add new message to the 'messages' array
        const _message = {
            data: chatMessage, isMine: true,
        }
        setMessages((_messages) => [..._messages, _message])
    }

    if (!room) {
        return (<main className="bg-white  rounded-md py-10 px-6">
            <h3 className="text-4xl font-semibold text-slate-800 text-center mb-4">Enter signaling room name</h3>
            <input
                className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline mb-4"
                type="text" placeholder="Room Name" ref={roomNameRef}/>
            <button className="bg-sky-600 hover:bg-sky-800 w-full rounded-md py-2" onClick={joinOrCreate}>Confirm
            </button>
        </main>)
    } else {
        return (<main className="bg-white w-full h-full flex rounded-md">

            {/* Chat */}
            <div className="flex-1 p:2 sm:p-6 justify-between flex flex-col card">

                {/* User info */}
                <div className="flex sm:items-center justify-between pb-3 border-b-2 border-gray-200">
                    <div className="relative flex items-center space-x-4">
                        <div className="relative">
                            <RiGroup2Fill size="72" color="rgb(15 23 42)"/>
                        </div>
                        <div className="flex flex-col leading-tight">
                            <div className="text-2xl mt-1 flex items-center">
                                <span className="text-gray-700 mr-3">{room}</span>
                            </div>
                        </div>
                    </div>
                </div>

                {/* Chat messages */}
                <div id="messages"
                     className="flex flex-1 flex-col space-y-4 p-8 overflow-y-auto scrollbar-thumb-blue scrollbar-thumb-rounded scrollbar-track-blue-lighter scrollbar-w-2 scrolling-touch">
                    {messages.map((message, key) => {
                        return (<div className="chat-message" key={key}>
                            <div className={`flex items-end ${message.isMine && "justify-end"}`}>
                                <div
                                    className={`flex flex-col space-y-2 text-base max-w-xs mx-2 order-2 ${message.isMine ? "items-end" : "items-start"}`}>
                                    <div><span
                                        className={`px-4 py-2 rounded-lg inline-block  bg-sky-500 text-white break-all ${message.isMine ? "rounded-br-none" : "rounded-bl-none"}`}>{message.data}</span>
                                    </div>
                                </div>
                                <FaUserCircle color="rgb(15 23 42)"
                                              className={`w-6 h-6 rounded-full ${message.isMine ? "order-2" : "order-1"}`}/>
                            </div>
                        </div>)
                    })}
                </div>

                {/* Input msg */}
                <div className="border-t-2 border-gray-200 pt-4 mb-2 sm:mb-0">
                    <div className="relative flex">
                        <input
                            type="text"
                            ref={chatMessageRef}
                            placeholder="Write your message..."
                            className="w-full focus:outline-none focus:placeholder-gray-400 text-gray-600 placeholder-gray-600 pl-4 bg-gray-200 rounded-md py-3"/>

                        <div className="absolute right-0 items-center inset-y-0 sm:flex">
                            <button
                                type="button"
                                onClick={sendMessage}
                                className="inline-flex items-center justify-center rounded-lg px-4 py-3 transition duration-500 ease-in-out text-white bg-sky-600 hover:bg-sky-800 focus:outline-none">
                                <span className="font-bold">Send</span>
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor"
                                     className="h-6 w-6 ml-2 transform rotate-90">
                                    <path
                                        d="M10.894 2.553a1 1 0 00-1.788 0l-7 14a1 1 0 001.169 1.409l5-1.429A1 1 0 009 15.571V11a1 1 0 112 0v4.571a1 1 0 00.725.962l5 1.428a1 1 0 001.17-1.408l-7-14z"></path>
                                </svg>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </main>);
    }
}

export default Chat;