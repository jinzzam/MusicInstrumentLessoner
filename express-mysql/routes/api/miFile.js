var express = require('express');
var router = express.Router();
var async = require('async');
var miFile = require('../../model/dao/miFileDao');

/* GET home page. */
router.get('/', function (req, res, next) {
    var data;
    const task1 = function (callback) {
        miFile.selectAll(function (rows) {
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

router.get('/:filename', function (req, res, next) {
    var fileName = req.params['filename'];
    var data;
    const task1 = function (callback) {
        miFile.selectOne(fileName, function (rows) {
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

router.get('/:ownerEmail/owner', function (req, res, next) {
    var ownerEmail = req.params['ownerEmail'];
    var data;
    const task1 = function (callback) {
        miFile.joinUser(ownerEmail, function (rows) {
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

router.get('/:filename/wrong', function (req, res, next) {
    var filename = req.params['filename'];
    var data;
    const task1 = function (callback) {
        miFile.joinWrong(filename, function (rows) {
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

router.get('/:email/:inner/:outer/insert', function (req, res, next) {
    var userEmail = req.params['email'];
    var innerFileName = req.params['inner'];
    var outerFileName = req.params['outer'];
    const task1 = function (callback) {
        miFile.insert(userEmail, innerFileName, outerFileName, function (rows) {
            callback(null);
        });
        res.send('Insert');
    };
    const tasks = [task1];
    async.series(tasks);
});

router.get('/:email/:filename/delete', function (req, res, next){
    var ownerEmail = req.params['email'];
    var innerFileName = req.params['filename'];
    const task1=function(callback){
        miFile.delete(ownerEmail, innerFileName, function(rows){
            callback(null);
        });
        res.send('Hello World');
    };
    const tasks = [task1];
    async.series(tasks);
});

module.exports = router;
