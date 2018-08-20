var express = require('express');
var router = express.Router();
var async = require('async');
var miUser = require('../model/Dao/miUserDao');

/* GET home page. */
router.get('/hi', function (req, res, next) {
    var data;
    const task1 = function (callback) {
        miUser.selectAll(function (rows) {
            data = rows;
            //console.log(rows);
            callback(null);
        });
    };
    const task2 = function (callback) {
        console.log(data);
        callback(null);
    };
    const task3 = function (callback) {
        // res.render('index', {title: 'Express', mydata: data});
        res.send(data);
        callback(null);
    };
    const tasks = [task1, task2, task3];
    async.series(tasks);
});
//
// router.get('/hi', function (req, res, next) {
//    res.send(req.query.email);
// });

module.exports = router;