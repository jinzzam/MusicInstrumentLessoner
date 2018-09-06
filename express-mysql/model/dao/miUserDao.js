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

this.join =(callback)=>{
    var sql = 'select mi_user.email, mi_user.username, music_template.music_title FROM mi_user LEFT JOIN music_template ON mi_user.email = music_template.owner = ?';
    connection.query(sql, (err, result)=>{
        if(!err){
            callback(rows);
        } else {
            
        }
    })
};

