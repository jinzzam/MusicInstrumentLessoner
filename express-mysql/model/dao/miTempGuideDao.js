var mysql = require('mysql');

var connection = mysql.createConnection({
    user : 'root',
    password : '123',
    database : 'midb'
});

connection.connect();

this.selectAll=(callback)=>{
    var sql = 'select * from music_template_guide';
    connection.query(sql,function (err, rows, fields) {
        if(!err){
            callback(rows);
        }
    });
};

this.selectOne=(id, callback)=>{
    var sql = 'select * from music_template_guide where music_template_id =?';
    connection.query(sql,[id],function (err, rows, fields) {
        if(!err){
            callback(rows);
        }
    });
};

this.insert =(id,playTime, comment, callback)=>{
    var sql = 'insert into music_template_guide (music_template_id, play_time, comment) values (?,?,?)';
    connection.query(sql, [id, playTime, comment], function(err,rows,fields){
        if(!err){
            callback(rows);
        } else{
            console.log('user post');
            console.log(err);
        }
    });
};