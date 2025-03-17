const axios = require('axios');

async function tts(text) {
    const response = await axios.post('https://api.on-demand.io/services/v1/public/service/execute/text_to_speech',
        {
            "input":text,
            "voice": "echo",
            "model": "tts-1"
        },
        {
            headers:{
                apikey:process.env.OD_KEY || 'tywDrSApltSOGBuZAmx3hS1WdKtzi8Br'
            }
        }
    );
    return response.data;
};

const test = async () => {
    const response = await tts("Hey, congratulations on scoring 99 marks in maths, that's an amazing achievement! I'm really proud of you, and you should be proud of yourself too. Now, don't get too comfortable, because there's always room for improvement, right? Take some time to review your test or exam paper and see where you lost those one mark, and focus on strengthening those areas so you can aim for perfection next time. Also, try to develop your problem-solving skills, learn from your mistakes, and explore the real-world applications of maths to make it more interesting and relevant. And hey, don't forget to stay organized, manage your time effectively, and don't hesitate to ask for help when you need it - we're all here to support you. So, what do you think, is there anything specific you'd like to work on or any questions you have about maths or studying in general?");
    console.log(response);
};

test();

module.exports = tts;

