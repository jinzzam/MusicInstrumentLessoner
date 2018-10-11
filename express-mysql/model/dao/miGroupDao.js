var mysql = require('mysql');
var db_config = require('../.dbconfig');


var connection = mysql.createConnection({
    host: db_config.host,
    user: db_config.user,
    password: db_config.password,
    database: db_config.database
});

connection.connect();

this.selectAll=(callback)=>{
    var sql = 'select * from mi_group';
    connection.query(sql,function (err, rows, fields) {
        if(!err){
            callback(rows);
        }
    });
};

this.selectOne=(groupname, callback)=>{
    var sql = 'select * from mi_group where group_name=?';
    connection.query(sql,[groupname],function (err, rows, fields) {
        if(!err){
            callback(rows);
        }
    });
};

this.insert =(groupname, place, info, instruments, genre, callback)=>{
    var sql = 'insert into mi_group values (?,?,?,?,?)';
    connection.query(sql, [groupname, place, info, instruments, genre], (err,rows,fields)=>{
        if(!err){
            callback(rows);
        } else{
            console.log('user post');
            console.log(err);
        }
    });
};

this.join = (useremail, callback) => {
    var sql =
        'select mi_user.email, mi_user.username, mi_group.*, mi_teacher.teacher_email from ((mi_user join mi_student on mi_user.email = ?) join mi_group on mi_student.student_email = mi_user.email and mi_student.group_name= mi_group.group_name) join mi_teacher on mi_group.group_name = mi_teacher.group_name';
    connection.query(sql, [useremail], (err,rows,fields)=>{
        if(!err){
            callback(rows);
        }
    });
};

this.delete = (groupname, callback) => {
    var sql = 'delete from mi_group where group_name = ?';
    connection.query(sql, [groupname], function(err, rows, fields){
        if(!err){
            callback(rows);
        }
    });
};

this.infoupdate = (str, groupName, callback)=>{
    var sql = 'update mi_group set info = ? where group_name = ?';
    connection.query(sql, [str, groupName], function(err, rows, fields){
        if(!err){
            callback(rows);
        }
    });
};

this.placeupdate = (str, groupName, callback)=>{
    var sql = 'update mi_group set place = ? where group_name = ?';
    connection.query(sql, [str, groupName], function(err, rows, fields){
        if(!err){
            callback(rows);
        }
    });
};

this.insupdate = (str, groupName, callback)=>{
    var sql = 'update mi_group set instruments = ? where group_name = ?';
    connection.query(sql, [str, groupName], function(err, rows, fields){
        if(!err){
            callback(rows);
        }
    });
};

this.genreupdate = (str, groupName, callback)=>{
    var sql = 'update mi_group set genre = ? where group_name = ?';
    connection.query(sql, [str, groupName], function(err, rows, fields){
        if(!err){
            callback(rows);
        }
    });
};