const mongoose = require('mongoose');

const flashCardSchema = new mongoose.Schema({
    topic:{
        type:String,
        required:true
    },
    flashCards:{
        type:Array,
        required:true
    }
});

const FlashCard = mongoose.model('FlashCard',flashCardSchema);

module.exports = FlashCard;