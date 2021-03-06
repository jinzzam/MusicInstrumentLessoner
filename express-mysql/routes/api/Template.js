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
        miTemplate.selectOneById(templateId, function (rows) {
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
    var ownerEmail = req.params['email'];
    var data;
    const task1 = function (callback) {
        miTemplate.selectOneByEmail(ownerEmail, function (rows) {
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

router.get('/:musicTemplateId/guide', function (req, res, next) {
    var musicTemplateId = req.params['musicTemplateId'];
    var data;
    const task1 = function (callback) {
        miTemplate.joinGuide(musicTemplateId, function (rows) {
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

router.get('/:musicTemplateId/assignment', function (req, res, next) {
    var musicTemplateId = req.params['musicTemplateId'];
    var data;
    const task1 = function (callback) {
        miTemplate.joinAssignment(musicTemplateId, function (rows) {
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

router.get('/:musicTemplateId/practice', function (req, res, next) {
    var musicTemplateId = req.params['musicTemplateId'];
    var data;
    const task1 = function (callback) {
        miTemplate.joinPractice(musicTemplateId, function (rows) {
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

router.get('/:guideupdate/:musicTemplateId/update', function (req, res, next){
    var guideUpdate = req.params['guideupdate'];
    var musicTemplateId = req.params['musicTemplateId'];
    const task1 = function (callback) {
        miTemplate.Gupdate(guideUpdate, musicTemplateId, function (rows){
            callback(null);
        });
        res.send('Hello World');
    };
    const tasks = [task1];
    async.series(tasks);
});

router.get('/:musicTemplateId/delete', function (req, res, next){
    var musicTemplateId = req.params['musicTemplateId'];
    const task1 = function (callback) {
        miTemplate.tempDelete(musicTemplateId, function (rows) {
            callback(null);
        });
        res.send('Hello World');
    };
    const tasks = [task1];
    async.series(tasks);
});

module.exports = router;
