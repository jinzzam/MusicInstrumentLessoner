var express = require('express');
var app = express();
var router = express.Router();
var miUser = require('../model/dao/miUserDao');
var async = require('async');

app.post('/users', (req, res) => {
    console.log('who get in here/users');
    console.log('who get in here post /users');
    var inputData;
    var data;

    req.on('data', (data) => {
        inputData = JSON.parse(data);
    });

    req.on('end', () => {
        miUser.selectOne(inputData.searchemail, function (rows) {
            data = rows;
            res.json(data);
        });
    });
});

app.post('/post', (req, res) => {
    console.log('who get in here post /users');
    var inputData;
    var data;

    req.on('data', (data) => {
        inputData = JSON.parse(data);
    });

    req.on('end', () => {
        console.log("useremail : "+ inputData.useremail + " , name : "+inputData.username + ", password : "+ inputData.password);
       miUser.insert(inputData.useremail, inputData.password, inputData.username,function (rows) {
        });
        // miUser.selectOne(inputData.useremail, function(rows){
        //     data = rows;
        //     console.log(data);
        // });
        //
    });
 res.write('OK !');
 res.end();
});

app.listen(3100, () => {
    console.log('Example app listening on port 3100!');
});

module.exports = router;
