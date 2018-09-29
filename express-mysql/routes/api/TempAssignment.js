var express = require('express');
var router = express.Router();
var async = require('async');
var miTempAssignment = require('../../model/dao/miTempAssignmentDao');

/* GET home page. */
router.get('/', function (req, res, next) {
    var data;
    const task1 = function (callback) {
        miTempAssignment.selectAll(function (rows) {
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

router.get('/:studentEmail', function (req, res, next) {
    var fileName = req.params['studentEmail'];
    var data;
    const task1 = function (callback) {
        miTempAssignment.selectOne(fileName, function (rows) {
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

router.get('/:filename/:tempid/:semail/:todo/:success/insert', function (req, res, next) {
    var innerFilename = req.params['filename'];
    var tempId = req.params['tempid'];
    var studentEmail = req.params['semail'];
    var todo = req.params['todo'];
    var done = 0;
    var sPercent = req.params['success'];
    const task1 = function (callback) {
        miTempAssignment.insert(innerFilename, tempId,studentEmail, todo, done, sPercent, function (rows) {
            callback(null);
        });
        res.send('Insert');
    };
    const tasks = [task1];
    async.series(tasks);
});

router.get('/:filename/done-count', function(req, res, next){
    var innerFilename = req.params['filename'];
    const task1 = function (callback) {
        miTempAssignment.count(innerFilename, function (rows) {
            callback(null);
        });
        res.send('Count ++');
    };
    const tasks = [task1];
    async.series(tasks);
});

router.get('/:filename/delete', function(req, res, next){
    var innerFilename = req.params['filename'];
    const task1 = function (callback) {
        miTempAssignment.delete(innerFilename, function (rows) {
            callback(null);
        });
        res.send('Delete');
    };
    const tasks = [task1];
    async.series(tasks);
});

router.get('/:percent/:filename/complete', function(req, res, next){
    var cPer = req.params['percent'];
    var innerFilename = req.params['filename'];
    const task1 = function (callback) {
        miTempAssignment.complete(cPer, innerFilename, function (rows) {
            callback(null);
        });
        res.send('Complete');
    };
    const tasks = [task1];
    async.series(tasks);
});

module.exports = router;