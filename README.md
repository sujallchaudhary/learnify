# AI-Driven Adaptive Learning Platform

A personalized education solution that adapts to each student's learning style, pace, and needs using AI.<br>
**(See another branch for website code)**
## 🌟 Features

- **Personalized Learning Paths**: AI identifies student weaknesses and customizes study materials accordingly
- **Real-Time Feedback**: AI-powered virtual tutors help students with doubts and practice tests
- **Adaptive Content Delivery**: Content is presented based on the student's preferred learning style
- **Progress Tracking**: Comprehensive analytics on student progress, strengths, and weaknesses
- **Urban-Rural Education Bridge**: Makes quality education accessible to underprivileged students

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
