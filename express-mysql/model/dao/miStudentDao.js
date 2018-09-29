var mysql = require('mysql');

var connection = mysql.createConnection({
    user : 'root',
    password : '',
    database : 'midb'
});

connection.connect();

this.selectAll=(callback)=>{
    var sql = 'select * from mi_student';
    connection.query(sql,function (err, rows, fields) {
        if(!err){
            callback(rows);
        }
    });
};

this.selectOne=(groupName, callback)=>{
    var sql = 'select * from mi_student where group_name=?';
    connection.query(sql,[groupName],function (err, rows, fields) {
        if(!err){
            callback(rows);
        }
    });
};

this.insert =(sEmail, groupName, callback)=>{
    var sql = 'insert into mi_student values (?,?)';
    connection.query(sql, [sEmail, groupName], (err,rows,fields)=>{
        if(!err){
            callback(rows);
        } else{
            console.log('user post');
            console.log(err);
        }
    });
};

this.delete =( sEmail, callback)=>{
    var sql = 'delete from mi_student where student_email = ?';
    connection.query(sql, [sEmail], (err,rows,fields)=>{
        if(!err){
            callback(rows);
        } else{
            console.log('user post');
            console.log(err);
        }
    });
};