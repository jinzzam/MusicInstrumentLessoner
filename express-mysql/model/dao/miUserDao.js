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
    var sql = 'select * from mi_user';
    connection.query(sql, function (err, rows, fields) {
        if (!err) {
            callback(rows);
        }
    });
};

this.selectOne = (email, callback) => {
    var sql = 'select * from mi_user where email =?';
    connection.query(sql, [email], function (err, rows, fields) {
        if (!err) {
            callback(rows);
        }
    });
};

this.insert = (email, password, username, callback) => {
    var sql = 'insert into mi_user values (?,?,?)';
    connection.query(sql, [email, password, username], (err, rows, fields) => {
        if (!err) {
            callback(rows);
        } else {
            console.log('user post');
            console.log(err);
        }
    });
};

this.join = (email, callback) => {
    var sql = 'select music_template_id, owner, music_title, musician, guide from mi_user right join music_template on mi_user.email = music_template.owner where mi_user.email= ?';
    connection.query(sql, [email], function (err, rows, fields) {
        if (!err) {
            callback(rows);
        }
    });
};

this.Eupdate = (updateEmail, email, callback) => {
    var sql = 'update mi_user set email = ? where email = ?';
    connection.query(sql, [updateEmail, email], function (err, rows, fields) {
        callback(rows);
    });
};

this.Nupdate = (updateName, email, callback) => {
    var sql = 'update mi_user set username = ? where email = ?';
    connection.query(sql, [updateName, email], function (err, rows, fields) {
        if (!err) {
            callback(rows);
        }
    });
};

this.Pupdate = (updatePass, email, callback) => {
    var sql = 'update mi_user set password = ? where email = ?';
    connection.query(sql, [updatePass, email], function (err, rows, fields) {
        if (!err) {
            callback(rows);
        }
    });
};

this.delete = (email, callback) => {
    var sql = 'delete from mi_user where email = ?';
    connection.connect(sql, [email], function (err, rows, fields) {
        if (!err) {
            callback(rows);
        }
    });
};
