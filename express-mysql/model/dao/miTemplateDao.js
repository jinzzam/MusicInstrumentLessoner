var mysql = require('mysql');
var db_config = require('../.dbconfig');


var connection = mysql.createConnection({
    host: db_config.host,
    user: db_config.user,
    password: db_config.password,
    database: db_config.database
});

connection.connect();

this.selectAll = (callback) => {
    var sql = 'select * from music_template';
    connection.query(sql, function (err, rows, fields) {
        if (!err) {
            callback(rows);
        }
    });
};

this.selectOneById = (id, callback) => {
    var sql = 'select * from music_template where music_template_id =?';
    connection.query(sql, [id], function (err, rows, fields) {
        if (!err) {
            callback(rows);
        }
    });
};

this.selectOneByEmail = (email, callback) => {
    var sql = 'select * from music_template where owner = ?';
    connection.query(sql, [email], function (err, rows, fields) {
        if (!err) {
            callback(rows);
        }
    });
};

this.insert = (owner, title, musician, callback) => {
    var sql = 'insert into music_template (owner,music_title,musician) values (?,?,?)';
    connection.query(sql, [owner, title, musician], function (err, rows, fields) {
        if (!err) {
            callback(rows);
        } else {
            console.log('user post');
            console.log(err);
        }
    });
};

this.joinGuide = (musicTemplateId, callback) => {
    var sql = "select music_template_guide.music_template_id, music_template.music_title,\n" +
        "music_template_guide.play_time, music_template_guide.comment\n" +
        "from music_template natural join music_template_guide\n" +
        "where music_template_guide.music_template_id = ?";
    connection.query(sql, [musicTemplateId], function (err, rows, fields) {
        if (!err) {
            callback(rows);
        }
    });
};

this.joinAssignment = (musicTemplateId, callback) => {
    var sql = 'select inner_filename, music_template_id,\n' +
        'to_do_count, done_count, success_percent \n' +
        'from music_template natural join music_template_assignment\n' +
        'where music_template.music_template_id = ?';
    connection.query(sql, [musicTemplateId], function (err, rows, fields) {
        if (!err) {
            callback(rows);
        }
    });
};

this.joinPractice = (musicTemplateId, callback) => {
    var sql = 'select music_template_practice_id,\n' +
        'music_template_id, student_email, inner_filename,\n' +
        'is_done, complete_percent\n' +
        'from music_template natural join music_template_practice\n' +
        'where music_template.music_template_id = ?';
    connection.query(sql, [musicTemplateId], function (err, rows, fields) {
        if (!err) {
            callback(rows);
        }
    });
};

this.Gupdate = (guideupdate, musicTemplateId, callback) => {
    var sql = 'update music_template set guide =? where music_template_id = ?';
    connection.connect(sql, [guideupdate, musicTemplateId], function (err, rows, fields){
        if(!err){
            callback(rows);
        }
    });
};

this.tempDelete = (musicTemplateId, callback) => {
    var sql = 'delete from music_template where music_template_id = ?';
    connection.query(sql, [musicTemplateId], function (err, rows, fields){
        if(!err){
            callback(rows);
        }
    });
};