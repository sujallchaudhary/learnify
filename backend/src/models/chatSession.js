const mongoose = require('mongoose');

const chatSessionSchema = new mongoose.Schema({
    user:{
        type:mongoose.Schema.Types.ObjectId,
        ref:'User',
        required:true
    },
    isShared:{
        type:Boolean,
        default:false
    },
    createdAt:{
        type:Date,
        default:Date.now,
        select:false
    }
})

const ChatSession = mongoose.model('ChatSession',chatSessionSchema);
module.exports = ChatSession;
