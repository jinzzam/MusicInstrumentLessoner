this.selectAll = (callback) => {
    var sql = 'select * from music_template_assignment';
    connection.query(sql, function (err, rows, fields) {
        if (!err) {
            callback(rows);
        }
    });
};

this.selectOne = (filename, callback) => {
    var sql = 'select * from music_template_assignment where inner_filename =?';
    connection.query(sql, [filename], function (err, rows, fields) {
        if (!err) {
            callback(rows);
        }
    });
};

this.insert = (filename, tempid, callback) => {
    var sql = 'insert into music_template_assignment values (?,?)';
    connection.query(sql, [filename, tempid], function (err, rows, fields) {
        if (!err) {
            callback(rows);
        } else {
            console.log('user post');
            console.log(err);
        }
    });
};