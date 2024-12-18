var http = require("http");
var users = [{ id: 1, firstName: 'Marko', lastName: 'Markovic' }]
var counter = 1;

var removeUserById = function (id) {
	var index = users.map(function (x) { return x.id; }).indexOf(id);
	users.splice(index, 1);
}

var findUserById = function(id){
	return users.filter(function (x){return x.id==id})[0];
}

http.createServer(function (req, res) {
	//GET id
	if(req.url.indexOf('/users') === 0 && req.url.replace('/users') !== '' && req.method === 'GET'){
		let id = +req.url.replace('/users/', '');
		res.writeHead(200, { 'Content-Type': 'application/json' });
		res.end(JSON.stringify(findUserById(id)));
	}
	//GET
	else if (req.url === '/users' && req.method === 'GET') {
		res.writeHead(200, { 'Content-Type': 'application/json' });
		res.end(JSON.stringify(users));
	}
	//POST
	else if (req.url === '/users' && req.method === 'POST') {
		//request implementira ReadableStream interface
		//sto znaci da moze da se osluskuje pozivom metode on 
		//na 'data' (pristigao chunk podataka)
		//i na 'end' (zavrsen prenos). 
		let body = [];
		req.on('data', (chunk) => {
			body.push(chunk);
		}).on('end', () => {
			body = Buffer.concat(body).toString();
			let user = JSON.parse(body);
			user.id = ++counter;
			users.push(user);
			res.writeHead(200, { 'Content-Type': 'application/json' });
			res.end(JSON.stringify(user));
		});
	}
	//DELETE
	else if (req.url.indexOf('/users') === 0 && req.method === 'DELETE') {
		let id = +req.url.replace('/users/', '');
		removeUserById(id);
		res.writeHead(200, { 'Content-Type': 'application/json' });
		res.end(JSON.stringify(users));
	}
	//PUT
	else if (req.url === '/users' && req.method === 'PUT') {
		let body = [];
		req.on('data', (chunk) => {
			body.push(chunk);
		}).on('end', () => {
			body = Buffer.concat(body).toString();
			let newUser = JSON.parse(body);
			let user = findUserById(newUser.id);
			user.firstName=newUser.firstName;
			user.lastName=newUser.lastName;
			res.writeHead(200, { 'Content-Type': 'application/json' });
			res.end(JSON.stringify(user));
		});
	}
	else {
		res.writeHead(404, { 'Content-Type': 'text/plain' });
		res.end('Not found');
	}
}).listen(8080);

console.log("server is running on port 8080");
