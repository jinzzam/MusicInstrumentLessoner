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
    var sql = 'select * from mi_teacher';
    connection.query(sql,function (err, rows, fields) {
        if(!err){
            callback(rows);
        }
    });
};

this.selectOne=(groupName, callback)=>{
    var sql = 'select * from mi_teacher where group_name=?';
    connection.query(sql,[groupName],function (err, rows, fields) {
        if(!err){
            callback(rows);
        }
    });
};

this.insert =(teacherEmail,groupName)=>{
    var sql = 'insert into mi_teacher values (?,?)';
    connection.query(sql, [teacherEmail, groupName], (err,rows,fields)=>{
        if(!err){
            callback(rows);
        } else{
            console.log('user post');
            console.log(err);
        }
    });
};

 this.delete= (temail, callback) => {
     var sql = 'delete from mi_teacher where teacher_email = ?';
     connection.query(sql, [temail], (err, rows, fields)=>{
         if(!err){
             callback(rows);
         } else {
             console.log('err');
         }
     });
 };