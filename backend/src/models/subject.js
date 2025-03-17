const mongoose = require('mongoose');

const subjectSchema = new mongoose.Schema({
    name:{
        type:String,
        required:[true,'Please enter the subject name']
    },
    description:{
        type:String,
        required:[true,'Please enter the subject description']
    },
});

const Subject = mongoose.model('Subject',subjectSchema);

module.exports = Subject;
