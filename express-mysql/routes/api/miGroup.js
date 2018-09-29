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

router.get('/:useremail/join', function(req, res, next){
    var userEmail = req.params['useremail'];
    var data;
    const task1 = function (callback) {
        miGroup.join(userEmail, function (rows) {
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

router.get('/:type/:str/:groupName/update', function (req, res, next){
    var type= req.params['type'];
    var str = req.params['str'];
    var groupName = req.params['groupName'];
    switch(type){
        case'info':
            const task1 = function (callback) {
            miGroup.infoupdate(str, groupName, function (rows) {
                callback(null);
            });
            res.send('info update');
        };
            const tasks1 = [task1];
            async.series(tasks1);
            break;
        case'place':
            const task2 = function (callback) {
                miGroup.placeupdate(str, groupName, function (rows) {
                    callback(null);
                });
                res.send('place update');
            };
            const tasks2 = [task2];
            async.series(tasks2);
            break;
        case'ins':
            const task3 = function (callback) {
                miGroup.insupdate(str, groupName, function (rows) {
                    callback(null);
                });
                res.send('instruments update');
            };
            const tasks3 = [task3];
            async.series(tasks3);
            break;
        case'genre':
            const task4 = function (callback) {
                miGroup.genreupdate(str, groupName, function (rows) {
                    callback(null);
                });
                res.send('genre update');
            };
            const tasks4 = [task4];
            async.series(tasks4);
            break;
    }

});

module.exports = router;
