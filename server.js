const net = require('net')
const stream = require('stream')
const functions = require('./functions.js')
const spawn = require('child_process')

var server = net.createServer()
server.listen(8080)

//starting corenlp server
/*var command = 'java -mxg4 -cp "*" edu.stanford.nlp.pipeline.StanfordCoreNLPServer';
spawn.exec(command, (err) =>{
	if(err){
		console.log(err);
	}
});*/

server.on('connection', (socket) => {
	socket.on('data', (data) => {
		const dataParse = JSON.parse(data)
		console.log(`message à analyser : ${dataParse.msg}`)
		functions.analyze(dataParse)
/*		console.log(`réponse : ${rep}`)
		const msg = JSON.stringify({msg: rep})
		socket.write(msg)*/
	});


}).on('close', () => {
	console.log("server closed")
}).on('error', (err) => {
	console.log(err)
});







