var express = require('express');
var router = express.Router();
var async = require('async');
var miGroup = require('../../model/dao/miGroupDao');

/* GET home page. */
router.get('/', function (req, res, next) {
    var data;
    const task1 = function (callback) {
        miGroup.selectAll(function (rows) {
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
        miGroup.selectOne(groupName, function (rows) {
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

router.get('/:name/:place/:info/:instruments/:genre', function (req, res, next) {
    var groupName = req.params['name'];
    var groupPlace = req.params['place'];
    var groupInfo = req.params['info'];
    var instruments = req.params['instruments'];
    var groupGenre = req.params['genre'];
    const task1 = function (callback) {
        miGroup.insert(groupName, groupPlace, groupInfo, instruments, groupGenre, function (rows) {
            callback(null);
        });
        res.send('Hello World');
    };
    const tasks = [task1];
    async.series(tasks);
});

router.get('/joinjoin', function(req, res, next){
    const task1 = function (callback) {
        miGroup.join(function (rows) {
            callback(null);
        });
        res.send('Hello World');
    };
    const tasks = [task1];
    async.series(tasks);
});


module.exports = router;
