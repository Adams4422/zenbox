var net = require('net');

var HOST = '127.0.0.1'; 
var PORT = 6969; //Port TCP

//instance of server
net.createServer( (socket) => {

	socket.on('connection', (data) => {
		console.log(`CONNECTED: ${socket.remoteAddress} : ${socket.remotePort}`);
	});

	socket.on('data', (data) =>{
		var msg = JSON.parse(data):
		console.log(msg);
	});

	socket.on('close', (data) =>{
		console.log(`CLOSED: ${socket.remoteAddress} : ${socket.remotePort}`)
	});
}).listen(PORT, HOST);

console.log('Server listening on '+ HOST +' : '+ PORT);