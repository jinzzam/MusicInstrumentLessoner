var mysql = require('mysql');

var connection = mysql.createConnection({
    user: 'root',
    password: '123',
    database: 'midb'
});

connection.connect();

this.selectAll = (callback) => {
    var sql = 'select * from music_template';
    connection.query(sql, function (err, rows, fields) {
        if (!err) {
            callback(rows);
        }
    });
};

this.selectOneById = (id, callback) => {
    var sql = 'select * from music_template where music_template_id =?';
    connection.query(sql, [id], function (err, rows, fields) {
        if (!err) {
            callback(rows);
        }
    });
};

this.selectOneByEmail = (email, callback) => {
    var sql = 'select * from music_template where owner = ?';
    connection.query(sql, [email], function (err, rows, fields) {
        if (!err) {
            callback(rows);
        }
    });
};

this.insert = (owner, title, musician, callback) => {
    var sql = 'insert into music_template (owner,music_title,musician) values (?,?,?)';
    connection.query(sql, [owner, title, musician], function (err, rows, fields) {
        if (!err) {
            callback(rows);
        } else {
            console.log('user post');
            console.log(err);
        }
    });
};