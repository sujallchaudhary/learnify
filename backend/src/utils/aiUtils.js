const together = require('together-ai');

const togetherAI = new together.Together({
    apiKey: process.env.TOGETHER_API_KEY
});

const togetherAiGenerateText = async (context, prompt, model, stream = false) => {

    const togetherModel = [
        'deepseek-ai/DeepSeek-R1-Distill-Llama-70B-free',
        'meta-llama/Llama-3.3-70B-Instruct-Turbo-Free',
        'meta-llama/Llama-Vision-Free'
    ];

    const response = await togetherAI.chat.completions.create({
        model: togetherModel[model],
        messages: [
            {
                "role": "user",
                "content": [
                    ...context,
                    {
                        "type": "text",
                        "text": prompt
                    },
                ]
            }
        ],
        stream: stream
    });
    return response;
};
module.exports = { togetherAiGenerateText };
