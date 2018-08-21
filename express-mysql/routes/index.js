var express = require('express');
var app = express();
var router = express.Router();
var miUser = require('../model/dao/miUserDao');

app.post('/users', (req, res) => {
    console.log('who get in here/users');
    var inputData;

    req.on('data', (data) => {
        inputData = JSON.parse(data);
    });

    req.on('end', () => {
        miUser.selectOne(inputData.searchEmail, function(rows){
            res.json(rows);
        });
    });

});

app.post('/post', (req, res) => {
    console.log('who get in here post /users');
    var inputData;

    req.on('data', (data) => {
        inputData = JSON.parse(data);
    });

    req.on('end', () => {
        console.log("useremail : "+ inputData.userEmail + " , name : "+inputData.userName + ", password : "+ inputData.userPassword);
       miUser.insert(inputData.userEmail, inputData.userPassword, inputData.userName,function (rows) {
        });
    });
    res.write('wow');
    res.end();
});

app.listen(3100, () => {
    console.log('Example app listening on port 3100!');
});

module.exports = router;
