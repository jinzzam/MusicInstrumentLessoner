var express = require('express');
var router = express.Router();
var async = require('async');
var miUser = require('../../model/dao/miUserDao');

/* GET home page. */
router.get('/', function (req, res, next) {
    var data;
    const task1 = function (callback) {
        miUser.selectAll(function (rows) {
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

router.get('/:email', function (req, res, next) {
    var userEmail = req.params['email'];
    var data;
    const task1 = function (callback) {
        miUser.selectOne(userEmail, function (rows) {
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

router.get('/:email/join', function (req, res, next) {
    var userEmail = req.params['email'];
    var data;
    const task1 = function (callback) {
        miUser.join(userEmail, function (rows) {
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

router.get('/:email/:password/:username', function (req, res, next) {
    var userEmail = req.params['email'];
    var userPassword = req.params['password'];
    var userUsername = req.params['username'];
    const task1 = function (callback) {
        miUser.insert(userEmail, userPassword, userUsername, function (rows) {
            callback(null);
        });
        res.send('Hello World');
    };
    const tasks = [task1];
    async.series(tasks);
});


module.exports = router;