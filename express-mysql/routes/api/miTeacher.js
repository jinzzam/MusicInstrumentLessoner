var express = require('express');
var router = express.Router();
var async = require('async');
var miTeacher = require('../../model/dao/miTeacherDao');

/* GET home page. */
router.get('/', function (req, res, next) {
    var data;
    const task1 = function (callback) {
        miTeacher.selectAll(function (rows) {
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

router.get('/:groupname', function (req, res, next) {
    var groupName = req.params['groupname'];
    var data;
    const task1 = function (callback) {
        miTeacher.selectOne(groupName, function (rows) {
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

router.get('/:groupname/:temail', function (req, res, next) {
    var groupName = req.params['groupname'];
    var tEmail = req.params['temail'];
    const task1 = function (callback) {
        miTeacher.insert(groupName, tEmail, function (rows) {
            callback(null);
        });
        res.send('Hello World');
    };
    const tasks = [task1];
    async.series(tasks);
});

module.exports = router;
