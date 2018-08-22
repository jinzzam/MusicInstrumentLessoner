var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');

var indexRouter = require('./routes/index');
var usersRouter = require('./routes/users');
var userApiRouter = require('./routes/api/miUser');
var tempRouter = require('./routes/api/Template');
var tempGuideRouter = require('./routes/api/TempGuide');
var tempPracticeRouter = require('./routes/api/TempPractice');
var notiRouter = require('./routes/api/miNotification');
var tempWrongRouter = require('./routes/api/TempWrong');
var fileRouter = require('./routes/api/miFile');
var groupRouter = require('./routes/api/miGroup');
var fileUploadRouter = require('./routes/fileUpload');

var app = express();

// views engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('views engine', 'ejs');

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({extended: false}));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use('/', indexRouter);
app.use('/users', usersRouter);
app.use('/api/miUser', userApiRouter);
app.use('/api/Template', tempRouter);
app.use('/api/TempGuide', tempGuideRouter);
app.use('/api/TempPractice', tempPracticeRouter);
app.use('/api/miNotification', notiRouter);
app.use('/api/TempWrong', tempWrongRouter);
app.use('/api/miFile', fileRouter);
app.use('/api/miGroup', groupRouter);
app.use('/fileUpload',fileUploadRouter);

// catch 404 and forward to error handler
app.use(function (req, res, next) {
    next(createError(404));
});

// error handler
app.use(function (err, req, res, next) {
    // set locals, only providing error in development
    res.locals.message = err.message;
    res.locals.error = req.app.get('env') === 'development' ? err : {};

    // render the error page
    res.status(err.status || 500);
    res.render('error');
});

module.exports = app;
