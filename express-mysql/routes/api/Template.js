var express = require('express');
var router = express.Router();
var async = require('async');
var miTemplate = require('../../model/dao/miTemplateDao');

/* GET home page. */
router.get('/', function (req, res, next) {
    var data;
    const task1 = function (callback) {
        miTemplate.selectAll(function (rows) {
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
        miTemplate.selectOne(templateId, function (rows) {
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

router.get('/:owner/:title/:musician', function (req, res, next) {
    var tempOwner = req.params['owner'];
    var musicTitle = req.params['title'];
    var musician = req.params['musician'];
    const task1 = function (callback) {
        miTemplate.insert(tempOwner, musicTitle, musician, function (rows) {
            callback(null);
        });
        res.send('Hello World');
    };
    const tasks = [task1];
    async.series(tasks);
});

module.exports = router;
