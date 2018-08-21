this.selectAll = (callback) => {
    var sql = 'select * from mi_notification';
    connection.query(sql, function (err, rows, fields) {
        if (!err) {
            callback(rows);
        }
    });
};

this.selectOne = (id, callback) => {
    var sql = 'select * from mi_notification where mi_notification_id =?';
    connection.query(sql, [id], function (err, rows, fields) {
        if (!err) {
            callback(rows);
        }
    });
};

this.insert = (notiid, tempid, type, comment, callback) => {
    var sql = 'insert into mi_notification ' +
        '(mi_notification_id, music_template_id, type, comment) values (?,?,?,?)';
    connection.query(sql, [notiid, tempid, type, comment], function (err, rows, fields) {
        if (!err) {
            callback(rows);
        } else {
            console.log('user post');
            console.log(err);
        }
    });
};