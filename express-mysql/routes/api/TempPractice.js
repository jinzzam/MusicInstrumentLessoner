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

router.get('/:innerFilename/file', function (req, res, next) {
    var innerFilename = req.params['innerFilename'];
    var data;
    const task1 = function (callback) {
        miTempPractice.joinFile(innerFilename, function (rows) {
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

router.get('/:tempid/:email/:filename/:isdone/:complete/insert', function (req, res, next) {
    var tempId = req.params['tempid'];
    var  sEmail = req.params['email'];
    var innerFile = req.params['filename'];
    var is_done = req.params['isdone'];
    var completePercent = req.params['complete'];
    const task1 = function (callback) {
        miTempPractice.insert(tempId, sEmail,innerFile,is_done, completePercent, function (rows) {
            callback(null);
        });
        res.send('Insert');
    };
    const tasks = [task1];
    async.series(tasks);
});

router.get('/:practiceId/isDone', function(req, res, next){
    var pId = req.params['practiceId'];
    const task1= function(callback){
        miTempPractice.isDone(pId, function(rows){
            callback(null);
        });
        res.send('is Done !');
    };
    const tasks = [task1];
    async.series(tasks);
});

router.get('/:percent/:practiceId/complete', function(req, res, next){
    var cPer = req.params['percent'];
    var pId = req.params['practiceId'];
    const task1= function(callback){
        miTempPractice.Complete(cPer, pId, function(rows){
            callback(null);
        });
        res.send('Complete Percent Change');
    };
    const tasks = [task1];
    async.series(tasks);
});

module.exports = router;