# AI-Driven Adaptive Learning Platform

A personalized education solution that adapts to each student's learning style, pace, and needs using AI.<br>
**(See another branch for website code)**
## 🌟 Features

- **Personalized Learning Paths**: AI identifies student weaknesses and customizes study materials accordingly
- **Real-Time Feedback**: AI-powered virtual tutors help students with doubts and practice tests
- **Adaptive Content Delivery**: Content is presented based on the student's preferred learning style
- **Progress Tracking**: Comprehensive analytics on student progress, strengths, and weaknesses
- **Urban-Rural Education Bridge**: Makes quality education accessible to underprivileged students

## Screenshots
<img width="236" alt="image" src="https://github.com/user-attachments/assets/ae9fcd10-822f-4db9-9b30-de5a95751606" />
<img width="235" alt="image" src="https://github.com/user-attachments/assets/551f7257-1461-412c-940e-5082bf73fc22" />
<img width="231" alt="image" src="https://github.com/user-attachments/assets/eead917c-1afb-4cef-8345-71bec57e4a22" />
<img width="231" alt="image" src="https://github.com/user-attachments/assets/72714e6d-bcdd-4ac6-a32e-03645f611eb1" />
<img width="235" alt="image" src="https://github.com/user-attachments/assets/4667d84c-9e27-46f1-8f81-c831bd225047" />
<img width="236" alt="image" src="https://github.com/user-attachments/assets/8e87962f-aeb5-43ec-9602-c66de3516e53" />
<img width="237" alt="image" src="https://github.com/user-attachments/assets/2fa61418-a4c5-4eb7-bc66-c18c772210ca" />
<img width="234" alt="image" src="https://github.com/user-attachments/assets/3d2c6008-8bcc-48fd-80c8-495f7c16bd0f" />
<img width="240" alt="image" src="https://github.com/user-attachments/assets/68d7917d-6a94-4538-b5e8-830632ac97e8" />
<img width="235" alt="image" src="https://github.com/user-attachments/assets/9ba55a28-e9ba-47f1-ba45-9c84a996b40e" />
<img width="233" alt="image" src="https://github.com/user-attachments/assets/6265ec31-1947-4324-a0e7-5908d560af54" />
<img width="237" alt="image" src="https://github.com/user-attachments/assets/d8ec4662-936d-47a3-b900-bb51a79227be" />

## Video Demo
https://drive.google.com/file/d/1sgr1hsaCZ6_W5QbUP74xkJXGkwEXevf2/view?usp=sharing


## 🛠️ Technologies Used

- **Backend**: Node.js, Express.js
- **Database**: MongoDB with Mongoose
- **Authentication**: JWT (JSON Web Tokens)
- **Security**: Helmet, CORS
- **AI Integration**: OpenAI compatible (framework ready for AI integration)
- **Website**: HTML, CSS, TypeScript, Next.js, React
- **Mobile App**: Kotlin, Retrofit, Jetpack Compose



## 📝 API Endpoints

### Authentication
- `POST /api/v1/users/register` - Register a new user
- `POST /api/v1/users/login` - Login user
- `GET /api/v1/users/profile` - Get user profile (protected)

### Subjects
- `GET /api/v1/subjects` - Get all subjects
- `GET /api/v1/subjects/:id` - Get subject by ID
- `POST /api/v1/subjects` - Create new subject (admin/teacher only)
- `PUT /api/v1/subjects/:id` - Update subject (admin/teacher only)
- `DELETE /api/v1/subjects/:id` - Delete subject (admin only)
- `GET /api/v1/subjects/grade/:grade` - Get subjects by grade level

### Learning
- `GET /api/v1/learning/path/:studentId/:subjectId` - Get personalized learning path
- `GET /api/v1/learning/content/:studentId/:topicId` - Get recommended content
- `POST /api/v1/learning/quiz/:studentId/:quizId` - Submit quiz and get feedback
- `POST /api/v1/learning/tutor/:studentId` - Get AI tutor assistance

## 🧠 AI Features Implementation

The platform is designed with integration points for AI services:

1. **Personalized Learning Paths**: The system analyzes student performance data to generate customized learning paths using AI algorithms.

2. **Content Adaptation**: Content is adapted based on the student's learning style preferences.

3. **Quiz Analysis**: AI analyzes quiz results to identify strengths, weaknesses, and recommend targeted practice.

4. **Virtual Tutor**: AI-powered virtual tutor provides personalized assistance and answers student questions.

## 🔜 Roadmap

- Mobile application
- Offline learning capabilities
- Peer learning and collaboration features
- Parent dashboard for monitoring progress
- Integration with school management systems

## 📄 License

This project is licensed under the ISC License. 
