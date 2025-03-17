const mongoose = require('mongoose');

const doubtSchema = new mongoose.Schema({
    query:{
        type:String,
        required:true
    },
    response:{
        type:String,
        required:true
    },
    image:{
        type:String,
        required:false
    },
    isSolved:{
        type:Boolean,
        default:false
    },
    createdAt:{
        type:Date,
        default:Date.now
    }
});

const Doubt = mongoose.model('Doubt',doubtSchema);
module.exports = Doubt;
