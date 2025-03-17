const {togetherAiGenerateText} = require('../utils/aiUtils');
const Doubt = require('../models/doubt');

const solveDoubt = async (req,res) => {
    const {query,file}=req.body;
    let imageContent="";
    try {

        if(file) {
            const imageResponse = await togetherAiGenerateText([{type:"image_url",image_url:{url:file}}], `You are a OCR expert. You are given an image of a text and you need to extract the text from the image. the text is a image of a question. you need to extract the question text and return it in a text format. do not include any other text or explanation.`, 2);
            imageContent = imageResponse.choices[0].message.content;
        }
        console.log(imageContent);
        
        const context = [
            {type:"text",text:imageContent},
            {type:"text",text:"You are a helpful AI assistant that helps students with their doubts."},
        ];
        
        const stream = await togetherAiGenerateText(context, query, 0, true);
        res.setHeader('Content-Type', 'text/event-stream');
        res.setHeader('Cache-Control', 'no-cache');
        res.setHeader('Connection', 'keep-alive');

        let fullResponse = '';
        
        for await (const chunk of stream) {
            const content = chunk.choices[0]?.delta?.content || '';
            if (content) {
                fullResponse += content;
                res.write(`data: ${JSON.stringify({ content })}\n\n`);
            }
        }
        res.end();
        
        const doubt = await Doubt.create({
            query,
            image: file,
            response: fullResponse,
            createdAt: new Date()
        });
        await doubt.save();
    } catch (error) {
        console.error('Error solving doubt:', error);
        res.status(500).json({ error: 'Failed to solve doubt' });
    }
};

const getDoubt = async (req,res) => {
    const {id} = req.params;
    try {
        const doubt = await Doubt.findById(id);
        if(!doubt){
            return res.status(404).json({success:false,error:"Doubt not found"});
        }
        res.status(200).json({success:true,data:doubt});
    } catch (error) {
        console.error('Error getting doubt:', error);
        res.status(500).json({ success: false, error: 'Failed to get doubt' });
    }
}

const getAllDoubts = async (req,res) => {
    try {
        const doubts = await Doubt.find().select("_id query");
        if(!doubts){
            return res.status(404).json({success:false,error:"No doubts found"});
        }
        res.status(200).json({success:true,data:doubts});
    } catch (error) {
        console.error('Error getting all doubts:', error);
        res.status(500).json({ success: false, error: 'Failed to get all doubts' });

    }
}

const markDoubtAsSolved = async (req,res) => {
    const {id} = req.params;
    try {
        const doubt = await Doubt.findById(id);
        if(!doubt){
            return res.status(404).json({success:false,error:"Doubt not found"});
        }
        doubt.isSolved = true;
        await doubt.save();
        res.status(200).json({success:true,data:doubt});
    } catch (error) {
        console.error('Error marking doubt as solved:', error);
        res.status(500).json({ success: false, error: 'Failed to mark doubt as solved' });
    }
}

module.exports = {solveDoubt,getDoubt,getAllDoubts,markDoubtAsSolved}

