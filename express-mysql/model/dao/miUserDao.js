var mysql = require('mysql');

var connection = mysql.createConnection({
    user: 'root',
    password: '123',
    database: 'midb'
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

