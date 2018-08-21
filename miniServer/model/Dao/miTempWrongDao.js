this.selectAll = (callback) => {
    var sql = 'select * from music_template_wrong';
    connection.query(sql, function (err, rows, fields) {
        if (!err) {
            callback(rows);
        }
    });
};

this.selectOne = (filename, time, callback) => {
    var sql = 'select * from music_template_wrong where assignment_inner_filename=? and play_time=?';
    connection.query(sql, [filename, time], function (err, rows, fields) {
        if (!err) {
            callback(rows);
        }
    });
};

this.insert = (filename, time, callback) => {
    var sql = 'insert into music_template_wrong values (?,?)';
    connection.query(sql, [filename, time], (err, rows, fields) => {
        if (!err) {
            callback(rows);
        } else {
            console.log('user post');
            console.log(err);
        }
    });
};