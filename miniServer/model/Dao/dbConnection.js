var mysql = require('mysql');

var connection = mysql.createConnection({
    host: 'localhost',
    port: '3306',
    user: 'root',
    password: '123',
    database: 'midb'
});

connection.connect();
console.log('dbConnection');

module.exports = connection;