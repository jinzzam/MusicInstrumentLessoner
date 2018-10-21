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
    var sql = 'select * from music_template_practice';
    connection.query(sql, function (err, rows, fields) {
        if (!err) {
            callback(rows);
        }
    });
};

this.selectOne = (id, callback) => {
    var sql = 'select * from music_template_practice where music_template_practice_id =?';
    connection.query(sql, [id], function (err, rows, fields) {
        if (!err) {
            callback(rows);
        }
    });
};

this.insert = (tempId, sEmail, innerFile, is_done, completePercent, callback) => {
    var sql = 'insert into music_template_practice (music_template_id, student_email, inner_filename, is_done, complete_percent) values (?,?,?,?,?)';
    connection.query(sql, [tempId, sEmail, innerFile, is_done, completePercent], function (err, rows, fields) {
        if (!err) {
            callback(rows);
        } else {
            console.log('user post');
            console.log(err);
        }
    });
};

this.joinFile = (innerFilename, callback) => {
    var sql = 'select owner, inner_filename, outter_filename\n' +
        'from music_template_practice natural join mi_file\n' +
        'where music_template_practice.inner_filename = ?';
    connection.query(sql, [innerFilename], function (err, rows, fields) {
        if (!err) {
            callback(rows);
        }
    });
};

this.isDone = (practiceId, callback) => {
    var sql = 'update music_template_practice set is_done = 1 where music_template_practice_id =?';
    connection.query(sql, [practiceId], function (err, rows, fields) {
        if (!err) {
            callback(rows);
        }
    });
};

this.Complete = (percent, practiceId, callback) => {
    var sql = 'update music_template_practice set complete_percent = ? where music_template_practice_id =?';
    connection.query(sql, [percent, practiceId], function (err, rows, fields) {
        if (!err) {
            callback(rows);
        }
    });
};