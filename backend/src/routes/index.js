const express = require('express');
const router = express.Router();
const doubtRoutes = require('./doubtRoute');
const flashCardRoutes = require('./flashCard');
const roadmapRoutes = require('./roadmap');
const authRoutes = require('./authRoute');

router.get('/health', (req, res) => {
  res.status(200).json({
    success: true,
    message: 'Server is running',
    timestamp: new Date().toISOString()
  });
});
router.use('/doubt',doubtRoutes);
router.use('/flashcard',flashCardRoutes);
router.use('/roadmap',roadmapRoutes);
router.use('/auth',authRoutes);
module.exports = router;
