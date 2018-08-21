var express = require('express');
var app = express();
var router = express.Router();
var miUser = require('../model/dao/miUserDao');
var async = require('async');

app.get('/users', (req, res) => {
    console.log('who get in here/users');
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
        res.json(data);
        callback(null);
    };
    const tasks = [task1, task2, task3];
    async.series(tasks);
});

app.post('/post', (req, res) => {
    console.log('who get in here post /users');
    var inputData;

    req.on('data', (data) => {
        inputData = JSON.parse(data);
    });

    req.on('end', () => {
        console.log("useremail : " + inputData.useremail + " , name : " + inputData.username + ", password : " + inputData.password);
        //miUser.insert(inputData.useremail, inputData.password, inputData.username,function (rows) {
        //});
        miUser.selectOne(inputData.useremail, function (rows) {
            console.log(rows);
        });
    });
    res.write('wow');
    res.end();
});

module.exports = router;
