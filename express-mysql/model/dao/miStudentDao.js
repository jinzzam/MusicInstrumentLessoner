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

this.selectOne=(groupname, callback)=>{
    var sql = 'select * from mi_student where group_name=?';
    connection.query(sql,[groupname],function (err, rows, fields) {
        if(!err){
            callback(rows);
        }
    });
};

this.insert =(groupname, sEmail, callback)=>{
    var sql = 'insert into mi_student values (?,?)';
    connection.query(sql, [groupname, sEmail], (err,rows,fields)=>{
        if(!err){
            callback(rows);
        } else{
            console.log('user post');
            console.log(err);
        }
    });
};