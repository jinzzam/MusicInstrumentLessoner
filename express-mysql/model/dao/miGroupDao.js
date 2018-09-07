var mysql = require('mysql');

var connection = mysql.createConnection({
    user : 'root',
    password : '123',
    database : 'midb'
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