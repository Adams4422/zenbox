const net = require('net')
const stream = require('stream')
const functions = require('./functions.js')

var socket = new net.Socket();

//Entrées au clavier
var readable = new stream.Readable();
	readable.setEncoding('utf8');
	readable._read = function(buff){//Read synch
		process.stdin.removeAllListeners('data').on('data', function(data){
		readable.push(data);
		});
	};

	readable.on('data', function(data){
		const dataClean = data.replace(/\r|\n/g, "")
		const dataSplit = dataClean.split("/")

		const mess = JSON.stringify({msg: dataClean})
		socket.write(mess)
	});


//Connection au server
socket.connect(8080, "127.0.0.1")


socket.on('connect', () => {
	console.log("Connected ")

	socket.on('data', (data) => {
		dataParse = JSON.parse(data)
		console.log(dataParse.msg)
	});

}).on('error', (err) => {
	console.log(err);

}).on('end', () => {
	process.stdin.pause();
});