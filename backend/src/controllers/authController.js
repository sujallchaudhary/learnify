const User = require('../models/user');
const bcrypt = require('bcrypt');
const jwt = require('jsonwebtoken');

const register = async (req,res) => {
    const {name,email,password,grade,instituteName} = req.body;
    try{
        const existingUser = await User.findOne({email});
        if(existingUser){
            return res.status(400).json({success:false,error:"User already exists"});
        }
        const hashedPassword = await bcrypt.hash(password,10);
        const user = await User.create({name,email,password:hashedPassword,grade,instituteName});
        user.password = undefined;
        const token = jwt.sign({_id:user._id},process.env.JWT_SECRET,{expiresIn:"30d"});
        res.status(201).json({success:true,data:user,token});
    }
    catch(error){
        console.log(error);
        res.status(500).json({success:false,error:"Failed to register"});
    }
}

const login = async (req,res) => {
    const {email,password} = req.body;
    try{
        const user = await User.findOne({email});
        if(!user){
            return res.status(400).json({success:false,error:"Invalid email or password"});
        }
        const isMatch = await bcrypt.compare(password,user.password);
        if(!isMatch){
            return res.status(400).json({success:false,error:"Invalid email or password"});
        }
        user.password = undefined;
        const token = jwt.sign({_id:user._id},process.env.JWT_SECRET,{expiresIn:"30d"});
        res.status(200).json({success:true,data:user,token});
    }
    catch(error){
        console.log(error);
        res.status(500).json({success:false,error:"Failed to login"});
    }
}

const me = async (req,res) => {
    const {_id} = req.user;
    const user = await User.findById(_id);
    if(!user){
        return res.status(400).json({success:false,error:"User not found"});
    }
    user.password = undefined;
    res.status(200).json({success:true,data:user});
}

module.exports = {register,login,me};
