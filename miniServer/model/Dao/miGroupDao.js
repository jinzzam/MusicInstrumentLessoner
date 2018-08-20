this.selectAll = (callback) => {
    var sql = 'select * from mi_group';
    connection.query(sql, function (err, rows, fields) {
        if (!err) {
            callback(rows);
        }
    });
};

this.selectOne = (groupname, callback) => {
    var sql = 'select * from mi_group where group_name=?';
    connection.query(sql, [filename, time], function (err, rows, fields) {
        if (!err) {
            callback(rows);
        }
    });
};

this.insert = (groupname, groupinfo, isteacher, callback) => {
    var sql = 'insert into mi_group values (?,?,?)';
    connection.query(sql, [groupname, groupinfo, isteacher], (err, rows, fields) => {
        if (!err) {
            callback(rows);
        } else {
            console.log('user post');
            console.log(err);
        }
    });
};