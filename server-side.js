var net = require('net');
var spawn = require('child_process');

var HOST = '127.0.0.1';
var PORT = 6969; //Port TCP

//instance of server
var server = net.createServer();
var commande = "docker run \
  --net=host \
  -v /etc/localtime:/etc/localtime:ro \
  -v `pwd`:/usr/src/app \
  -t -i --rm tradfri-dev";

	spawn.exec(commande, (error) =>{
		if (error){
			console.log(error);
		}
	})

	var commande2 = "docker exec -it <ebfa6281c5fd> python3 -i -m pytradfri 192.168.1.128";
		spawn.exec(commande2, (error) =>{
			if (error){
				console.log(error);
			}
		})

server.on('connection',function(socket){
	console.log('CONNECTED: ' + socket.remoteAddress +':'+ socket.remotePort);

	socket.on('data', (data) =>{
		//var msg = JSON.parse(data);
		socket.write(data);
		var message = data.toString();
		console.log("la commande est : " + message);

		if (message.equals("off"){
			var commande3 = "docker exec -it <ebfa6281c5fd> api(light.light_control.set_dimmer(0))";
				spawn.exec(commande3, (error) =>{
					if (error){
						console.log(error);
					}
				})

		})

		if (message.equals("on"){
			var commande4 = "docker exec -it <ebfa6281c5fd> api(light.light_control.set_dimmer(254))";
				spawn.exec(commande4, (error) =>{
					if (error){
						console.log(error);
					}
				})

		})
	});

	socket.on('close', (data) =>{
		console.log(`CLOSED: ${socket.remoteAddress} : ${socket.remotePort}`)
	});
});

server.listen(PORT, HOST);

console.log('Server listening on '+ HOST +' : '+ PORT);
