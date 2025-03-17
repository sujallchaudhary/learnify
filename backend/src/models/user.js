const mongoose = require('mongoose');

const userSchema = new mongoose.Schema({
    name:{
        type:String,
        required:[true,'Please enter your name']
    },
    email:{
        type:String,
        required:[true,'Please enter your email'],
        unique:true,
        match:[/^\S+@\S+\.\S+$/,'Please enter a valid email address']
    },
    password:{
        type:String,
        required:[true,'Please enter your password'],
        minlength:[8,'Password must be at least 8 characters long']
    },
    grade:{
        type:String,
        required:[true,'Please enter your grade']
    },
    instituteName:{
        type:String,
        required:[true,'Please enter your institute name']
    },
});

const User = mongoose.model('User',userSchema);

module.exports = User;
