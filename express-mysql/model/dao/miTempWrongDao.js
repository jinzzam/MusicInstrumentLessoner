var mysql = require('mysql');
var db_config = require('../dbconfig');


var connection = mysql.createConnection({
    host: db_config.host,
    user: db_config.user,
    password: db_config.password,
    database: db_config.database,
});

connection.connect();

this.selectAll = (callback) => {
    var sql = 'select * from music_template_wrong';
    connection.query(sql, function (err, rows, fields) {
        if (!err) {
            callback(rows);
        }
    });
};

this.selectOne = (filename, time, callback) => {
    var sql = 'select * from music_template_wrong where assignment_inner_filename=? and play_time=?';
    connection.query(sql, [filename, time], function (err, rows, fields) {
        if (!err) {
            callback(rows);
        }
    });
};

this.insert = (filename, startTime, endTime, callback) => {
    var sql = 'insert into music_template_wrong values (?,?,?)';
    connection.query(sql, [filename, startTime, endTime], (err, rows, fields) => {
        if (!err) {
            callback(rows);
        } else {
            console.log('user post');
            console.log(err);
        }
    });
};