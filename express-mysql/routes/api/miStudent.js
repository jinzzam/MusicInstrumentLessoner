var express = require('express');
var router = express.Router();
var async = require('async');
var miStudent = require('../../model/dao/miStudentDao');

/* GET home page. */
router.get('/', function (req, res, next) {
    var data;
    const task1 = function (callback) {
        miStudent.selectAll(function (rows) {
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

router.get('/:groupName', function (req, res, next) {
    var groupName = req.params['groupName'];
    var data;
    const task1 = function (callback) {
        miStudent.selectOne(groupName, function (rows) {
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

router.get('/:groupname/:semail', function (req, res, next) {
    var groupName = req.params['groupname'];
    var sEmail = req.params['semail'];
    const task1 = function (callback) {
        miStudent.insert(groupName, sEmail, function (rows) {
            callback(null);
        });
        res.send('Hello World');
    };
    const tasks = [task1];
    async.series(tasks);
});

module.exports = router;
