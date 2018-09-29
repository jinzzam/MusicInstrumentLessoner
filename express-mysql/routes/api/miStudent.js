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

router.get('/:sEmail/:groupName/insert', function (req, res, next) {
    var sEmail = req.params['sEmail'];
    var groupName = req.params['groupName'];
    const task1 = function (callback) {
        miStudent.insert(sEmail,groupName, function (rows) {
            callback(null);
        });
        res.send('Insert');
    };
    const tasks = [task1];
    async.series(tasks);
});

router.get('/:semail/delete', function (req, res, next) {
    var sEmail = req.params['semail'];
    const task1= function(callback) {
        miStudent.delete(sEmail, function (rows){
            callback(null);
        });
        res.send('Done');
    };
    const tasks = [ task1];
    async.series(tasks);
});


module.exports = router;
