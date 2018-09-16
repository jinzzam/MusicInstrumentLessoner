var mysql = require('mysql');

var connection = mysql.createConnection({
    user : 'root',
    password : '',
    database : 'midb'
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

this.insert =(groupName, teacherEmail)=>{
    var sql = 'insert into mi_teacher values (?,?)';
    connection.query(sql, [groupName, teacherEmail], (err,rows,fields)=>{
        if(!err){
            callback(rows);
        } else{
            console.log('user post');
            console.log(err);
        }
    });
};