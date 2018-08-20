var express = require('express');
var router = express.Router();
var async = require('async');
var miTempPractice = require('../../model/dao/miTempPracticeDao');
// mi_file 에서 filename 받아와야 하기 때문에 아직 설정 불가 !
/* GET home page. */
router.get('/', function (req, res, next) {
    var data;
    const task1 = function (callback) {
        miTempPractice.selectAll(function (rows) {
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
        miTempPractice.selectOne(templateId, function (rows) {
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

router.get('/:email/:filename/:tempid', function (req, res, next) {
    var studentEmail = req.params['email'];
    var innerFilename = req.params['filename'];
    var tempId = req.params['tempid'];
    const task1 = function (callback) {
        miTempPractice.insert(studentEmail, innerFilename, tempId, function (rows) {
            callback(null);
        });
        res.send('Hello World');
    };
    const tasks = [task1];
    async.series(tasks);
});

module.exports = router;