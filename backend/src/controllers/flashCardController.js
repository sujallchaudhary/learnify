const {togetherAiGenerateText} = require('../utils/aiUtils');
const FlashCard = require('../models/flashcards');

const createFlashCard = async (req,res) => {
    const {topic}=req.body;
    try{
        const togetherResponse = await togetherAiGenerateText([{type:"text",text:topic}],`create flashcards for the topic of ${topic} in JSON format. Each flashcard should include a category, question, and answer. Follow this exact format for each card:

{ "category": "CategoryName", "question": "Question text goes here?", "answer": "Answer text goes here." },

Create at least 20 flashcards covering the most important concepts, definitions, principles, and applications of ${topic}. Organize them into 4-6 logical categories that help structure the knowledge.

Make the questions clear and specific. Ensure answers are concise but comprehensive enough to fully address the question. Format all cards consistently with the exact JSON structure shown above, with double quotes around all string values.

Example format:
[
  { "category": "Category1", "question": "Sample question 1?", "answer": "Sample answer 1." },
] do not include any other text or explanation.`,1);
 if(togetherResponse.choices[0].message.content){
        const flashCards = JSON.parse(togetherResponse.choices[0].message.content);
        await FlashCard.create({topic,flashCards});
        res.status(200).json({success:true,data:flashCards});
 }
    }
    catch(error){
        console.log(error);
        res.status(500).json({success:false,error:"Failed to create flash card"});
    }
};

const getFlashCards = async (req,res) => {
    const {id} = req.params;
    try{
        const flashCards = await FlashCard.findById(id);
    if(!flashCards){
        return res.status(404).json({success:false,error:"Flash cards not found"});
    }
        res.status(200).json({success:true,data:flashCards});
    }
    catch(error){
        console.log(error);
        res.status(500).json({success:false,error:"Failed to get flash cards"});
    }
}

const getAllFlashCards = async (req,res) => {
    try{
        const flashCards = await FlashCard.find().select('topic _id');
        if(!flashCards){
            return res.status(404).json({success:false,error:"Flash cards not found"});
        }
        res.status(200).json({success:true,data:flashCards});
    }
    catch(error){
        console.log(error);
        res.status(500).json({success:false,error:"Failed to get all flash cards"});
    }
}  

module.exports = {createFlashCard,getFlashCards,getAllFlashCards};
