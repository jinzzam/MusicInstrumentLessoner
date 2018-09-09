var mysql = require('mysql');

var connection = mysql.createConnection({
    user: 'root',
    password: '123',
    database: 'midb'
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

this.selectOne = (id, callback) => {
    var sql = 'select * from mi_notification where mi_notification_id =?';
    connection.query(sql, [id], function (err, rows, fields) {
        if (!err) {
            callback(rows);
        }
    });
};

this.insert = (notiid, tempid, type, comment, callback) => {
    var sql = 'insert into mi_notification ' +
        '(mi_notification_id, music_template_id, type, comment) values (?,?,?,?)';
    connection.query(sql, [notiid, tempid, type, comment], function (err, rows, fields) {
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