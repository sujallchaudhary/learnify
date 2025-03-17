const {togetherAiGenerateText} = require('../utils/aiUtils');
const Roadmap = require('../models/roadmap');

const createRoapmap = async (req,res) => {
    try{
        const {topic} = req.body;
        const togetherResponse = await togetherAiGenerateText([],`You are an expert educator and curriculum designer tasked with creating a detailed learning path and roadmap for a learner to master ${topic}. The learner is {beginner, e.g., 'a beginner with no prior experience', 'an intermediate learner with basic knowledge', 'an advanced learner looking to specialize'}. Your goal is to provide a step-by-step guide that includes:

Overview: A brief explanation of what ${topic} entails and why it's valuable to learn.
Prerequisites: Any foundational skills, knowledge, or tools the learner needs before starting ${topic}.
Learning Path: A structured breakdown of ${topic} into phases or modules (e.g., beginner, intermediate, advanced), with specific subtopics or skills to focus on in each phase.
Timeline: A suggested timeline for each phase (e.g., weeks or months), assuming the learner dedicates e.g., '5-10 hours per week'}.
Resources: Recommended resources for each phase, including books, online courses, videos, websites, or practical exercises relevant to ${topic} (e.g., projects, challenges).
Milestones: Key checkpoints or achievements to track progress in ${topic} (e.g., 'complete a basic task', 'grasp core concepts').
Tips for Success: Practical advice to stay motivated, overcome challenges, or deepen understanding of ${topic}.
Keep the roadmap clear, actionable, and tailored to the learner's starting level. Avoid overwhelming them with too much detail, but ensure the path is comprehensive enough to achieve proficiency in ${topic}. If any tools or software are relevant to ${topic} (e.g., specific programs, platforms, or libraries), include them in the appropriate sections. Present the roadmap in an organized format, such as numbered sections or bullet points..`,1);
        if(togetherResponse.choices[0].message.content){
            const roadmap = togetherResponse.choices[0].message.content;
            const roadmapData = await Roadmap.create({topic,roadmap:roadmap});
            res.status(200).json({success:true,data:roadmap});
        }
    }
    catch(error){
        console.log(error);
        res.status(500).json({success:false,error:"Failed to create roapmap"});
    }
};

const getRoadmap = async (req,res) => {
    try{
        const id = req.params.id;
        const roadmap = await Roadmap.findById(id);
        if(!roadmap){
        return res.status(404).json({success:false,error:"Roadmap not found"});
    }
        res.status(200).json({success:true,data:roadmap});
    }
    catch(error){
        console.log(error);
        res.status(500).json({success:false,error:"Failed to get roadmap"});
    }
}

const getAllRoadmap = async (req,res) => {
    try{
        const roadmap = await Roadmap.find();
        if(!roadmap){
            return res.status(404).json({success:false,error:"Roadmap not found"});
        }
        res.status(200).json({success:true,data:roadmap});
    }
    catch(error){
        console.log(error);
        res.status(500).json({success:false,error:"Failed to get all roadmap"});
    }
}
module.exports = {createRoapmap,getRoadmap,getAllRoadmap};
