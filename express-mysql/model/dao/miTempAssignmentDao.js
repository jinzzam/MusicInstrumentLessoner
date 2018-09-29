var mysql = require('mysql');

var connection = mysql.createConnection({
    user: 'root',
    password: '',
    database: 'midb'
});

connection.connect();

this.selectAll = (callback) => {
    var sql = 'select * from music_template_assignment';
    connection.query(sql, function (err, rows, fields) {
        if (!err) {
            callback(rows);
        }
    });
};

this.selectOne = (studentEmail, callback) => {
    var sql = 'select * from music_template_assignment natural join music_template\n' +
        'where music_template_assignment.student_email = ?';
    connection.query(sql, [studentEmail], function (err, rows, fields) {
        if (!err) {
            callback(rows);
        }
    });
};

this.insert = (innerFilename, tempId,studentEmail, todo, done, sPercent, callback) => {
    var sql = 'insert into music_template_assignment values (?,?,?,?,?,?)';
    connection.query(sql, [innerFilename, tempId,studentEmail, todo, done, sPercent], function (err, rows, fields) {
        if (!err) {
            callback(rows);
        } else {
            console.log('user post');
            console.log(err);
        }
    });
};

this.count = (filename,callback)=>{
    var sql = 'update music_template_assignment set done_count = done_count + 1 where inner_filename =?';
    connection.query(sql, [filename], function(err, rows, fields){
        if(!err){
            callback(rows);
        } else {
            console.log('user post');
            console.log(err);
        }
    });
};

this.delete = (filename, callback)=>{
    var sql = 'delete from music_template_assignment where inner_filename = ?';
    connection.query(sql, [filename], function(err, rows, fields){
        if(!err){
            callback(rows);
        } else {
            console.log('user post');
            console.log(err);
        }
    });
};

this.complete = (percent, filename, callback)=>{
    var sql = 'update music_template_assignment set success_percent=? where inner_filename =?';
    connection.query(sql, [percent, filename], function(err, rows, fields){
        if(!err){
            callback(rows);
        } else {
            console.log('user post');
            console.log(err);
        }
    });
};
