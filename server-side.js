var net = require('net');
var spawn = require('child_process');

var HOST = '127.0.0.1';
var PORT = 6969; //Port TCP

//instance of server
var server = net.createServer();


server.on('connection',function(socket){
	console.log('CONNECTED: ' + socket.remoteAddress +':'+ socket.remotePort);

	socket.on('data', (data) =>{
		//var msg = JSON.parse(data);
		//socket.write(data()
		var donnees = data.toString();
		var tab = donnees.split('\n');
		var message = tab[0]
		console.log("la commande est : " + message);

		if (message == 'off'){
			console.log("test")
			var commande = "docker run \
			--net=host \
			-v /etc/localtime:/etc/localtime:ro \
			-v `pwd`:/usr/src/app \
			tradfri-dev \
			echo 'api(light.light_control.set_dimmer(0))'|python3 -i -m pytradfri 192.168.1.128 \
			";

			spawn.exec(commande, (err, stdout, stderr) =>{
					if (err){
						console.log(err);
						return;
					}
					console.log(stdout);
			})
		}

		if (message == 'on'){
			var commande = "docker run \
			--net=host \
			-v /etc/localtime:/etc/localtime:ro \
			-v `pwd`:/usr/src/app \
			tradfri-dev \
			echo 'api(light.light_control.set_dimmer(250))'|python3 -i -m pytradfri 192.168.1.128 \
			";

			spawn.exec(commande, (err, stdout, stderr) =>{
					if (err){
						console.log(err);
						return;
					}
					console.log(stdout);
			})

		}
	});

});

server.listen(PORT, HOST);

console.log('Server listening on '+ HOST +' : '+ PORT);
