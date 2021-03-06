var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');
var cors = require('cors');

var userApiRouter = require('./routes/api/miUser');
var tempRouter = require('./routes/api/Template');
var tempAssignmentRouter = require('./routes/api/TempAssignment');
var tempGuideRouter = require('./routes/api/TempGuide');
var tempPracticeRouter = require('./routes/api/TempPractice');
var notiRouter = require('./routes/api/miNotification');
var tempWrongRouter = require('./routes/api/TempWrong');
var fileRouter = require('./routes/api/miFile');
var groupRouter = require('./routes/api/miGroup');
var teacherRouter = require('./routes/api/miTeacher');
var studentRouter = require('./routes/api/miStudent');
var fileUploadRouter = require('./routes/fileUpload');

var app = express();

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');

app.use(cors());
app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({extended: false}));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use('/api/user', userApiRouter);
app.use('/api/template', tempRouter);
app.use('/api/template-assignment', tempAssignmentRouter);
app.use('/api/template-guide', tempGuideRouter);
app.use('/api/template-practice', tempPracticeRouter);
app.use('/api/notification', notiRouter);
app.use('/api/template-wrong', tempWrongRouter);
app.use('/api/file', fileRouter);
app.use('/api/group', groupRouter);
app.use('/api/group-teacher', teacherRouter);
app.use('/api/group-student', studentRouter);
app.use('/fileUpload', fileUploadRouter);

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
