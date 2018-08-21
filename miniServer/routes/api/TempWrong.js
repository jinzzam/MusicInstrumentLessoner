var express = require('express');
var router = express.Router();
var async = require('async');
var miTempWrong = require('../../model/dao/miTempWrongDao');

/* GET home page. */
router.get('/', function (req, res, next) {
    var data;
    const task1 = function (callback) {
        miTempWrong.selectAll(function (rows) {
            data = rows;
            callback(null);
        });
    };
    const task2 = function (callback) {
        console.log(data);
        callback(null);
    };
    const task3 = function (callback) {
        res.send(data);
        callback(null);
    };
    const tasks = [task1, task2, task3];
    async.series(tasks);
});

router.get('/find/:filename/:time', function (req, res, next) {
    var fileName = req.params['filename'];
    var time = req.params['time'];
    var data;
    const task1 = function (callback) {
        miTempWrong.selectOne(fileName, time, function (rows) {
            data = rows;
            callback(null);
        });
    };
    const task2 = function (callback) {
        console.log(data);
        callback(null);
    };
    const task3 = function (callback) {
        res.send(data);
        callback(null);
    };
    const tasks = [task1, task2, task3];
    async.series(tasks);
});

router.get('/:filename/:time', function (req, res, next) {
    var fileName = req.params['filename'];
    var playTime = req.params['time'];
    const task1 = function (callback) {
        miTempWrong.insert(fileName, playTime, function (rows) {
            callback(null);
        });
        res.send('Hello World');
    };
    const tasks = [task1];
    async.series(tasks);
});

module.exports = router;
