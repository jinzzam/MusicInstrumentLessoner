var mysql = require('mysql');

var connection = mysql.createConnection({
    user: 'root',
    password: '',
    database: 'midb'
});

connection.connect();

this.selectAll = (callback) => {
    var sql = 'select * from mi_file';
    connection.query(sql, function (err, rows, fields) {
        if (!err) {
            callback(rows);
        }
    });
};

this.selectOne = (filename, callback) => {
    var sql = 'select * from mi_file where inner_filename =?';
    connection.query(sql, [filename], function (err, rows, fields) {
        if (!err) {
            callback(rows);
        }
    });
};

this.insert = (email, filename, callback) => {
    var sql = 'insert into mi_file values (?,?)';
    connection.query(sql, [email, filename], (err, rows, fields) => {
        if (!err) {
            callback(rows);
        } else {
            console.log('user post');
            console.log(err);
        }
    });
};

this.joinUser = (ownerEmail, callback) => {
    var sql = 'select mi_file.owner, mi_user.username,\n' +
        'mi_file.inner_filename, mi_file.outter_filename\n' +
        'from mi_user natural join mi_file \n' +
        'where mi_user.email = ?';
    connection.query(sql, [ownerEmail], function (err, rows, fields) {
        if (!err) {
            callback(rows);
        }
    });
};

this.joinWrong = (filename, callback) => {
    var sql = 'select inner_filename, wrong_time_start, wrong_time_end\n' +
        'from music_template_wrong natural join mi_file\n' +
        'where mi_file.inner_filename = ?';
    connection.query(sql, [filename], function (err, rows, fields) {
        if (!err) {
            callback(rows);
        }
    });
};
