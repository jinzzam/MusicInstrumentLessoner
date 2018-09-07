var mysql = require('mysql');

var connection = mysql.createConnection({
    user : 'root',
    password : '123',
    database : 'midb'
});

connection.connect();

this.selectAll=(callback)=>{
    var sql = 'select * from mi_file';
    connection.query(sql,function (err, rows, fields) {
        if(!err){
            callback(rows);
        }
    });
};

this.selectOne=(filename, callback)=>{
    var sql = 'select * from mi_file where inner_filename =?';
    connection.query(sql,[filename],function (err, rows, fields) {
        if(!err){
            callback(rows);
        }
    });
};

this.insert =(email, filename, callback)=>{
    var sql = 'insert into mi_file values (?,?)';
    connection.query(sql, [email,filename], (err,rows,fields)=>{
        if(!err){
            callback(rows);
        } else{
            console.log('user post');
            console.log(err);
        }
    });
};