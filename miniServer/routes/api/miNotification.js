var express = require('express');
var router = express.Router();
var async = require('async');
var miNoti = require('../../model/dao/miNotificationDao');

/* GET home page. */
router.get('/', function (req, res, next) {
    var data;
    const task1 = function (callback) {
        miNoti.selectAll(function (rows) {
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

router.get('/:id', function (req, res, next) {
    var templateId = req.params['id'];
    var data;
    const task1 = function (callback) {
        miNoti.selectOne(templateId, function (rows) {
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

router.get('/:notiid/:tempid/:type/:comment', function (req, res, next) {
    var notiId = req.params['notiid'];
    var tempId = req.params['tempid'];
    var type = req.params['type'];
    var comment = req.params['comment'];
    const task1 = function (callback) {
        miNoti.insert(notiId, tempId, type, comment, function (rows) {
            callback(null);
        });
        res.send('Hello World');
    };
    const tasks = [task1];
    async.series(tasks);
});

module.exports = router;