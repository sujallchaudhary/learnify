const mongoose = require('mongoose');

const roadmapSchema = new mongoose.Schema({
    topic: {type:String,required:true},
    roadmap: {type:String,required:true},
})

const Roadmap = mongoose.model('Roadmap',roadmapSchema);

module.exports = Roadmap;
