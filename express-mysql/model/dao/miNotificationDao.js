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
    var sql = 'select * from mi_notification';
    connection.query(sql, function (err, rows, fields) {
        if (!err) {
            callback(rows);
        }
    });
};

this.selectOne = (notiId, callback) => {
    var sql = 'select * from mi_notification where mi_notification_id =?';
    connection.query(sql, [notiId], function (err, rows, fields) {
        if (!err) {
            callback(rows);
        }
    });
};

this.insert = (tempid, type, comment, callback) => {
    var sql = 'insert into mi_notification ' +
        '(music_template_id, type, comment) values (?,?,?)';
    connection.query(sql, [tempid, type, comment], function (err, rows, fields) {
        if (!err) {
            callback(rows);
        } else {
            console.log('user post');
            console.log(err);
        }
    });
};

this.joinTemplate = (musicTemplateId, callback) => {
    var sql = 'select music_template_id, owner, music_title, musician\n' +
        'from music_template natural join mi_notification\n' +
        'where music_template.music_template_id = ?';
    connection.query(sql, [musicTemplateId], function (err, rows, fields) {
        if (!err) {
            callback(rows);
        }
    });
};

this.delete = (notiid, callback) => {
    var sql = 'delete from mi_notification where mi_notification_id = ?';
    connection.query(sql, [notiid], function (err, rows, fields) {
        if (!err) {
            callback(null);
        }
    });
};