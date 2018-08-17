var mysql = require('mysql');

var connection = mysql.createConnection({
    user : 'root',
    password : '',
    database : 'midb'
});

connection.connect();

this.selectAll=(callback)=>{
    var sql = 'select * from mi_user';
    connection.query(sql,function (err, rows, fields) {
        if(!err){
            callback(rows);
        }
    });
};

this.selectOne=(email, callback)=>{
    var sql = 'select * from mi_user where email =?';
    connection.query(sql,[email],function (err, rows, fields) {
        if(!err){
            callback(rows);
        }
    });
};

this.insert =(email, password, username, callback)=>{
    var sql = 'insert into mi_user values (?,?,?)';
    connection.query(sql, [email,password,username], (err,rows,fields)=>{
        if(!err){
            callback(rows);
        } else{
            console.log('user post');
            console.log(err);
        }
    });
};