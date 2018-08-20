this.selectAll = (callback) => {
    var sql = 'select * from music_template';
    connection.query(sql, function (err, rows, fields) {
        if (!err) {
            callback(rows);
        }
    });
};

this.selectOne = (id, callback) => {
    var sql = 'select * from music_template where music_template_id =?';
    connection.query(sql, [id], function (err, rows, fields) {
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